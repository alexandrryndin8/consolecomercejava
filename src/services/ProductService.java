package services;

import models.Product;
import java.util.ArrayList;

public class ProductService {
    private final ArrayList<Product> products = new ArrayList<Product>();

    public boolean addProduct(Product product) {
        if (product == null) return false;
        if (findById(product.getId()) != null) return false;
        products.add(product);
        return true;
    }

    public boolean removeProductById(String id) {
        if (id == null) return false;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (id.equals(p.getId())) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product findById(String id) {
        if (id == null) return null;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (id.equals(p.getId())) {
                return p;
            }
        }
        return null;
    }

    public void listAll() {
        if (products.isEmpty()) {
            System.out.println("No products yet.");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            p.displayInfo();
            System.out.println("---------------------------");
        }
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public boolean updatePrice(String id, double newPrice) {
        Product p = findById(id);
        if (p == null) return false;
        p.setPrice(newPrice);
        return true;
    }

    public boolean updateStock(String id, int newQty) {
        Product p = findById(id);
        if (p == null) return false;
        p.setStockQuantity(newQty);
        return true;
    }
}
