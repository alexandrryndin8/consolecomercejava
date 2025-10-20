import services.ProductService;
import services.CustomerService;
import services.OrderService;

import models.Product;
import models.Book;
import models.Electronics;
import models.Clothing;
import models.Customer;
import models.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final ProductService productService = new ProductService();
    private static final CustomerService customerService = new CustomerService();
    private static final OrderService orderService = new OrderService();

    public static void main(String[] args) {
        seedDemoData();

        while (true) {
            System.out.println("\n--- Assignment 1 ---");
            System.out.println("1 - Login as Employee");
            System.out.println("2 - Login as Customer");
            System.out.println("0 - Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            if (choice.equals("0")) {
                System.out.println("Bye!");
                break;
            } else if (choice.equals("1")) {
                while (true) {
                    System.out.println("\n--- Employee Menu ---");
                    System.out.println("1 - List products");
                    System.out.println("2 - Add product");
                    System.out.println("3 - Update product price");
                    System.out.println("4 - Update product stock");
                    System.out.println("5 - Remove product by id");
                    System.out.println("6 - List customers");
                    System.out.println("7 - Add customer");
                    System.out.println("8 - List orders");
                    System.out.println("0 - Back");
                    System.out.print("Choose: ");
                    choice = sc.nextLine();

                    if (choice.equals("0")) break;

                    if (choice.equals("1")) {
                        productService.listAll();

                    } else if (choice.equals("2")) {
                        System.out.print("Type (book/electronics/clothing): ");
                        String type = sc.nextLine();

                        System.out.print("id: ");
                        String id = sc.nextLine();
                        System.out.print("name: ");
                        String name = sc.nextLine();

                        System.out.print("price: ");
                        double price = sc.nextDouble(); // читаем double как есть
                        sc.nextLine();                  // очистка перевода строки

                        System.out.print("stock: ");
                        int stock = sc.nextInt();       // читаем int как есть
                        sc.nextLine();                  // очистка перевода строки

                        Product p = null;
                        if (type.equalsIgnoreCase("book")) {
                            System.out.print("author: ");
                            String author = sc.nextLine();
                            p = new Book(id, name, price, stock, author);
                        } else if (type.equalsIgnoreCase("electronics")) {
                            System.out.print("brand: ");
                            String brand = sc.nextLine();
                            p = new Electronics(id, name, price, stock, brand);
                        } else if (type.equalsIgnoreCase("clothing")) {
                            System.out.print("size: ");
                            String size = sc.nextLine();
                            p = new Clothing(id, name, price, stock, size);
                        } else {
                            System.out.println("Unknown type. Try book, electronics, or clothing");
                        }

                        if (p != null) {
                            boolean ok = productService.addProduct(p);
                            System.out.println(ok ? "Product added." : "Duplicate id.");
                        }

                    } else if (choice.equals("3")) {
                        System.out.print("Enter product id: ");
                        String id = sc.nextLine();
                        System.out.print("New price: ");
                        double newPrice = sc.nextDouble();
                        sc.nextLine();
                        boolean ok = productService.updatePrice(id, newPrice);
                        System.out.println(ok ? "Updated." : "Product not found.");

                    } else if (choice.equals("4")) {
                        System.out.print("Enter product id: ");
                        String id = sc.nextLine();
                        System.out.print("New stock quantity: ");
                        int newQty = sc.nextInt();
                        sc.nextLine();
                        boolean ok = productService.updateStock(id, newQty);
                        System.out.println(ok ? "Updated." : "Product not found.");

                    } else if (choice.equals("5")) {
                        System.out.print("Enter product id to remove: ");
                        String id = sc.nextLine();
                        boolean ok = productService.removeProductById(id);
                        System.out.println(ok ? "Removed." : "Product not found.");

                    } else if (choice.equals("6")) {
                        customerService.listAll();

                    } else if (choice.equals("7")) {
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        System.out.print("Address: ");
                        String address = sc.nextLine();
                        Customer c = new Customer(name, email, password, "customer", address);
                        boolean ok = customerService.addCustomer(c);
                        System.out.println(ok ? "Customer added." : "Email already exists.");

                    } else if (choice.equals("8")) {
                        orderService.listAll();

                    } else {
                        System.out.println("Wrong choice.");
                    }
                }

            } else if (choice.equals("2")) {
                System.out.print("\nEnter your email: ");
                String email = sc.nextLine();
                Customer current = customerService.findByEmail(email);
                if (current == null) {
                    System.out.println("Customer not found.");
                } else {
                    while (true) {
                        System.out.println("\n--- Hello, " + current.getName() + "---");
                        System.out.println("1 - List products");
                        System.out.println("2 - Place order");
                        System.out.println("3 - Orders list");
                        System.out.println("0 - Back");
                        System.out.print("Choose: ");
                        String cChoice = sc.nextLine();

                        if (cChoice.equals("0")) break;

                        if (cChoice.equals("1")) {
                            productService.listAll();

                        } else if (cChoice.equals("2")) {
                            ArrayList<Product> basket = new ArrayList<Product>();
                            while (true) {
                                System.out.print("Enter product id to add or 'done' to finish ordering: ");
                                String pid = sc.nextLine();
                                if (pid.equalsIgnoreCase("done")) break;

                                Product p = productService.findById(pid);
                                if (p == null) {
                                    System.out.println("No product with id " + pid);
                                } else {
                                    basket.add(p);
                                    System.out.println("Added: " + p.getName());
                                }
                            }

                            if (basket.isEmpty()) {
                                System.out.println("Basket is empty.");
                            } else {
                                System.out.print("Enter order id: ");
                                String oid = sc.nextLine();
                                Order o = new Order(oid, current, basket);
                                orderService.addOrder(o);
                                System.out.println("Order created:");
                                o.printReceipt();
                            }

                        } else if (cChoice.equals("3")) {
                            ArrayList<Order> all = orderService.getAllOrders();
                            boolean found = false;
                            for (int i = 0; i < all.size(); i++) {
                                Order o = all.get(i);
                                if (o.getCustomer() != null && email.equals(o.getCustomer().getEmail())) {
                                    o.printReceipt();
                                    System.out.println("---------------------------");
                                    found = true;
                                }
                            }
                            if (!found) System.out.println("No orders yet.");

                        } else {
                            System.out.println("Wrong choice.");
                        }
                    }
                }
            } else {
                System.out.println("Wrong choice.");
            }
        }

        sc.close();
    }

    private static void seedDemoData() {
        productService.addProduct(new Book("B1", "Clean Code", 45.50, 10, "Robert C. Martin"));
        productService.addProduct(new Electronics("E1", "Logitech Mouse", 19.99, 25, "Logitech"));
        productService.addProduct(new Clothing("C1", "T-Shirt", 12.00, 50, "M"));

        customerService.addCustomer(new Customer("Alice", "alice@mail.com", "1234", "customer", "Street 2"));
        customerService.addCustomer(new Customer("Bob", "bob@mail.com", "abcd", "customer", "Street 1"));
    }
}
