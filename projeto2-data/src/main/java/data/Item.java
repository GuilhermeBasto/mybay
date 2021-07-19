package data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String category;
    private String originCountry;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] photograph;
    private int price;
    private boolean bought;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    public Item(){
    }

    public Item(String name, String category, String originCountry, Date date, byte[] photograph, int price) {
        this.name = name;
        this.category = category;
        this.originCountry = originCountry;
        this.date = date;
        this.photograph = photograph;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getPhotograph() {
        return photograph;
    }

    public void setPhotograph(byte[] photograph) {
        this.photograph = photograph;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", date=" + date +
                ", photograph=" + Arrays.toString(photograph) +
                ", price='" + price +
                '}';
    }
}
