package org.example.hiredrive.Connection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MessageConnection {


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
    //TODO getting messeages between 2 users



}
