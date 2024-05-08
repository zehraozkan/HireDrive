package org.example.hiredrive;

import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.Connection.UserConnection;

public class Test {
    public static void main(String[] args) {
     Company company = new Company("ee", "ee", "123456", "cail");
        Driver driver = new Driver("eee", "eee", "123456", "ljail");
        Message message = new Message( driver, company, "asd");
        driver.sendMessage(message); 

        MessageConnection.getMessagesForUser(23);


    }
}
