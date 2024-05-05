package org.example.hiredrive;

import java.sql.*;

public class DatabaseCheck {


    //bu bölüm sizlerde farklı olacak
    private static final String url = "jdbc:mysql://localhost:3306/HireDrive";
    private static final String username = "root";
    private static final String password = "student_sifre";

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;



    public static void addUser(String userName, String userSurname, String userMail,String password, String userType, Date dateCreated) {
        String sql = "INSERT INTO users (user_name, user_surname, user_mail,user_password, user_type, date_created) VALUES (?, ?, ?, ?, ? , ?)";

        try (Connection conn = DriverManager.getConnection(url, username, DatabaseCheck.password);
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

    public static void sendMessage(int sender_id,int userId, String messageContent) {
        // SQL statement to insert a message into the messages table
        String insertMessageSQL = "INSERT INTO messages (date_sent, sender_id, receiver_id, message_content) VALUES (NOW(), ?, ?, ?)";

        try (
                // Connect to the database
                Connection connection = DriverManager.getConnection(url, username, password);

                // Create a PreparedStatement object to execute the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(insertMessageSQL);
        ) {
            // Set values for the parameters in the SQL statement
            preparedStatement.setInt(1, sender_id); // Your sender_id
            preparedStatement.setInt(2, userId); // Receiver's user_id
            preparedStatement.setString(3, messageContent);

            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Message sent successfully to user with user_id: " + userId);
            } else {
                System.out.println("Failed to send message.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPassword(String email, String password){

        String sql = "SELECT user_password from users WHERE user_mail = ?";

        try (Connection conn = DriverManager.getConnection(url, username, DatabaseCheck.password);
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
        try (
                // Connect to the database
                Connection connection = DriverManager.getConnection(url, username, password);

                // Create a Statement object to execute SQL queries
                Statement statement = connection.createStatement();

                // Execute the SQL query and get the result set
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        ) {
            // Print the header
            System.out.println("User ID | User Name | User Surname");
            System.out.println("----------------------------------");

            // Iterate through the result set and print each user
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String userSurname = resultSet.getString("user_surname");

                // Print the user information
                System.out.printf("%7d | %-20s | %-20s%n", userId, userName, userSurname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
    }
    public static void getMessagesForUser(int userId) {
        try {
            // Establish connection to MySQL database
            connection = DriverManager.getConnection(url, username, password);

            // SQL query to retrieve messages for a user
            String query = "SELECT date_sent, sender_id, message_content FROM messages WHERE receiver_id = ? ORDER BY date_sent DESC";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Print out the results formatted nicely
            System.out.println("Messages sent to user with ID " + userId + ":");
            while (resultSet.next()) {
                Timestamp dateSent = resultSet.getTimestamp("date_sent");
                int senderId = resultSet.getInt("sender_id");
                String messageContent = resultSet.getString("message_content");

                System.out.println("Date Sent: " + dateSent);
                System.out.println("Sender ID: " + senderId);
                System.out.println("Message Content: " + messageContent);
                System.out.println("------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection, statement, and result set
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     public static void main(String[] args) {

       // getUsers();
       printAllUsers();
        // sendMessage(1,2,"you are beautiful");
        // getMessagesForUser(2);

    }
}


