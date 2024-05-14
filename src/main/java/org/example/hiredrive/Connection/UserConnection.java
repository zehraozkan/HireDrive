package org.example.hiredrive.Connection;

import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;
import org.example.hiredrive.users.User;

import java.io.IOException;
import java.sql.*;
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

    public static void addUser(String userName, String userSurname, String userMail,String aPassword,String phoneNo ,String userType, Date dateCreated, int experience) {
        String sql = "INSERT INTO users (user_name, user_mail,user_password, user_type, date_created, phone_number, experience) VALUES (?, ?, ?, ?, ? , ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName + " " + userSurname);
            pstmt.setString(2, userMail);
            pstmt.setString(3, aPassword);
            pstmt.setString(4, userType);
            pstmt.setDate(5, dateCreated);
            pstmt.setString(6, phoneNo);
            if(userType.equals("company")){
                pstmt.setInt(7, 0);

            }
            pstmt.setInt(7, experience);
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
        String sql = "UPDATE users SET user_name = ? WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName + " " + newSurname);
            pstmt.setInt(2, userId);

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
    public static User getUser(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        //StringBuilder resultString = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            // Check if there is a result
            if (rs.next()) {
                String k = rs.getString("user_name");
                String userName = k;
                String userSurname = "";
                if(k.contains(" ")){
                    userName = k.split(" ")[0];
                    userSurname = rs.getString("user_name").split(" ")[1];
                }
                String userPassword = rs.getString("user_password");
                String userMail = rs.getString("user_mail");
                String phoneNumber = rs.getString("phone_number");
                String dateCreated = rs.getString("date_created");
                String type = rs.getString("user_type");
                int experience = rs.getInt("experience");

                if(type.equals("driver")){
                    user = new Driver(userName, userSurname, userPassword, userMail, userId, phoneNumber, experience);

                }
                else{
                    user = new Company(userName + userSurname , userPassword, userMail, userId, phoneNumber);
                }

            } else {

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Return the result string
        return user;
    }
    public static User getUser(String userMail) {
        String sql = "SELECT * FROM users WHERE user_mail = ?";
        User user = null;
        //StringBuilder resultString = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userMail);
            ResultSet rs = pstmt.executeQuery();

            // Check if there is a result
            if (rs.next()) {
                String k = rs.getString("user_name");
                String userName = k;
                String userSurname = "";
                if(k.contains(" ")){
                    userName = k.split(" ")[0];
                    userSurname = k.split(" ")[1];
                }
                String userPassword = rs.getString("user_password");
                String phoneNumber = rs.getString("phone_number");
                String dateCreated = rs.getString("date_created");
                String type = rs.getString("user_type");
                int userId = rs.getInt("user_id");
                int experience = rs.getInt("experience");

                if(type.equals("driver")){
                    user = new Driver(userName, userSurname, userPassword, userMail, userId, phoneNumber, experience);

                }
                else{
                    user = new Company(userName + userSurname , userPassword, userMail, userId, phoneNumber);
                }

            } else {

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // Return the result string
        return user;
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
                String userPassword = rs.getString("user_password");
                String k = rs.getString("user_name");
                String userName = k.split(" ")[0];
                String userSurname = "";
                if(k.contains(" ")) userSurname = rs.getString("user_name").split(" ")[1];
                String dateCreated = rs.getString("date_created");
                String userMail = rs.getString("user_mail");
                String user_type = rs.getString("user_type");
                String phoneNumber = rs.getString("phone_number");
                int experience = rs.getInt("experience");

                if(user_type.equals("driver")){
                    users.add(new Driver(userName, userSurname, userPassword, userMail, userId, phoneNumber, experience));
                }
                else if(user_type.equals("company")){
                    users.add(new Company(userName + userSurname, userPassword, userMail, userId, phoneNumber));
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

    public static ArrayList<Driver> getAssociatedDrivers(int company_id){

        ArrayList<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM works_with WHERE company_id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, company_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String k = rs.getString("user_name");
                String userName = k.split(" ")[0];
                String userSurname = "";
                if(k.contains(" ")) userSurname = k.split(" ")[1];
                String userPassword = rs.getString("user_password").split(" ")[1];
                String phoneNumber = rs.getString("phone_number");
                String userMail = rs.getString("user_mail");
                int experience = rs.getInt("experience");

                drivers.add(new Driver(userName, userSurname, userPassword, userMail, userId, phoneNumber, experience));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return  drivers;
    }

    public static void addWorksWith(int driverId, int companyId, Date startDate) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Prepare SQL statement
            String sql = "INSERT INTO works_with (driver_id, company_id, start_date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, driverId);
            statement.setInt(2, companyId);
            statement.setDate(3, new java.sql.Date(startDate.getTime()));

            // Execute the update
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Works with relation added successfully.");
            } else {
                System.out.println("Failed to add works with relation.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteWorksWithRelation(int driverId, int companyId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Prepare SQL statement
            String sql = "DELETE FROM works_with WHERE driver_id = ? AND company_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, driverId);
            statement.setInt(2, companyId);

            // Execute the update
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Works with relation deleted successfully.");
            } else {
                System.out.println("No works with relation found for deletion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean worksWith(int userId1, int userId2) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(url, username, password);

            // Check if the two users work together
            String query = "SELECT * FROM works_with WHERE (driver_id = ? AND company_id = ?) OR (driver_id = ? AND company_id = ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId1);
            stmt.setInt(2, userId2);
            stmt.setInt(3, userId2);
            stmt.setInt(4, userId1);
            rs = stmt.executeQuery();

            return rs.next(); // Return true if there is a matching record
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public static ArrayList<Driver> filterDrivers(String from, String destination, String cargoType, int minExperience, int maxExperience, int minRate, int maxRate, Date minDeadline, Date maxDeadline) {
        ArrayList<Driver> matchingDrivers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE cargo_type = ?";
        
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
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String userSurname = rs.getString("user_surname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNo = rs.getString("phone_no");
                int experience = rs.getInt("experience");
    
                Driver driver = new Driver(username, userSurname, password, email, userId, phoneNo, experience);
                matchingDrivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return matchingDrivers;
    }
    public static int getTotalRated(int userId) throws SQLException {
        int totalRated = 0;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT total_rated FROM users WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        totalRated = resultSet.getInt("total_rated");
                    }
                }
            }
        }

        return totalRated;
    }
    

    public static void main(String[] args) {

        for(User user : getAllUsers()){
            System.out.println(user.getUserId() + " |" + user.getUsername() + " rating:" +  user.getRating());
        }
    }
}
