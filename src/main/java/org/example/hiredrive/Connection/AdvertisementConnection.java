package org.example.hiredrive.Connection;

import org.example.hiredrive.advertisement.Advertisement;
import org.example.hiredrive.advertisement.Request;
import org.example.hiredrive.users.Company;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;
import java.sql.*;
import java.util.ArrayList;
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
    public static int addAdvertisement(int ownerId, String addTitle, String cargoType, String addContent, Date dueDate, String requiredLicense, int experience) {
        String sql = "INSERT INTO advertisement (owner_id, add_title, cargo_type, add_content, due_date, required_license, experience) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int advertisementId = -1; // Initialize advertisement ID to -1 (invalid value)

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ownerId);
            pstmt.setString(2, addTitle);
            pstmt.setString(3, cargoType);
            pstmt.setString(4, addContent);
            pstmt.setDate(5, new java.sql.Date(dueDate.getTime()));
            pstmt.setString(6, requiredLicense);
            pstmt.setInt(7, experience);

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

    /**
     *
     * @param ownerId
     * @return an arraylist of advertisements containing all the advertisements
     */
    public static ArrayList<Advertisement> listAdvertisementsByOwner(int ownerId) {
        String sql = "SELECT * FROM advertisement WHERE owner_id = ?";
        ArrayList<Advertisement> advertisements = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ownerId);
            ResultSet rs = pstmt.executeQuery();

            // Append header to the result string

            // Iterate through the result set and append each advertisement to the result string
            while (rs.next()) {
                int advertId = rs.getInt("advert_id");
                int owner_id = rs.getInt("owner_id");
                String addTitle = rs.getString("add_title");
                String cargoType = rs.getString("cargo_type");
                String addContent = rs.getString("add_content");
                Date dueDate = rs.getDate("due_date");
                String license = rs.getString("required_license");
                int experience = rs.getInt("experience");


                advertisements.add(new Advertisement(advertId, owner_id, addTitle, addContent, cargoType,dueDate, getAllRequestsForAdvertisement(advertId), license, experience ));

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return advertisements;
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
                        String license = resultSet.getString("required_license");
                        int experience = resultSet.getInt("experience");

                        //public Advertisement(Company owner, String addTitle,String addContent, String cargoType, Date dueDate)
//
                        advertisement = new Advertisement(add_id, owner_id, add_title, add_content, cargo_type, due_date, getAllRequestsForAdvertisement(add_id), license, experience);
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
    public static ArrayList<Request> getAllRequestsForAdvertisement(int advertisementId) {
        ArrayList<Request> requests = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Prepare SQL statement
            String sql = "SELECT * FROM jobRequests WHERE add_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, advertisementId);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int driverId = resultSet.getInt("driver_id");
                int addId = resultSet.getInt("add_id");
                String status = resultSet.getString("status");

                // Create JobRequest object
                Request request = new Request(status, driverId, addId);
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static ArrayList<Advertisement> filterAdvertisements(String from, String destination, String cargoType, int minExperience, int maxExperience, int minRate, int maxRate, Date minDeadline, Date maxDeadline){
            ArrayList<Advertisement> matchingAdvertisements = new ArrayList<>();
            String sql = "SELECT * FROM advertisement WHERE cargo_type = ?";
        
            // Construct the SQL query based on the provided parameters
            if (from != null && !from.isEmpty()) {
                sql += " AND add_title LIKE ?";
            }
            if (destination != null && !destination.isEmpty()) {
                sql += " AND add_content LIKE ?";
            }
            if (minExperience >= 0) {
                sql += " AND experience >= ?";
            }
            if (maxExperience >= 0) {
                sql += " AND experience <= ?";
            }
            if (minRate >= 0) {
                sql += " AND requests >= ?";
            }
            if (maxRate >= 0) {
                sql += " AND requests <= ?";
            }
            if (minDeadline != null) {
                sql += " AND due_date >= ?";
            }
            if (maxDeadline != null) {
                sql += " AND due_date <= ?";
            }
        
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cargoType);
        
                int parameterIndex = 2; // Start index for additional parameters
        
                // Set parameters based on the provided criteria
                if (from != null && !from.isEmpty()) {
                    pstmt.setString(parameterIndex++, "%" + from + "%");
                }
                if (destination != null && !destination.isEmpty()) {
                    pstmt.setString(parameterIndex++, "%" + destination + "%");
                }
                if (minExperience >= 0) {
                    pstmt.setInt(parameterIndex++, minExperience);
                }
                if (maxExperience >= 0) {
                    pstmt.setInt(parameterIndex++, maxExperience);
                }
                if (minRate >= 0) {
                    pstmt.setInt(parameterIndex++, minRate);
                }
                if (maxRate >= 0) {
                    pstmt.setInt(parameterIndex++, maxRate);
                }
                if (minDeadline != null) {
                    pstmt.setDate(parameterIndex++, minDeadline);
                }
                if (maxDeadline != null) {
                    pstmt.setDate(parameterIndex++, maxDeadline);
                }
        
                ResultSet rs = pstmt.executeQuery();
        
                while (rs.next()) {
                    int advertId = rs.getInt("advert_id");
                    int ownerId = rs.getInt("owner_id");
                    String addTitle = rs.getString("add_title");
                    String addContent = rs.getString("add_content");
                    Date dueDate = rs.getDate("due_date");
                    int requests = rs.getInt("requests");
                    String requiredLicense = rs.getString("required_license");
                    int experience = rs.getInt("experience");
        
                    Advertisement advertisement = new Advertisement(advertId, ownerId, addTitle, addContent, cargoType, dueDate, getAllRequestsForAdvertisement(advertId), requiredLicense, experience);
                    matchingAdvertisements.add(advertisement);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
            return matchingAdvertisements;
        }
    }
        


