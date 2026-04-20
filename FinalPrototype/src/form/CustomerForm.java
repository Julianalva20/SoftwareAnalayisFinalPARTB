package form;

import java.util.Scanner;
import manager.CustomerManager;
import model.Customer;
 
public class CustomerForm {
 
    private Scanner scanner = new Scanner(System.in);
 
    public void start() {
        while (true) {
            printMenu();
 
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }
 
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomersFromFile();
                    break;
                case 0:
                    return; // back to MainMenu
                default:
                    System.out.println("Invalid option");
            }
        }
    }
 
    private void printMenu() {
        System.out.println("\n=== Customer System ===");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customers");
        System.out.println("0. Back");
        System.out.print("Choose: ");
    }
 
    private void addCustomer() {
        try {
            System.out.print("Customer ID: ");
            int id = Integer.parseInt(scanner.nextLine());
 
            if (CustomerManager.idExists(id)) {
                System.out.println("❌ ID already exists!");
                return;
            }
 
            System.out.print("First Name: ");
            String fn = scanner.nextLine();
 
            System.out.print("Last Name: ");
            String ln = scanner.nextLine();
 
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
 
            System.out.print("Email: ");
            String email = scanner.nextLine();
 
            System.out.print("Is Banned (true/false): ");
            boolean banned = Boolean.parseBoolean(scanner.nextLine());
 
            Customer c = new Customer(id, fn, ln, phone, email, banned);
            CustomerManager.addCustomer(c);
 
            System.out.println("✅ Customer added!");
 
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
 
    private void viewCustomersFromFile() {
        System.out.println("\n--- Customers ---");
        CustomerManager.getAllCustomers();
    }
}
