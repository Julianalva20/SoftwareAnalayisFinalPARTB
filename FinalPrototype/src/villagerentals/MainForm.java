package villagerentals;

import java.util.ArrayList;
import java.util.Scanner;

public class MainForm {
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Equipment> equipmentList = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        rentals = FileHandler.loadRentals("rentals.txt");

        while (true) {
            System.out.println("\n=== Village Rentals System ===");
            System.out.println("1. Add Equipment");
            System.out.println("2. Display Equipment");
            System.out.println("3. Delete Equipment");
            System.out.println("4. Process Rental");
            System.out.println("5. Display Rentals");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

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
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
            return;
        }

        if (equipmentList.isEmpty()) {
            System.out.println("No equipment available.");
            return;
        }

        System.out.print("Enter Client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        Client selectedClient = null;
        for (Client c : clients) {
            if (c.getClientID() == clientId) {
                selectedClient = c;
                break;
            }
        }

        if (selectedClient == null) {
            System.out.println("Client not found.");
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

        System.out.print("Enter Number of Rental Days: ");
        int days = scanner.nextInt();
        scanner.nextLine();

        double totalCost = selectedEquipment.getDailyRentalCost() * days;

        Rental rental = new Rental(selectedClient, selectedEquipment, days, totalCost);
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
