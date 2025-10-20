package models;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalAmount;

    public Order(String orderId, Customer customer, ArrayList<Product> products) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        calculateTotal();
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void calculateTotal() {
        totalAmount = 0.0;
        for (Product product : products) {
            totalAmount += product.getPrice();
        }
    }

    public void printReceipt() {
        System.out.println("----- Order receipt -----");
        System.out.println("Order Id: " + getOrderId());
        System.out.println("Ordered by: " + customer.getName());
        for  (Product product : products) {
            System.out.println("Product Name: " + product.getName() + "-" + product.getPrice());
        }
        System.out.println("Total amount: " + getTotalAmount());
    }

}
