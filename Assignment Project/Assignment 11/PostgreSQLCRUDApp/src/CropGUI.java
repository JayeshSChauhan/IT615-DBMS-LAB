import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CropGUI extends JFrame {
    private JTextField cropIDField, nameField, growthPeriodField, yieldField, typeIDField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable cropTable;
    private DefaultTableModel tableModel;
    private DatabaseManager dbManager;

    public CropGUI() {
        dbManager = new DatabaseManager();
        setTitle("Crop Management");
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Crop ID:"));
        cropIDField = new JTextField();
        inputPanel.add(cropIDField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Growth Period:"));
        growthPeriodField = new JTextField();
        inputPanel.add(growthPeriodField);
        inputPanel.add(new JLabel("Yield:"));
        yieldField = new JTextField();
        inputPanel.add(yieldField);
        inputPanel.add(new JLabel("Type ID:"));
        typeIDField = new JTextField();
        inputPanel.add(typeIDField);

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
        tableModel = new DefaultTableModel(new String[] { "Crop ID", "Name", "Growth Period", "Yield", "Type ID" }, 0);
        cropTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cropTable);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cropID = Integer.parseInt(cropIDField.getText().trim());
                    String name = nameField.getText();
                    String growthPeriod = growthPeriodField.getText();
                    double yield = Double.parseDouble(yieldField.getText().trim());
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    dbManager.insertCrop(cropID, name, growthPeriod, yield, typeID);
                    loadCrops();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for Crop ID, Yield, and Type ID.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cropID = Integer.parseInt(cropIDField.getText().trim());
                    String name = nameField.getText();
                    String growthPeriod = growthPeriodField.getText();
                    double yield = Double.parseDouble(yieldField.getText().trim());
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    dbManager.updateCrop(cropID, name, growthPeriod, yield, typeID);
                    loadCrops();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for Crop ID, Yield, and Type ID.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cropID = Integer.parseInt(cropIDField.getText().trim());
                    dbManager.deleteCrop(cropID);
                    loadCrops();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Crop ID.");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCrops();
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Load crops into the table
    private void loadCrops() {
        try {
            ResultSet resultSet = dbManager.readCrops();
            tableModel.setRowCount(0); // Clear existing data
            while (resultSet != null && resultSet.next()) {
                int cropID = resultSet.getInt("cropid");
                String name = resultSet.getString("name");
                String growthPeriod = resultSet.getString("growthperiod");
                double yield = resultSet.getDouble("yield");
                int typeID = resultSet.getInt("typeid");
                tableModel.addRow(new Object[] { cropID, name, growthPeriod, yield, typeID });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CropGUI();
    }
}
