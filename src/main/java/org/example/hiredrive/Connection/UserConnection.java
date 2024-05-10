package org.example.hiredrive.Connection;

import org.example.hiredrive.Company;
import org.example.hiredrive.Driver;
import org.example.hiredrive.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class UserConnection {


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


    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    public static void addUser(String userName, String userSurname, String userMail,String aPassword, String userType, Date dateCreated) {
        String sql = "INSERT INTO users (user_name, user_surname, user_mail,user_password, user_type, date_created) VALUES (?, ?, ?, ?, ? , ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userSurname);
            pstmt.setString(3, userMail);
            pstmt.setString(4, aPassword);
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

    /**
     *
     * @param userId int
     * @return returns the information of the user whose id is given
     */
    public static User retrieveUser(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        org.example.hiredrive.Driver driver = null;
        //StringBuilder resultString = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            // Check if there is a result
            if (rs.next()) {
                String userName = rs.getString("user_name");
                String userSurname = rs.getString("user_surname");
                String userPassword = rs.getString("user_password");
                String userMail = rs.getString("user_mail");
                String dateCreated = rs.getString("date_created");
                String type = rs.getString("user_type");
                double rating = rs.getDouble("rating");
                String available = rs.getString("available");

                driver = new org.example.hiredrive.Driver(userName, userSurname, userPassword, userMail, userId);

            } else {
            //    resultString.append("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Return the result string
        return driver;
    }

    /**
     *
     * @param email String
     * @param password String
     * @return
     * checks whether the email mathces the password
     */
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
    /**
     * return all the users in the user table
     */
    public static ArrayList<User> getAllUsers(){
            // Call getAllUsers(String usertype) with a wildcard parameter
            return getAllUsers("%");
    }

    /**
     *
     * @param usertype String
     * @return returns all the users in the usertype type
     */
    public static ArrayList<User> getAllUsers(String usertype) {

        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE user_type LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usertype);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userPassword = rs.getString("user_password");
                String userSurname = rs.getString("user_surname");
                String dateCreated = rs.getString("date_created");
                String userMail = rs.getString("user_mail");
                String user_type = rs.getString("user_type");
                double rating = rs.getDouble("rating");
                String available = rs.getString("available");

                if(user_type.equals("driver")){
                    users.add(new org.example.hiredrive.Driver(userName, userSurname, userPassword, userMail, userId));
                }
                else if(user_type.equals("company")){
                    users.add(new Company(userName, userSurname, userPassword, userMail,userId));
                }

                // Append user information to the result string
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Return the result string
        return users;
    }

    /**
     * for debugging
     */
    private static void getUsers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);

            statement = connection.createStatement();

            String query = "SELECT user, host FROM mysql.user";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String host = resultSet.getString("host");
                System.out.println("User: " + user + ", Host: " + host);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
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
    public static int getUserID(String email){
        int user_id = 0;
        String sql = "SELECT user_id FROM users WHERE user_mail = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            // Check if a user with the given ID exists
            if (rs.next()) {
                user_id = rs.getInt("user_id");
            } else {
                System.out.println("No user found with email " + email);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user_id;
    }

    public static ArrayList<org.example.hiredrive.Driver> getAssociatedDrivers(int company_id){

        ArrayList<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, company_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String userPassword = rs.getString("user_password");
                String userSurname = rs.getString("user_surname");
                String dateCreated = rs.getString("date_created");
                String userMail = rs.getString("user_mail");
                String userType = rs.getString("user_type");
                double rating = rs.getDouble("rating");
                String available = rs.getString("available");

                drivers.add(new org.example.hiredrive.Driver(userName, userSurname, userPassword, userMail, userId));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return  drivers;
    }


    public static void main(String[] args) {

        AdvertisementConnection.deleteAdvertisement(2);
        System.out.println(AdvertisementConnection.getAdvertisementCount());

    }
}



