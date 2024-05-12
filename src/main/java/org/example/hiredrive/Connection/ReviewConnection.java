package org.example.hiredrive.Connection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ReviewConnection {
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

    public static double getRating(int user_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double rating =0;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(url, username, password);

            // Retrieve current total rating and total number of ratings for the user
            String query = "SELECT rating FROM users WHERE user_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, user_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                rating = rs.getDouble("rating");

            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rating;
    }

    private static void rateUser(double rate, int user_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT rating, total_rated FROM users WHERE user_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, user_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double currentRating = rs.getDouble("rating");
                int totalRated = rs.getInt("total_rated");

                int newTotalRated = totalRated + 1;
                double newTotalRating = ((currentRating * totalRated) + rate) / newTotalRated;

                String updateQuery = "UPDATE users SET rating = ?, total_rated = ?, available = ? WHERE user_id = ?";
                stmt = conn.prepareStatement(updateQuery);
                stmt.setDouble(1, newTotalRating);
                stmt.setInt(2, newTotalRated);
                stmt.setInt(4, user_id);
                stmt.setString(3, "yes");
                stmt.executeUpdate();

                System.out.println("User rating updated successfully!");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void addReview(int sender_id, int user_id, String comment, int rating){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO review VALUES(?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, sender_id);
            stmt.setDouble(2, user_id);
            stmt.setString(3, comment);
            stmt.setDouble(3, rating);

            int rows_affected = stmt.executeUpdate();

            if (rows_affected > 0) {
                System.out.println("Review added successfully!");
                rateUser(rating, user_id);
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
