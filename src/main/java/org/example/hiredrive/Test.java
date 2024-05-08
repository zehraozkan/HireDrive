package org.example.hiredrive;

import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.Connection.UserConnection;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Company company = new Company("ee", "ee", "123456", "cail");
        Driver driver = new Driver("eee", "eee", "123456", "ljail");
        Message message = new Message( driver, company, "asd");
        driver.sendMessage(message);

        UserConnection.retrieveUserData(17).sendMessage(message);
        //System.out.println(UserConnection.getUserID(company.getMail()));

        User user = driver;

       // ArrayList<User> users = new ArrayList<Driver>();



    }
}
