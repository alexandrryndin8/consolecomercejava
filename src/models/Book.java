package models;

import java.util.ArrayList;

public class Book extends Product {
    private String author;

    public Book(String id, String name, double price, int stockQuantity, String author) {
        super(id, name, price, stockQuantity);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Author: " + getAuthor());
    }
}
