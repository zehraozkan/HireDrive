package org.example.hiredrive.users;

import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.Connection.ReviewConnection;
import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.message.Message;

public class User {

    protected int userId;
    protected String username;
    protected String password;
    protected String email;
    protected double rating;
    protected String phoneNumber;
    protected String userType;


    public User(String username,String password, String email, String phoneNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.rating = 0;
        this.phoneNumber = phoneNumber;
    }


    public void reviewUser(User user, String comment, int rating){
        if(UserConnection.worksWith(userId, user.userId)){
            this.userId = user.userId;
            this.username = user.username;
            ReviewConnection.addReview(this.userId, user.userId, comment, rating);
           // user.updateRating();
        }
        else throw new IllegalArgumentException("You cannot rate a user you are not associated with");
    }
    public String getUserType(){
        return userType;
    }

    public void sendMessage(Message message) {
        MessageConnection.sendMessage(this.userId, message.getReceiver().userId, message.getContent());
    }
    public String getMail() {
        return this.email;
    }
    public int getUserId() {
        return this.userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public double getRating() {
        this.rating = ReviewConnection.getRating(userId);
        return this.rating;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toString(){
        return this.userId + this.username + " "  + " " + this.password + " " + this.email;
    }


}
