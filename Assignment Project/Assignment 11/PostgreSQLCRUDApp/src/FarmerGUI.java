import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmerGUI extends JFrame {
    private JTextField nameField, farmSizeField, contactInfoField, idField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable farmerTable;
    private DefaultTableModel tableModel;
    private DatabaseManager dbManager;

    public FarmerGUI() {
        dbManager = new DatabaseManager();
        setTitle("Farmer Management");
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Farmer ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Farm Size:"));
        farmSizeField = new JTextField();
        inputPanel.add(farmSizeField);
        inputPanel.add(new JLabel("Contact Info:"));
        contactInfoField = new JTextField();
        inputPanel.add(contactInfoField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        loadButton = new JButton("Load");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(loadButton);

        // Table
        tableModel = new DefaultTableModel(new String[] { "Farmer ID", "Name", "Farm Size", "Contact Info" }, 0);
        farmerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(farmerTable);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int farmerid = Integer.parseInt(idField.getText().trim()); // Fixed Integer.parseInt
                    String name = nameField.getText();
                    double farmSize = Double.parseDouble(farmSizeField.getText().trim()); // Parse as double
                    String contactInfo = contactInfoField.getText();
                    dbManager.insertFarmer(farmerid, name, farmSize, contactInfo);
                    loadFarmers();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for Farm Size or Farmer ID.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int farmerID = Integer.parseInt(idField.getText().trim());
                    String name = nameField.getText();
                    double farmSize = Double.parseDouble(farmSizeField.getText().trim()); // Parse as double
                    String contactInfo = contactInfoField.getText();
                    dbManager.updateFarmer(farmerID, name, farmSize, contactInfo);
                    loadFarmers();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for Farm Size or Farmer ID.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int farmerID = Integer.parseInt(idField.getText().trim());
                    dbManager.deleteFarmer(farmerID);
                    loadFarmers();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Farmer ID.");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFarmers();
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Load farmers into the table
    private void loadFarmers() {
        try {
            ResultSet resultSet = dbManager.readFarmers();
            tableModel.setRowCount(0); // Clear existing data
            while (resultSet != null && resultSet.next()) {
                int farmerID = resultSet.getInt("farmerid");
                String name = resultSet.getString("name");
                double farmSize = resultSet.getDouble("farmsize");
                String contactInfo = resultSet.getString("contactinfo");
                tableModel.addRow(new Object[] { farmerID, name, farmSize, contactInfo });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FarmerGUI();
    }
}
