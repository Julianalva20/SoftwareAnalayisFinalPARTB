package villagerentals;

import java.util.ArrayList;
import java.util.Scanner;

public class MainForm {
    static ArrayList<Equipment> equipmentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Equipment");
            System.out.println("2. Display Equipment");
            System.out.println("3. Delete Equipment");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

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
                    System.exit(0);
            }
        }
    }

    static void addEquipment() {
        System.out.print("Enter ID: ");
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

        equipmentList.add(new Equipment(id, category, name, desc, cost));
        System.out.println("Equipment added!");
    }

    static void displayEquipment() {
        for (Equipment eq : equipmentList) {
            System.out.println(eq);
        }
    }

    static void deleteEquipment() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        Equipment found = null;

        for (Equipment eq : equipmentList) {
            if (eq.getEquipmentID() == id) {
                found = eq;
                break;
            }
        }

        if (found != null) {
            equipmentList.remove(found);
            System.out.println("Deleted.");
        } else {
            System.out.println("Not found.");
        }
    }
}
