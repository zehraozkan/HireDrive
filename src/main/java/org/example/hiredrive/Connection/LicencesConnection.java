package org.example.hiredrive.Connection;

import java.io.IOException;
import java.sql.*;
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

    private Connection connection;
    private static final String url = properties.getProperty("db.url");
    private static final String username = properties.getProperty("db.username");
    private static final String password = properties.getProperty("db.password");


    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    // Method to close the database connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void addLicense(int driverId, String licenseType) throws SQLException {
        String sql = "INSERT INTO licenses (driver_id, licence_type) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, driverId);
            statement.setString(2, licenseType);
            statement.executeUpdate();
        }
    }

    // Method to return all licenses belonging to one driver
    public ResultSet getDriverLicenses(int driverId) throws SQLException {
        String sql = "SELECT * FROM licenses WHERE driver_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, driverId);
        return statement.executeQuery();
    }
}
