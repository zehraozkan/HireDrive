package org.example.hiredrive;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.example.hiredrive.Connection.UserConnection;

public class Company extends User{
    private ArrayList<Driver> worksWith;

    public Company(String username, String usersurname, String password, String email){
        super(username, usersurname, password, email);
        UserConnection.addUser(username, surname, email, password, "company", Date.valueOf(LocalDate.now()));
        worksWith = new ArrayList<Driver>();
    }
}
