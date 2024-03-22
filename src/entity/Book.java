package entity;

public class Book {
    private int id;
    private String title;
    private String pubicationDate;
    private int price;
    private Author author;

    public Book(){}

    public Book(int id, String title, String pubicationDate, int price, Author author) {
        this.id = id;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return
                "id: " + id + "\n" +
                "pubicationDate: " + pubicationDate + "\n" +
                "price: " + price + "\n" +
                "title: " + title + "\n" +
                "author:" + author
                ;
    }
}
