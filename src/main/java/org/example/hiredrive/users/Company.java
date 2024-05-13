package org.example.hiredrive.users;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.example.hiredrive.Connection.UserConnection;

public class Company extends User {
    private ArrayList<Driver> worksWith;


    //for the user
    public Company(String username, String password, String email, String phoneNo){
        super(username,  password, email, phoneNo);

        //adds the user to the database
        UserConnection.addUser(username, "", email, password, phoneNo, "company", Date.valueOf(LocalDate.now()), 0);//no surname for company
        userId = UserConnection.getUserID(email);
        worksWith = UserConnection.getAssociatedDrivers(userId);
        userType = "company";
    }

    //for the database
    public Company(String username,  String password, String email, int user_id, String phoneNo){
        super(username,  password, email, phoneNo);
        userId = user_id;
        worksWith = UserConnection.getAssociatedDrivers(user_id);
    }

    public ArrayList<Driver> getWorksWith() {
        return worksWith;
    }

    @Override
    public String toString() {
        return super.toString() + "Company{" + "worksWith=" + worksWith + '}';
    }
}
