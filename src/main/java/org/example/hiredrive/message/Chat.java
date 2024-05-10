package org.example.hiredrive.message;

import java.util.ArrayList;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

public class Chat {
    private int chatID;
    private Driver driver;
    private Company company;
    private ArrayList<Message> messages;

    public Chat(int chatID, Driver driver, Company company, ArrayList<Message> messages){
        this.chatID = chatID;
        this.driver = driver;
        this.company = company;
        this.messages = messages;
    }

}
