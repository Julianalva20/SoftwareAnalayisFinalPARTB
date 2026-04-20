package villageRentals;

import model.Customer;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {

    private final String FILE_NAME = "customers.txt";

    // ✅ ADD CUSTOMER (saves to file)
    public void addCustomer(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write(customer.getCustomerId() + "," +
                         customer.getFirstName() + "," +
                         customer.getLastName() + "," +
                         customer.getPhone() + "," +
                         customer.getEmail() + "," +
                         customer.isBanned());

            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving customer.");
        }
    }

    public void getAllCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                int id = lineScanner.nextInt();
                String firstName = lineScanner.next();
                String lastName = lineScanner.next();
                String phone = lineScanner.next();
                String email = lineScanner.next();
                boolean isBanned = lineScanner.nextBoolean();

                Customer c = new Customer(id, firstName, lastName, phone, email, isBanned);
                System.out.println(c);

                lineScanner.close();
            }

        } catch (IOException e) {
            System.out.println("No customers found.");
        }
    }

    
}




