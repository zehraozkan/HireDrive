package org.example.hiredrive.message;

import java.util.ArrayList;

import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

public class Chat {
    //private int chatID;
    private Driver driver;
    private Company company;
    private ArrayList<Message> messages;

    public Chat(Driver driver, Company company, ArrayList<Message> messages){

        this.driver = driver;
        this.company = company;
        this.messages = messages;
    }

    public ArrayList<Message> showChat(int senderId, int receiverId){
        return MessageConnection.retrieveMessagesBetweenUsers(senderId, receiverId);
    }

    public Driver getDriver(){
        return driver;
    }
    public Company getCompany(){
        return company;
    }
    public ArrayList<Message> getMessages(){
        return messages;
    }

}
