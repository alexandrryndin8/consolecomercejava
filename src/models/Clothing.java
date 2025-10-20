package models;

public class Clothing extends Product {
    private String size;

    public Clothing(String id, String name, double price, int stockQuantity, String size) {
        super(id, name, price, stockQuantity);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Size: " + getSize());
    }
}
