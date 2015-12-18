package se.contribe.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Book {

    private int id;

    private String title;

    private String author;

    private BigDecimal price;

    private int quantity;

    private int toBuyQuantity;

    private int status;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getToBuyQuantity() {
        return toBuyQuantity;
    }

    public void setToBuyQuantity(int toBuyQuantity) {
        this.toBuyQuantity = toBuyQuantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
