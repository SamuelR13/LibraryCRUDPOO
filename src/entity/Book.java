package entity;

public class Book extends Author{
    private int id;
    private String title;
    private String pubicationDate;
    private int price;
    private int author;

    public Book(){}

    public Book(int id, String name, String nationality, int id1, String title, String pubicationDate, int price, int author) {
        super(id, name, nationality);
        this.id = id1;
        this.title = title;
        this.pubicationDate = pubicationDate;
        this.price = price;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubicationDate() {
        return pubicationDate;
    }

    public void setPubicationDate(String pubicationDate) {
        this.pubicationDate = pubicationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return
                "id: " + id + "\n" +
                "pubication Date: " + pubicationDate + "\n" +
                "price: " + price + "\n" +
                "title: " + title + "\n" +
                "author: " + super.getName() + "\n"
                ;
    }
}
