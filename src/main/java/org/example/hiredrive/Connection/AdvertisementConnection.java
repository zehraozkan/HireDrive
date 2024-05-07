package org.example.hiredrive.Connection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AdvertisementConnection {
    private static final Properties properties;

    static {
        try {
            properties = DatabaseConfig.loadProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties", e);
        }
    }

    private static final String url = properties.getProperty("db.url");
    private static final String username = properties.getProperty("db.username");
    private static final String password = properties.getProperty("db.password");


    /**
     *
     * @param ownerId
     * @param addTitle
     * @param cargoType
     * @param addContent
     * @param dueDate
     * creates a new advertisement
     */
    public static void addAdvertisement(int ownerId, String addTitle, String cargoType, String addContent, Date dueDate) {
        String sql = "INSERT INTO advertisement (owner_id, add_title, cargo_type, add_content, due_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ownerId);
            pstmt.setString(2, addTitle);
            pstmt.setString(3, cargoType);
            pstmt.setString(4, addContent);
            pstmt.setDate(5, new java.sql.Date(dueDate.getTime()));

            pstmt.executeUpdate();

            System.out.println("Advertisement added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void deleteAdvertisement(int advertisementId) {
        String sql = "DELETE FROM advertisement WHERE advert_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, advertisementId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Advertisement with ID " + advertisementId + " deleted successfully.");
            } else {
                System.out.println("No advertisement found with ID " + advertisementId + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static String listAdvertisementsByOwner(int ownerId) {
        StringBuilder resultString = new StringBuilder();
        String sql = "SELECT * FROM advertisement WHERE owner_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ownerId);
            ResultSet rs = pstmt.executeQuery();

            // Append header to the result string
            resultString.append("Advertisement ID | Owner ID | Title | Cargo Type | Content | Due Date\n");
            resultString.append("-----------------------------------------\n");

            // Iterate through the result set and append each advertisement to the result string
            while (rs.next()) {
                int advertId = rs.getInt("advert_id");
                String addTitle = rs.getString("add_title");
                String cargoType = rs.getString("cargo_type");
                String addContent = rs.getString("add_content");
                String dueDate = rs.getString("due_date");

                // Append advertisement details to the result string
                resultString.append(String.format("%-16d | %-9d | %-5s | %-10s | %-7s | %-10s%n",
                        advertId, ownerId, addTitle, cargoType, addContent, dueDate));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Return the result string
        return resultString.toString();
    }

    //TODO increment the request in the advertisement
    public static void sendJobRequestToAdd(int driver_id, int add_id){
        String sql = "INSERT INTO jobRequests (driver_id, add_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, AdvertisementConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, driver_id);
            pstmt.setInt(2, add_id);

            pstmt.executeUpdate();

            System.out.println("Request sent successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
