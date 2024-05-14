package org.example.hiredrive.Connection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

    public class LicencesConnection {

    private static final Properties properties;

    static {
        try {
            properties = DatabaseConfig.loadProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties", e);
        }
    }

    private static Connection connection;
    private static final String url = properties.getProperty("db.url");
    private static final String username = properties.getProperty("db.username");
    private static final String password = properties.getProperty("db.password");


    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    // Method to close the database connection
    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void addLicense(int driverId, String licenseType) throws SQLException {
        String sql = "INSERT INTO licenses (driver_id, licence_type) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            statement.setString(2, licenseType);
            statement.executeUpdate();
        }
    }

        // Database connection details

    public static ArrayList<String> getLicensesForDriver(int driverId) {
        ArrayList<String> licenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve license strings for the given driver ID
            String sql = "SELECT licence_type FROM licenses WHERE driver_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, driverId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String licenseType = resultSet.getString("licence_type");
                        licenses.add(licenseType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return licenses;
    }
}
