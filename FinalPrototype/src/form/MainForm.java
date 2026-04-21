package form;

import java.util.ArrayList;
import Manager.CustomerManager;
import java.util.Scanner;

import Manager.FileHandler;
import villagerentals.Customer;
import villagerentals.Equipment;
import villagerentals.Rental;

public class MainForm {
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Equipment> equipmentList = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	customers = CustomerManager.loadCustomers();
    	rentals = FileHandler.loadRentals("rentals.txt");

        while (true) {
            System.out.println("\n=== Village Rentals System ===");
            System.out.println("\n1. Add Equipment");
            System.out.println("2. Display Equipment");
            System.out.println("3. Delete Equipment");
            System.out.println("4. Process Rental");
            System.out.println("5. Display Rentals");
            System.out.println("6. Exit");
            System.out.print("\nEnter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    displayEquipment();
                    break;
                case 3:
                    deleteEquipment();
                    break;
                case 4:
                    processRental();
                    break;
                case 5:
                    displayRentals();
                    break;
                case 6:
                    FileHandler.saveRentals(rentals, "rentals.txt");
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addEquipment() {
        System.out.print("Enter Equipment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter Daily Cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        equipmentList.add(new Equipment(id, category, name, desc, cost));
        System.out.println("Equipment added!");
    }

    static void displayEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment found.");
            return;
        }

        for (Equipment eq : equipmentList) {
            System.out.println(eq);
        }
    }

    static void deleteEquipment() {
        System.out.print("Enter Equipment ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Equipment found = null;

        for (Equipment eq : equipmentList) {
            if (eq.getEquipmentID() == id) {
                found = eq;
                break;
            }
        }

        if (found != null) {
            equipmentList.remove(found);
            System.out.println("Equipment deleted.");
        } else {
            System.out.println("Equipment not found.");
        }
    }

    static void processRental() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }

        if (equipmentList.isEmpty()) {
            System.out.println("No equipment available.");
            return;
        }

        System.out.print("Enter Rental ID: ");
        String rentalId = scanner.nextLine();

        // check duplicate rental ID
        for (Rental r : rentals) {
            if (r.getRentalId().equalsIgnoreCase(rentalId)) {
                System.out.println("Rental ID already exists.");
                return;
            }
        }

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer selectedCustomer = null;
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                selectedCustomer = c;
                break;
            }
        }

        if (selectedCustomer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter Equipment ID: ");
        int equipmentId = scanner.nextInt();
        scanner.nextLine();

        Equipment selectedEquipment = null;
        for (Equipment e : equipmentList) {
            if (e.getEquipmentID() == equipmentId) {
                selectedEquipment = e;
                break;
            }
        }

        if (selectedEquipment == null) {
            System.out.println("Equipment not found.");
            return;
        }

        System.out.print("Enter Rental Date (YYYY-MM-DD): ");
        String rentalDate = scanner.nextLine();

        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        System.out.print("Enter Total Cost: ");
        double totalCost = scanner.nextDouble();
        scanner.nextLine();

        Rental rental = new Rental(
            rentalId,
            String.valueOf(selectedCustomer.getCustomerId()),
            String.valueOf(selectedEquipment.getEquipmentID()),
            rentalDate,
            returnDate,
            totalCost
        );

        rentals.add(rental);
        FileHandler.saveRentals(rentals, "rentals.txt");

        System.out.println("Rental processed successfully!");
        System.out.println("Total Cost: $" + totalCost);
    }

    static void displayRentals() {
        if (rentals.isEmpty()) {
            System.out.println("No rentals found.");
            return;
        }

        for (Rental r : rentals) {
            System.out.println(r);
        }
    }
}
