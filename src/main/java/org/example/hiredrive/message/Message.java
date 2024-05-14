package org.example.hiredrive.message;

import org.example.hiredrive.users.User;

import java.sql.Date;
import java.time.LocalDate;

@SuppressWarnings("exports")
public class Message {
    //private int messageID;
    private boolean isRead;
    private User sender;
    private User receiver;
    private String content;
    private Date timeStamp;
    
    public Message(User sender, User receiver, String content) {

        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = Date.valueOf(LocalDate.now());

    }
    //for database
    public Message(User sender, User receiver, String content, Date date, boolean isRead) {

        this.isRead = isRead;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = date;

    }

    public User getSender(){
        return sender;
    }
    public User getReceiver(){
        return receiver;
    }
    public String getContent(){
        return content;
    }
    public boolean getIsRead(){
        return isRead;
    }
    public Date getDate(){
        return timeStamp;
    }

}
