package main;

import java.util.ArrayList;
import java.util.Scanner;

import manager.CustomerManager;
import model.Customer;

public class CustomerForm {

    private Scanner scanner;

    public CustomerForm() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomersFromFile();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Customer System ===");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customers (from file)");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }


        private void addCustomer() {
            try {
                System.out.print("Customer ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                // CHECK FOR DUPLICATE ID
                if (CustomerManager.idExists(id)) {
                    System.out.println("❌ Error: Customer ID already exists!");
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
                boolean isBanned = Boolean.parseBoolean(scanner.nextLine());

                Customer c = new Customer(id, fn, ln, phone, email, isBanned);

                CustomerManager.addCustomer(c);

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }

    private void viewCustomersFromFile() {
        System.out.println("\n--- Customers from File ---");
        CustomerManager.getAllCustomers(); //READS FILE
    }

    public static void main(String[] args) {
        CustomerForm ui = new CustomerForm();
        ui.start();
    }
}

