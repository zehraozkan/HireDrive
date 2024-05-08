package org.example.hiredrive;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.example.hiredrive.Connection.UserConnection;

public class Driver extends User{
    private boolean available;
    private ArrayList<String> lisenses;

    public Driver(String username, String usersurname, String password, String email){
        super(username, usersurname, password, email);
        UserConnection.addUser(username, surname, email, password, "driver", Date.valueOf(LocalDate.now()));
    }

}
