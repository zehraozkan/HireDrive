package org.example.hiredrive.Connection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.example.hiredrive.message.Message;


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
        String insertMessageSQL = "INSERT INTO messages (date_sent, sender_id, receiver_id, message_content) VALUES (NOW(), ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement = connection.prepareStatement(insertMessageSQL);
        ) {
            preparedStatement.setInt(1, sender_id); // Your sender_id
            preparedStatement.setInt(2, userId); // Receiver's user_id
            preparedStatement.setString(3, messageContent);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Message sent successfully to user with user_id: " + userId);
            } else {
                System.out.println("Failed to send message.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static ArrayList<Message> getMessagesForUser(int recieverID) {
        ArrayList<Message> messages = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT date_sent, sender_id, message_content FROM messages WHERE receiver_id = ? ORDER BY date_sent DESC";
            statement = connection.prepareStatement(query);
            statement.setInt(1, recieverID);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Timestamp dateSent = resultSet.getTimestamp("date_sent");
                int senderId = resultSet.getInt("sender_id");
                String messageContent = resultSet.getString("message_content");
                Date messageDate = resultSet.getDate("date_sent");
                boolean isRead = resultSet.getBoolean("is_read");

                
                //public Message(User sender, User receiver, String content, Date date, boolean isRead)
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
        return messages;
    }
    public static ArrayList<Message> retrieveMessagesBetweenUsers(int senderId, int receiverId) {

        ArrayList<Message> messages = new ArrayList<Message>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) ORDER BY date_sent";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            statement.setInt(3, receiverId);
            statement.setInt(4, senderId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Date dateSent = resultSet.getDate("date_sent");
                int sender = resultSet.getInt("sender_id");
                int receiver = resultSet.getInt("receiver_id");
                String messageContent = resultSet.getString("message_content");
                boolean isRead = resultSet.getBoolean("is_read");

                messages.add(new Message(UserConnection.getUser(sender), UserConnection.getUser(receiver), messageContent, dateSent, isRead));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  messages;
    }


    public static void main(String[] args) {



        sendMessage(2,3, "sdnfjnsdf");
        ArrayList<Message> messages = retrieveMessagesBetweenUsers(2, 3);

        for (Message message : messages) {System.out.println(message.getContent());}


    }

}
