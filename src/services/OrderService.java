package services;

import models.Order;
import java.util.ArrayList;

public class OrderService {
    private final ArrayList<Order> orders = new ArrayList<Order>();

    public void addOrder(Order order) {
        if (order == null) return;
        order.calculateTotal();
        orders.add(order);
    }

    public Order findById(String orderId) {
        if (orderId == null) return null;
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (orderId.equals(order.getOrderId())) {
                return order;
            }
        }
        return null;
    }

    public boolean removeById(String orderId) {
        if (orderId == null) return false;
        for (int i = 0; i < orders.size(); i++) {
            if (orderId.equals(orders.get(i).getOrderId())) {
                orders.remove(i);
                return true;
            }
        }
        return false;
    }

    public void listAll() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).printReceipt();
            System.out.println("---------------------------");
        }
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }
}
