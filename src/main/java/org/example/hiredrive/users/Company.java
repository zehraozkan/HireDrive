package org.example.hiredrive.users;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.example.hiredrive.Connection.UserConnection;

public class Company extends User {
    private ArrayList<Driver> worksWith;


    //for the user
    public Company(String username, String usersurname, String password, String email){
        super(username, usersurname, password, email);

        //adds the user to the database
        UserConnection.addUser(username, surname, email, password, "company", Date.valueOf(LocalDate.now()));
        userId = UserConnection.getUserID(email);
        worksWith = UserConnection.getAssociatedDrivers(userId);
    }

    //for the database
    public Company(String username, String usersurname, String password, String email, int user_id){
        super(username, usersurname, password, email);
        userId = user_id;
        worksWith = UserConnection.getAssociatedDrivers(user_id);
    }

    public ArrayList<Driver> getWorksWith() {
        return worksWith;
    }
}
