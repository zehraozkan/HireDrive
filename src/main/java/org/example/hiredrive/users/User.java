package org.example.hiredrive.users;

import org.example.hiredrive.Connection.MessageConnection;
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
    protected int rating;

    public User( String username, String usersurname, String password, String email){
        this.username = username;
        this.surname = usersurname;
        this.password = password;
        this.email = email;
        
    }

    public void sendMessage(Message message) {
        MessageConnection.sendMessage(this.userId, message.getReceiver().userId, message.getContent());
    }

    public String getMail(){
        return this.email;
    }public int getUserId(){
        return this.userId;
    }public int getRating(){
        return this.rating;
    }


}
