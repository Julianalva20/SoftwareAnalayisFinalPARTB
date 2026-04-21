package Manager;

import villagerentals.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {

    private final static String FILE_NAME = "customers.txt";

    // ✅ ADD CUSTOMER (saves to file)
    public static void addCustomer(Customer customer) {
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

    public static void getAllCustomers() {
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
    
    public static ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 6) {
                    int id = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String phone = data[3];
                    String email = data[4];
                    boolean isBanned = Boolean.parseBoolean(data[5]);

                    customers.add(new Customer(id, firstName, lastName, phone, email, isBanned));
                }
            }

        } catch (IOException e) {
            System.out.println("No customers found.");
        }

        return customers;
    }

	
    public static boolean idExists(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && Integer.parseInt(data[0]) == id) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

	}

    





