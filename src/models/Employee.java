package models;

public class Employee extends User {
    private int salary;

    public Employee(String name, String email, String password, String role, int salary) {
        super(name, email, password, "employee");
        this.salary = salary;
    }
}
