import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/G2_SARM"; // Change to your database name
    private static final String USER = "postgres"; // Change to your username
    private static final String PASSWORD = "admin"; // Change to your password

    // Connect to the database
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Insert farmer (farmerid will now be entered manually by the user)
    public void insertFarmer(int farmerid, String name, double farmSize, String contactInfo) {
        // SQL query to insert farmer
        String insertSQL = "INSERT INTO \"G2SARM\".farmer (farmerid, name, farmsize, contactinfo) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, farmerid); // farmerid is manually entered by the user
            pstmt.setString(2, name); // name of the farmer
            pstmt.setDouble(3, farmSize); // farmSize, should be treated as a double
            pstmt.setString(4, contactInfo); // contactInfo (contactinfo in this case)

            pstmt.executeUpdate();
            System.out.println("Farmer inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     // 1. Insert Crop
     public void insertCrop(int cropID, String name, String growthPeriod, double yield, int typeID) {
        String insertSQL = "INSERT INTO \"G2SARM\".crop (cropid, name, growthperiod, yield, typeid) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, cropID);
            pstmt.setString(2, name);
            pstmt.setString(3, growthPeriod);
            pstmt.setDouble(4, yield);
            pstmt.setInt(5, typeID);
            pstmt.executeUpdate();
            System.out.println("Crop inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Insert CropType
    public void insertCropType(int typeID, String type, String additionalInfo) {
        String insertSQL = "INSERT INTO \"G2SARM\".croptype (typeid, type, additionalinfo) VALUES (?, ?, ?)";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, typeID);
            pstmt.setString(2, type);
            pstmt.setString(3, additionalInfo);
            pstmt.executeUpdate();
            System.out.println("Crop Type inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Insert Technology
    public void insertTechnology(int technologyID, String name, String purpose, int typeID) {
        String insertSQL = "INSERT INTO \"G2SARM\".technology (technologyid, name, purpose, typeid) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, technologyID);
            pstmt.setString(2, name);
            pstmt.setString(3, purpose);
            pstmt.setInt(4, typeID);
            pstmt.executeUpdate();
            System.out.println("Technology inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Insert TechnologyType
    public void insertTechnologyType(int typeID, String type, String additionalInfo) {
        String insertSQL = "INSERT INTO \"G2SARM\".technologytype (typeid, type, additionalinfo) VALUES (?, ?, ?)";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, typeID);
            pstmt.setString(2, type);
            pstmt.setString(3, additionalInfo);
            pstmt.executeUpdate();
            System.out.println("Technology Type inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all farmers
    public ResultSet readFarmers() {
        String selectSQL = "SELECT * FROM \"G2SARM\".farmer";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            return pstmt.executeQuery(); // Execute query and return result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet readCrops() {
        String selectSQL = "SELECT * FROM \"G2SARM\".crop";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 6. Read all CropTypes
    public ResultSet readCropTypes() {
        String selectSQL = "SELECT * FROM \"G2SARM\".croptype";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 7. Read all Technologies
    public ResultSet readTechnologies() {
        String selectSQL = "SELECT * FROM \"G2SARM\".technology";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 8. Read all TechnologyTypes
    public ResultSet readTechnologyTypes() {
        String selectSQL = "SELECT * FROM \"G2SARM\".technologytype";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update farmer details
    public void updateFarmer(int farmerID, String name, double farmSize, String contactInfo) {
        String updateSQL = "UPDATE \"G2SARM\".farmer SET name = ?, farmsize = ?, contactinfo = ? WHERE farmerid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, name); // Set name of the farmer
            pstmt.setDouble(2, farmSize); // Set farmSize
            pstmt.setString(3, contactInfo); // Set contactInfo (contactinfo)
            pstmt.setInt(4, farmerID); // Set farmerID (the record to be updated)

            pstmt.executeUpdate();
            System.out.println("Farmer updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Crop
    public void updateCrop(int cropID, String name, String growthPeriod, double yield, int typeID) {
        String updateSQL = "UPDATE \"G2SARM\".crop SET name = ?, growthperiod = ?, yield = ?, typeid = ? WHERE cropid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, growthPeriod);
            pstmt.setDouble(3, yield);
            pstmt.setInt(4, typeID);
            pstmt.setInt(5, cropID);
            pstmt.executeUpdate();
            System.out.println("Crop updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update CropType
    public void updateCropType(int typeID, String type, String additionalInfo) {
        String updateSQL = "UPDATE \"G2SARM\".croptype SET type = ?, additionalinfo = ? WHERE typeid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, type);
            pstmt.setString(2, additionalInfo);
            pstmt.setInt(3, typeID);
            pstmt.executeUpdate();
            System.out.println("Crop Type updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Technology
    public void updateTechnology(int technologyID, String name, String purpose, int typeID) {
        String updateSQL = "UPDATE \"G2SARM\".technology SET name = ?, purpose = ?, typeid = ? WHERE technologyid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, purpose);
            pstmt.setInt(3, typeID);
            pstmt.setInt(4, technologyID);
            pstmt.executeUpdate();
            System.out.println("Technology updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update TechnologyType
    public void updateTechnologyType(int typeID, String type, String additionalInfo) {
        String updateSQL = "UPDATE \"G2SARM\".technologytype SET type = ?, additionalinfo = ? WHERE typeid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, type);
            pstmt.setString(2, additionalInfo);
            pstmt.setInt(3, typeID);
            pstmt.executeUpdate();
            System.out.println("Technology Type updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete farmer based on farmerID
    public void deleteFarmer(int farmerID) {
        String deleteSQL = "DELETE FROM \"G2SARM\".farmer WHERE farmerid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, farmerID); // Set the farmerID of the record to be deleted

            pstmt.executeUpdate();
            System.out.println("Farmer deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Crop
    public void deleteCrop(int cropID) {
        String deleteSQL = "DELETE FROM \"G2SARM\".crop WHERE cropid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, cropID);
            pstmt.executeUpdate();
            System.out.println("Crop deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete CropType
    public void deleteCropType(int typeID) {
        String deleteSQL = "DELETE FROM \"G2SARM\".croptype WHERE typeid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, typeID);
            pstmt.executeUpdate();
            System.out.println("Crop Type deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Technology
    public void deleteTechnology(int technologyID) {
        String deleteSQL = "DELETE FROM \"G2SARM\".technology WHERE technologyid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, technologyID);
            pstmt.executeUpdate();
            System.out.println("Technology deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete TechnologyType
    public void deleteTechnologyType(int typeID) {
        String deleteSQL = "DELETE FROM \"G2SARM\".technologytype WHERE typeid = ?";
        try (Connection connection = connect();
                PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, typeID);
            pstmt.executeUpdate();
            System.out.println("Technology Type deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a farmer by farmerid
    public ResultSet getFarmerById(int farmerID) {
        String selectSQL = "SELECT * FROM \"G2SARM\".farmer WHERE farmerid = ?";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setInt(1, farmerID); // Set farmerID for filtering
            return pstmt.executeQuery(); // Execute query and return result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     // 2. Get a crop by cropid
     public ResultSet getCropById(int cropID) {
        String selectSQL = "SELECT * FROM \"G2SARM\".crop WHERE cropid = ?";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setInt(1, cropID); // Set cropID for filtering
            return pstmt.executeQuery(); // Execute query and return result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. Get a crop type by typeid
    public ResultSet getCropTypeById(int typeID) {
        String selectSQL = "SELECT * FROM \"G2SARM\".croptype WHERE typeid = ?";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setInt(1, typeID); // Set typeID for filtering
            return pstmt.executeQuery(); // Execute query and return result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Get a technology by technologyid
    public ResultSet getTechnologyById(int technologyID) {
        String selectSQL = "SELECT * FROM \"G2SARM\".technology WHERE technologyid = ?";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setInt(1, technologyID); // Set technologyID for filtering
            return pstmt.executeQuery(); // Execute query and return result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 5. Get a technology type by typeid
    public ResultSet getTechnologyTypeById(int typeID) {
        String selectSQL = "SELECT * FROM \"G2SARM\".technologytype WHERE typeid = ?";
        try {
            Connection connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setInt(1, typeID); // Set typeID for filtering
            return pstmt.executeQuery(); // Execute query and return result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}