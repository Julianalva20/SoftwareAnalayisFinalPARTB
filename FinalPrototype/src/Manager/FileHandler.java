package form;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import Manager.FileHandler;
import villagerentals.Customer;
import villagerentals.Equipment;
import villagerentals.Rental;

public class RentalForm {

    private ArrayList<Customer> customers;
    private ArrayList<Equipment> equipmentList;
    private ArrayList<Rental> rentals;
    private Scanner scanner;

    public RentalForm(ArrayList<Customer> customers, ArrayList<Equipment> equipmentList, ArrayList<Rental> rentals) {
        this.customers = customers;
        this.equipmentList = equipmentList;
        this.rentals = rentals;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        processRental();
    }

    private void processRental() {
        System.out.println("\n=== Process Rental ===");

        System.out.print("Enter Rental ID: ");
        String rentalId = scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Equipment ID: ");
        int equipmentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Rental Date (YYYY-MM-DD): ");
        String rentalDateText = scanner.nextLine();

        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDateText = scanner.nextLine();

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        Equipment equipment = findEquipmentById(equipmentId);
        if (equipment == null) {
            System.out.println("Equipment not found.");
            return;
        }

        try {
            LocalDate rentalDate = LocalDate.parse(rentalDateText);
            LocalDate returnDate = LocalDate.parse(returnDateText);

            long days = ChronoUnit.DAYS.between(rentalDate, returnDate);

            if (days <= 0) {
                System.out.println("Return date must be after rental date.");
                return;
            }

            double totalCost = days * equipment.getDailyRentalCost();

            for (Rental r : rentals) {
                if (r.getRentalId().equalsIgnoreCase(rentalId)) {
                    System.out.println("Rental ID already exists.");
                    return;
                }
            }

            Rental rental = new Rental(
                rentalId,
                String.valueOf(customerId),
                String.valueOf(equipmentId),
                rentalDateText,
                returnDateText,
                totalCost
            );

            rentals.add(rental);
            FileHandler.saveRentals(rentals, "rentals.txt");

            System.out.println("Rental processed successfully!");
            System.out.println("Total Cost: $" + totalCost);

        } catch (Exception e) {
            System.out.println("Invalid input or date format. Use YYYY-MM-DD.");
        }
    }

    private Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private Equipment findEquipmentById(int equipmentId) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentID() == equipmentId) {
                return equipment;
            }
        }
        return null;
    }
}
