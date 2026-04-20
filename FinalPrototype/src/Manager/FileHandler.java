package villagerentals;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void saveRentals(ArrayList<Rental> rentals, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Rental rental : rentals) {
                writer.println(rental.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving rentals: " + e.getMessage());
        }
    }

    public static ArrayList<Rental> loadRentals(String fileName) {
        ArrayList<Rental> rentals = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return rentals;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 6) {
                    String rentalId = data[0];
                    String customerId = data[1];
                    String equipmentId = data[2];
                    String rentalDate = data[3];
                    String returnDate = data[4];
                    double totalCost = Double.parseDouble(data[5]);

                    rentals.add(new Rental(rentalId, customerId, equipmentId, rentalDate, returnDate, totalCost));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading rentals: " + e.getMessage());
        }

        return rentals;
    }
}