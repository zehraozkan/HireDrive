package org.example.hiredrive.Connection;

import java.sql.*;

public class UserConnection {
    private static final String url = "jdbc:mysql://localhost:3306/HireDrive";
    private static final String username = "root";
    private static final String password = "student_sifre";

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    public static void addUser(String userName, String userSurname, String userMail,String password, String userType, Date dateCreated) {
        String sql = "INSERT INTO users (user_name, user_surname, user_mail,user_password, user_type, date_created) VALUES (?, ?, ?, ?, ? , ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userSurname);
            pstmt.setString(3, userMail);
            pstmt.setString(4, password);
            pstmt.setString(5, userType);
            pstmt.setDate(6, dateCreated);
            pstmt.executeUpdate();

            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            // Execute the delete operation
            int rowsAffected = pstmt.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                System.out.println("User with ID " + userId + " deleted successfully.");
            } else {
                System.out.println("No user found with ID " + userId + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static boolean updateUserNameAndSurname(int userId, String newName, String newSurname) {
        String sql = "UPDATE users SET user_name = ?, user_surname = ? WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newSurname);
            pstmt.setInt(3, userId);

            // Execute the update operation
            int rowsAffected = pstmt.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                System.out.println("Name and surname updated successfully for user with ID " + userId + ".");
                return true; // Update successful
            } else {
                System.out.println("No user found with ID " + userId + ".");
                return false; // No user found with the given ID
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false; // Update failed
        }
    }
    public static void retrieveUserData(String userType) {
        String sql = "SELECT * FROM users WHERE user_type LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + userType + "%");
            ResultSet rs = pstmt.executeQuery();

            // Print retrieved data
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userSurname = rs.getString("user_surname");
                String userMail = rs.getString("user_mail");
                String dateCreated = rs.getString("date_created");
                String type = rs.getString("user_type");
                double rating = rs.getDouble("rating");
                String available = rs.getString("available");

                // Print formatted output
                System.out.printf("User ID: %-5d | Name: %-20s | Surname: %-20s | Email: %-30s | Created: %-20s | Type: %-10s | Rating: %-4.1f | Available: %-3s\n",
                        userId, userName, userSurname, userMail, dateCreated, type, rating, available);

                // Append to output string
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static boolean checkPassword(String email, String password){

        String sql = "SELECT user_password from users WHERE user_mail = ?";

        try (Connection conn = DriverManager.getConnection(url, username, UserConnection.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            // String a = rs.getString("user_password");
            // Check if ResultSet has any rows
            if (rs.next()) {
                String storedPassword = rs.getString("user_password");
                return storedPassword.equals(password);
            } else {
                // No matching user found
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    public static void printAllUsers() {
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            // Print header
            System.out.println("User ID | User Name | User Surname | Date Created | User Mail | User Type | Rating | Available");
            System.out.println("---------------------------------------------------------------");

            // Iterate through the result set and print each user's information
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userSurname = rs.getString("user_surname");
                String dateCreated = rs.getString("date_created");
                String userMail = rs.getString("user_mail");
                String userType = rs.getString("user_type");
                double rating = rs.getDouble("rating");
                String available = rs.getString("available");

                // Print user information
                System.out.printf("%-10d | %-17s | %-12s | %-12s | %-20s | %-9s | %-6.1f | %-9s%n",
                        userId, userName, userSurname, dateCreated, userMail, userType, rating, available);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void getUsers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(url, username, password);

            // Create statement
            statement = connection.createStatement();

            // Execute query to fetch users
            String query = "SELECT user, host FROM mysql.user";
            resultSet = statement.executeQuery(query);

            // Process results
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String host = resultSet.getString("host");
                System.out.println("User: " + user + ", Host: " + host);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }    }

    public static void main(String[] args) {

        //4gggg44// rs();
        //dehhhhhjjjjj// // j;

       // p00rlhhhhhhhhhjjjjjgggggg
        //
        //
        //



        //
        //
        //
        //
        //
        //
        // jjjjjj// jjjjhhhhhhhhhhhhhhhhhhhhhjjjjjjjjjjhhhhhhhhhhhhhhhhhhhhhces?"// );
       MessageConnection.getMessagesForUser(5);
      //1222222222222228888888888.addAdvertisement();

    }
}



