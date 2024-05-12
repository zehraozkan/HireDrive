package org.example.hiredrive.users;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.example.hiredrive.Connection.UserConnection;

public class Driver extends User {
    private boolean available;
    private ArrayList<String> lisenses;
    private String surname;


    //for the user
    public Driver(String username, String usersurname, String password, String email, String phoneNo){
        super(username + usersurname, password, email, phoneNo);
        this.surname = usersurname;
        
        UserConnection.addUser(username, surname, email, password, "driver", Date.valueOf(LocalDate.now()));
        userId = UserConnection.getUserID(email);

    }
    //for database
    public Driver(String username, String userSurname, String password, String email, int userId, String phoneNo) {
        super(username,  password, email, phoneNo);
        super.userId = userId;
    }

    public String toString(){
        return super.toString() + "driver " + available + "\nLisenses: " + lisenses;
    }


}
