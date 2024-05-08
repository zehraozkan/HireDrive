package org.example.hiredrive;

import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.Connection.UserConnection;

public class User {
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


}
