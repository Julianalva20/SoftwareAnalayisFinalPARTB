package villagerentals;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RentalForm extends JFrame {
    private JTextField txtRentalId;
    private JTextField txtCustomerId;
    private JTextField txtEquipmentId;
    private JTextField txtRentalDate;
    private JTextField txtReturnDate;
    private JTextField txtTotalCost;

    private JButton btnCalculate;
    private JButton btnProcess;

    private ArrayList<Client> clients;
    private ArrayList<Equipment> equipmentList;
    private ArrayList<Rental> rentals;

    public RentalForm(ArrayList<Client> clients, ArrayList<Equipment> equipmentList, ArrayList<Rental> rentals) {
        this.clients = clients;
        this.equipmentList = equipmentList;
        this.rentals = rentals;

        setTitle("Process Rental");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        txtRentalId = new JTextField();
        txtCustomerId = new JTextField();
        txtEquipmentId = new JTextField();
        txtRentalDate = new JTextField("2026-04-18");
        txtReturnDate = new JTextField("2026-04-19");
        txtTotalCost = new JTextField();
        txtTotalCost.setEditable(false);

        btnCalculate = new JButton("Calculate Cost");
        btnProcess = new JButton("Process Rental");

        add(new JLabel("Rental ID:"));
        add(txtRentalId);

        add(new JLabel("Customer ID:"));
        add(txtCustomerId);

        add(new JLabel("Equipment ID:"));
        add(txtEquipmentId);

        add(new JLabel("Rental Date (YYYY-MM-DD):"));
        add(txtRentalDate);

        add(new JLabel("Return Date (YYYY-MM-DD):"));
        add(txtReturnDate);

        add(new JLabel("Total Cost:"));
        add(txtTotalCost);

        add(btnCalculate);
        add(btnProcess);

        btnCalculate.addActionListener(e -> calculateCost());
        btnProcess.addActionListener(e -> processRental());

        setVisible(true);
    }

    private void calculateCost() {
        String equipmentId = txtEquipmentId.getText().trim();
        String rentalDateText = txtRentalDate.getText().trim();
        String returnDateText = txtReturnDate.getText().trim();

        Equipment equipment = findEquipmentById(equipmentId);

        if (equipment == null) {
            JOptionPane.showMessageDialog(this, "Equipment not found.");
            return;
        }

        try {
            LocalDate rentalDate = LocalDate.parse(rentalDateText);
            LocalDate returnDate = LocalDate.parse(returnDateText);

            long days = ChronoUnit.DAYS.between(rentalDate, returnDate);

            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Return date must be after rental date.");
                return;
            }

            double totalCost = days * equipment.getDailyRentalCost();
            txtTotalCost.setText(String.valueOf(totalCost));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.");
        }
    }

    private void processRental() {
        String rentalId = txtRentalId.getText().trim();
        String customerId = txtCustomerId.getText().trim();
        String equipmentId = txtEquipmentId.getText().trim();
        String rentalDate = txtRentalDate.getText().trim();
        String returnDate = txtReturnDate.getText().trim();
        String totalCostText = txtTotalCost.getText().trim();

        if (rentalId.isEmpty() || customerId.isEmpty() || equipmentId.isEmpty()
                || rentalDate.isEmpty() || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (findClientById(customerId) == null) {
            JOptionPane.showMessageDialog(this, "Client not found.");
            return;
        }

        if (findEquipmentById(equipmentId) == null) {
            JOptionPane.showMessageDialog(this, "Equipment not found.");
            return;
        }

        if (totalCostText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please calculate the cost first.");
            return;
        }

        for (Rental rental : rentals) {
            if (rental.getRentalId().equalsIgnoreCase(rentalId)) {
                JOptionPane.showMessageDialog(this, "Rental ID already exists.");
                return;
            }
        }

        double totalCost = Double.parseDouble(totalCostText);

        Rental rental = new Rental(rentalId, customerId, equipmentId, rentalDate, returnDate, totalCost);
        rentals.add(rental);

        FileHandler.saveRentals(rentals, "rentals.txt");

        JOptionPane.showMessageDialog(this, "Rental processed successfully.");
        clearFields();
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

    private void clearFields() {
        txtRentalId.setText("");
        txtCustomerId.setText("");
        txtEquipmentId.setText("");
        txtRentalDate.setText("");
        txtReturnDate.setText("");
        txtTotalCost.setText("");
    }
}
