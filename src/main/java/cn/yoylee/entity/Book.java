package cn.yoylee.entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private double price;
    private String comment;
    private int stuid;

    public Book() { }

    public Book(int id, String name, String author, double price, String comment, int stuid) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.comment = comment;
        this.stuid = stuid;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", stuid=" + stuid +
                '}';
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }
}
