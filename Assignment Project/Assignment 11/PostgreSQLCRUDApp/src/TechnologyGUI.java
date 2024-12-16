import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnologyGUI extends JFrame {
    private JTextField technologyIDField, nameField, purposeField, typeIDField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable technologyTable;
    private DefaultTableModel tableModel;
    private DatabaseManager dbManager;

    public TechnologyGUI() {
        dbManager = new DatabaseManager();
        setTitle("Technology Management");
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Technology ID:"));
        technologyIDField = new JTextField();
        inputPanel.add(technologyIDField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Purpose:"));
        purposeField = new JTextField();
        inputPanel.add(purposeField);
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
        tableModel = new DefaultTableModel(new String[] { "Technology ID", "Name", "Purpose", "Type ID" }, 0);
        technologyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(technologyTable);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int technologyID = Integer.parseInt(technologyIDField.getText().trim());
                    String name = nameField.getText();
                    String purpose = purposeField.getText();
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    dbManager.insertTechnology(technologyID, name, purpose, typeID);
                    loadTechnologies();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for Technology ID and Type ID.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int technologyID = Integer.parseInt(technologyIDField.getText().trim());
                    String name = nameField.getText();
                    String purpose = purposeField.getText();
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    dbManager.updateTechnology(technologyID, name, purpose, typeID);
                    loadTechnologies();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for Technology ID and Type ID.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int technologyID = Integer.parseInt(technologyIDField.getText().trim());
                    dbManager.deleteTechnology(technologyID);
                    loadTechnologies();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Technology ID.");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTechnologies();
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Load technologies into the table
    private void loadTechnologies() {
        try {
            ResultSet resultSet = dbManager.readTechnologies();
            tableModel.setRowCount(0); // Clear existing data
            while (resultSet != null && resultSet.next()) {
                int technologyID = resultSet.getInt("technologyid");
                String name = resultSet.getString("name");
                String purpose = resultSet.getString("purpose");
                int typeID = resultSet.getInt("typeid");
                tableModel.addRow(new Object[] { technologyID, name, purpose, typeID });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TechnologyGUI();
    }
}
