package manager;

import model.Customer;
import java.io.*;
 
public class CustomerManager {
 
    private static final String FILE_NAME = "customers.txt";
 
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
            System.out.println("Error writing to file.");
        }
    }
 
    public static void getAllCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
 
            String line;
            while ((line = reader.readLine()) != null) {
 
                String[] data = line.split(",");
 
                Customer c = new Customer(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        Boolean.parseBoolean(data[5])
                );
 
                System.out.println(c);
            }
 
        } catch (IOException e) {
            System.out.println("No customers found.");
        }
    }
 
    public static boolean idExists(int idToCheck) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
 
            String line;
            while ((line = reader.readLine()) != null) {
 
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
 
                if (id == idToCheck) {
                    return true;
                }
            }
 
        } catch (IOException e) {
            return false; // file doesn't exist yet
        }
 
        return false;
    }
}


