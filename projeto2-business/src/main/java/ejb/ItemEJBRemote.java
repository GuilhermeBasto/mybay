package ejb;

import data.Item;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ItemEJBRemote {
    void createItem(String userId, String name, String category, String originCountry, byte[] photo, int price);

    void editItem(int id, String name, String category, String originCountry, byte[] photo, int price);

    List<Item> getAllItems(String sort);

    Item getItemById(String id);

    void deleteItem(int id);

    List<Item> getAllItemsFormUser(int userId);

    List<Item> getItemsByCategory(String category, String sort);

    List<Item> getItemsByCountry(String country, String sort);

    List<Item> getItemsBetweenPrice(int lowerPrice, int biggestPrice, String sort);

    List<Item> getItemsByName(String name, String sort);

    List<Item> getItemsAfterDate(String date, String sort);

    void buyItem(int id);

    List<Item> getThreeNewestItems();
}


