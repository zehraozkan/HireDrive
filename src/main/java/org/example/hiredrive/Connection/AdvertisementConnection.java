package org.example.hiredrive.Connection;

import org.example.hiredrive.Advertisement;

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
     *adds an advertisement with the given info
     * @param ownerId int
     * @param addTitle String
     * @param cargoType String
     * @param addContent String
     * @param dueDate Date
     * creates a new advertisement
     * only companies can add advertisement
     */
    public static int addAdvertisement(int ownerId, String addTitle, String cargoType, String addContent, Date dueDate) {
        String sql = "INSERT INTO advertisement (owner_id, add_title, cargo_type, add_content, due_date) VALUES (?, ?, ?, ?, ?)";
        int advertisementId = -1; // Initialize advertisement ID to -1 (invalid value)

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ownerId);
            pstmt.setString(2, addTitle);
            pstmt.setString(3, cargoType);
            pstmt.setString(4, addContent);
            pstmt.setDate(5, new java.sql.Date(dueDate.getTime()));

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                // Retrieve the generated keys (advertisement ID)
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        advertisementId = generatedKeys.getInt(1); // Retrieve the first generated key
                        System.out.println("Advertisement added successfully. Advertisement ID: " + advertisementId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return advertisementId; // Return the generated advertisement ID
    }

    /**
     * deletes the advertisement wiht the given id
     * @param advertisementId int
     */
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


    public static Advertisement getAdvertisementById(int add_id) {
        Advertisement advertisement = null;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT owner_id, add_title, cargo_type, add_content, due_date, requests FROM advertisement WHERE advert_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, add_id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int owner_id = resultSet.getInt("owner_id");
                        String add_title = resultSet.getString("add_title");
                        String cargo_type = resultSet.getString("cargo_type");
                        String add_content = resultSet.getString("add_content");
                        Date due_date = resultSet.getDate("due_date");
                        int requests = resultSet.getInt("requests");

                        //public Advertisement(Company owner, String addTitle,String addContent, String cargoType, Date dueDate)
                        advertisement = new Advertisement(); //TODO
                        //advertisement = new Advertisement(add_id, owner_id, add_title, cargo_type, add_content, due_date, requests);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return advertisement;
    }
    public static int getAdvertisementCount() {
        int count = 0;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM advertisement")) {

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(getAdvertisementCount());
    }
}
