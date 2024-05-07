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
    public static void listAdvertisementsByOwner(int ownerId) {
        String sql = "SELECT * FROM advertisement WHERE owner_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ownerId);

            ResultSet rs = pstmt.executeQuery();

            // Print header
            System.out.println("Advertisement ID | Owner ID | Title | Cargo Type | Content | Due Date");
            System.out.println("-----------------------------------------");

            // Iterate through the result set and print each advertisement
            while (rs.next()) {
                int advertId = rs.getInt("advert_id");
                String addTitle = rs.getString("add_title");
                String cargoType = rs.getString("cargo_type");
                String addContent = rs.getString("add_content");
                String dueDate = rs.getString("due_date");

                // Print advertisement details
                System.out.printf("%-16d | %-9d | %-5s | %-10s | %-7s | %-10s%n",
                        advertId, ownerId, addTitle, cargoType, addContent, dueDate);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //TODO increment the request in the advertisement
    public static void sendJobRequest(int driver_id, int add_id){
        String sql = "INSERT INTO jobRequests (driver_id, add_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, AdvertisementConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driver_id);
            pstmt.setInt(2, add_id);

            pstmt.executeUpdate();

            System.out.println("Request sent successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public class UserConnection {


        //bu bölüm sizlerde farklı olacak
        private static final String url = "jdbc:mysql://localhost:3306/HireDrive";
        private static final String username = "root";
        private static final String password = "student_sifre";

        private static Connection connection;
        private static PreparedStatement statement;
        private static ResultSet resultSet;


        public static String getUserType(int user_id){
            String userType = null;
            String sql = "SELECT user_type FROM users WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, user_id);

                ResultSet rs = pstmt.executeQuery();

                // Check if a user with the given ID exists
                if (rs.next()) {
                    userType = rs.getString("user_type");
                } else {
                    System.out.println("No user found with ID " + user_id);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

            return userType;
        }

    }
}
