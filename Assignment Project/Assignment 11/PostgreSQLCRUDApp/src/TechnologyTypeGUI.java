import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnologyTypeGUI extends JFrame {
    private JTextField typeIDField, typeField, additionalInfoField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable technologyTypeTable;
    private DefaultTableModel tableModel;
    private DatabaseManager dbManager;

    public TechnologyTypeGUI() {
        dbManager = new DatabaseManager();
        setTitle("Technology Type Management");
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Type ID:"));
        typeIDField = new JTextField();
        inputPanel.add(typeIDField);
        inputPanel.add(new JLabel("Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);
        inputPanel.add(new JLabel("Additional Info:"));
        additionalInfoField = new JTextField();
        inputPanel.add(additionalInfoField);

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
        tableModel = new DefaultTableModel(new String[] { "Type ID", "Type", "Additional Info" }, 0);
        technologyTypeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(technologyTypeTable);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    String type = typeField.getText();
                    String additionalInfo = additionalInfoField.getText();
                    dbManager.insertTechnologyType(typeID, type, additionalInfo);
                    loadTechnologyTypes();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for Type ID.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    String type = typeField.getText();
                    String additionalInfo = additionalInfoField.getText();
                    dbManager.updateTechnologyType(typeID, type, additionalInfo);
                    loadTechnologyTypes();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for Type ID.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int typeID = Integer.parseInt(typeIDField.getText().trim());
                    dbManager.deleteTechnologyType(typeID);
                    loadTechnologyTypes();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Type ID.");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTechnologyTypes();
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Load technology types into the table
    private void loadTechnologyTypes() {
        try {
            ResultSet resultSet = dbManager.readTechnologyTypes();
            tableModel.setRowCount(0); // Clear existing data
            while (resultSet != null && resultSet.next()) {
                int typeID = resultSet.getInt("typeid");
                String type = resultSet.getString("type");
                String additionalInfo = resultSet.getString("additionalinfo");
                tableModel.addRow(new Object[] { typeID, type, additionalInfo });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TechnologyTypeGUI();
    }
}
