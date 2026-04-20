package villagerentals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalForm {

    private ArrayList<Client> clients;
    private ArrayList<Equipment> equipmentList;
    private ArrayList<Rental> rentals;
    private Scanner scanner;

    public RentalForm(ArrayList<Client> clients, ArrayList<Equipment> equipmentList, ArrayList<Rental> rentals) {
        this.clients = clients;
        this.equipmentList = equipmentList;
        this.rentals = rentals;
        this.scanner = new Scanner(System.in);

        processRental(); // directly run in console
    }

    private void processRental() {
        System.out.println("\n=== Process Rental ===");

        System.out.print("Enter Rental ID: ");
        String rentalId = scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter Equipment ID: ");
        String equipmentId = scanner.nextLine();

        System.out.print("Enter Rental Date (YYYY-MM-DD): ");
        String rentalDateText = scanner.nextLine();

        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDateText = scanner.nextLine();

        Client client = findClientById(customerId);
        if (client == null) {
            System.out.println("Client not found.");
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

            // Check duplicate rental ID
            for (Rental r : rentals) {
                if (r.getRentalId().equalsIgnoreCase(rentalId)) {
                    System.out.println("Rental ID already exists.");
                    return;
                }
            }

            Rental rental = new Rental(
                    rentalId,
                    customerId,
                    equipmentId,
                    rentalDateText,
                    returnDateText,
                    totalCost
            );

            rentals.add(rental);
            FileHandler.saveRentals(rentals, "rentals.txt");

            System.out.println("Rental processed successfully!");
            System.out.println("Total Cost: $" + totalCost);

        } catch (Exception e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    private Client findClientById(String customerId) {
        for (Client client : clients) {
            if (client.getCustomerId().equalsIgnoreCase(customerId)) {
                return client;
            }
        }
        return null;
    }

    private Equipment findEquipmentById(String equipmentId) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentId().equalsIgnoreCase(equipmentId)) {
                return equipment;
            }
        }
        return null;
    }
}
