package models;

public class Electronics extends Product {
    private String brand;

    public Electronics(String id, String name, double price, int stockQuantity,  String brand) {
        super(id, name, price, stockQuantity);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Brand: " + getBrand());
    }

}
