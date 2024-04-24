package org.example.hiredrive;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseCheck {

    private static final String url = "jdbc:mysql://localhost:3306/giraffe";
    private static final String username = "root";
    private static final String password = "student_sifre";

        
        
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
    
    public static String retrieveUserData(String userType) {
        String sql = "SELECT * FROM users WHERE user_type = ?";
        String output = "";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userType);
            ResultSet rs = pstmt.executeQuery();
    
            // Print retrieved data
            while (rs.next()) {
                String userName = rs.getString("user_name");
                String userSurname = rs.getString("user_surname");
                String userMail = rs.getString("user_mail");
                String dateCreated = rs.getString("date_created");
                String type = rs.getString("user_type");
    
                // Print formatted output
                System.out.printf("User: %-20s | Email: %-30s| Created: %-20s | Type: %-10s\n",
                        userName +"  " +  userSurname, userMail, dateCreated , type);
    
                // Append to output string
                output += String.format("User: %s %s, Email: %s, Created: %s\n",
                        userName, userSurname, userMail, dateCreated);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
        return output;
    }
    public static boolean checkPassword(String email, String password){

        String sql = "SELECT user_password from users WHERE user_mail = ?";
        String ouptut ="";
        
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
    
     public static void main(String[] args) {
        retrieveUserData("driver");
    }
}


