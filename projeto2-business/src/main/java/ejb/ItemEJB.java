package ejb;

import data.Item;
import data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class ItemEJB implements ItemEJBRemote {
    private final static Logger logger = Logger.getLogger(ItemEJB.class.getName());

    @PersistenceContext(name = "Users")
    EntityManager em;

    public ItemEJB() {
        // TODO Auto-generated constructor stub
    }


    private Query getSortQuery(String q, String sort) {
        Query query;
        switch (sort) {
            case "asc":
                query = em.createQuery(q + "asc");
                break;
            case "desc":
                query = em.createQuery(q + "desc");
                break;
            default:
                return null;
        }
        return query;
    }

    private Date getDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        return cal.getTime();
    }

    private Date getCurrentDate() {
        logger.info("get current date");
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getTime());
    }

    public void createItem(String userId, String name, String category, String originCountry, byte[] photo, int price) {
        logger.info("create items");
        User user = em.find(User.class, Integer.parseInt(userId));
        Item item = new Item(name, category, originCountry, getCurrentDate(), photo, price);
        item.setUser(user);
        em.persist(item);
        logger.info("Item added");

    }

    public void editItem(int itemId, String name, String category, String originCountry, byte[] photo, int price) {
        logger.info("edit items");
        Item item = em.find(Item.class, itemId);
        item.setName(name);
        item.setCategory(category);
        item.setOriginCountry(originCountry);
        item.setPrice(price);
        if (photo != null) item.setPhotograph(photo);

        em.persist(item);
        logger.info("Item added");

    }

    public List<Item> getAllItems(String sort) {
        logger.info("get All items");
        String q = "FROM Item i where i.bought=false order by i.date ";
        Query query = getSortQuery(q, sort);
        return query.getResultList();
    }

    public List<Item> getThreeNewestItems() {
        logger.info("get the three newest items");
        Query q = em.createQuery("FROM Item i where i.bought=false order by i.date desc");
        return new ArrayList<Item>(q.getResultList().subList(0, 3));
    }

    public List<Item> getAllItemsFormUser(int userId) {
        logger.info("get all users form user");
        User user = em.find(User.class, userId);
        List<Item> sortedList = user.getItems();
        sortedList.sort(new Comparator<Item>() {
            public int compare(Item i1, Item i2) {
                return i1.getDate().compareTo(i2.getDate());
            }
        });
        return sortedList;

    }

    public List<Item> getItemsByCategory(String category, String sort) {
        logger.info("get items by category");
        String q = "FROM Item e WHERE e.category LIKE :category and e.bought=false order by e.category ";
        Query query = getSortQuery(q, sort);
        if (query != null)
            query.setParameter("category", category + "%");
        return query.getResultList();
    }

    private Date string2Date(String date) {
        logger.info("Transform string to date");
        String[] splited = date.split("-");
        int day = Integer.parseInt(splited[2]);
        int month = Integer.parseInt(splited[1]);
        int year = Integer.parseInt(splited[0]);
        return getDate(day, month, year);
    }

    public List<Item> getItemsAfterDate(String date, String sort) {
        logger.info("get items after given date");
        Date newDate = string2Date(date);
        String q = "FROM Item e WHERE e.date > :date and e.bought=false order by e.date ";
        Query query = getSortQuery(q, sort);
        if (query != null)
            query.setParameter("date", newDate);
        return query.getResultList();
    }


    public List<Item> getItemsByCountry(String country, String sort) {
        logger.info("get items by country");
        String q = "FROM Item e WHERE e.originCountry LIKE :country and e.bought=false order by e.originCountry ";
        Query query = getSortQuery(q, sort);
        if (query != null)
            query.setParameter("country", country + "%");
        return query.getResultList();

    }

    public List<Item> getItemsBetweenPrice(int lowerPrice, int biggestPrice, String sort) {
        logger.info("get items between two prices");
        String q = "FROM Item e WHERE e.price BETWEEN :lowerPrice AND :biggestPrice and e.bought=false order by e.price ";
        Query query = getSortQuery(q, sort);
        if (query != null) {
            query.setParameter("lowerPrice", lowerPrice);
            query.setParameter("biggestPrice", biggestPrice);
        }
        return query.getResultList();
    }

    public List<Item> getItemsByName(String name, String sort) {
        logger.info("get items by name");
        String q = "FROM Item e WHERE e.name LIKE :name and e.bought=false order by e.name ";
        Query query = getSortQuery(q, sort);
        if (query != null)
            query.setParameter("name", name + "%");
        return query.getResultList();
    }

    public Item getItemById(String id) {
        logger.info("get item by id");
        return em.find(Item.class, Integer.parseInt(id));

    }

    public void deleteItem(int id) {
        logger.info("deleting a item");
        Item item = em.find(Item.class, id);
        em.remove(item);
    }

    public void buyItem(int id) {
        logger.info("buying a item");
        Item item = em.find(Item.class, id);
        item.setBought(true);
        em.persist(item);
    }

}
