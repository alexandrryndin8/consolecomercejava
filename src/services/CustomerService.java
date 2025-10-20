package services;

import models.Customer;
import java.util.ArrayList;

public class CustomerService {
    private final ArrayList<Customer> customers = new ArrayList<Customer>();

    public boolean addCustomer(Customer customer) {
        if (customer == null) return false;
        if (findByEmail(customer.getEmail()) != null) return false;
        customers.add(customer);
        return true;
    }

    public Customer findByEmail(String email) {
        if (email == null) return null;
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            if (email.equals(c.getEmail())) {
                return c;
            }
        }
        return null;
    }

    public void listAll() {
        if (customers.isEmpty()) {
            System.out.println("No customers yet.");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println("Name: " + c.getName()
                    + ", Email: " + c.getEmail()
                    + ", Address: " + c.getAddress());
        }
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }
}
