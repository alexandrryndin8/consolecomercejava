package models;

public class Customer extends User {
    private String address;

    public Customer(String name, String email, String password, String role, String address) {
        super(name, email, password, "customer");
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
