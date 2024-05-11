package org.example.hiredrive.users;

import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.message.Message;

public class User {
    public String getUsername() {
        return username;
    }

    protected int userId;
    protected String surname;
    protected String username;
    protected String password;
    protected String email;
    protected double rating;

    public void setRating(double rating) {
        this.rating = rating;
    }


    public User( String username, String usersurname, String password, String email){
        this.username = username;
        this.surname = usersurname;
        this.password = password;
        this.email = email;
        this.rating = 0;
    }

    public void rateUser(User user, double rate) throws Exception{
        int ratedID = user.userId;
        if(UserConnection.worksWith(userId, ratedID)){
            UserConnection.rateUser(rate, user.userId);
            user.updateRating();
        }
        else throw new IllegalArgumentException("You cannot rate a user you are not associated with");
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
    public double getRating() {
        return this.rating;
    }
    public void updateRating() {
        this.rating = UserConnection.getRating(userId);
    }


    public String toString(){
        return this.userId + this.username + " " + this.surname + " " + this.password + " " + this.email;
    }


}
