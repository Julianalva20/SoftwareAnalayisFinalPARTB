package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;

import java.util.Scanner;

public class MainMenu {
 
    private Scanner scanner = new Scanner(System.in);
 
    public static void main(String[] args) {
        new MainMenu().start();
    }
 
    public void start() {
        boolean running = true;
 
        while (running) {
            System.out.println("\n===== VILLAGE RENTALS SYSTEM =====");
            System.out.println("1. Customer Menu");
            System.out.println("2. Equipment Menu");
            System.out.println("3. Rental Menu");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
 
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }
 
            switch (choice) {
                case 1:
                    new CustomerForm().start();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
