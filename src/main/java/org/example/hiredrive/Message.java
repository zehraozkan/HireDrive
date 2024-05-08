package org.example.hiredrive;

import java.util.*;

public class Message {
    //private int messageID;
    private boolean isRead;
    private User sender;
    private User receiver;
    private String content;
    private Date timeStamp;
    
    public Message( boolean isRead, User sender, User receiver, String content, Date timeStamp) {

        this.isRead = isRead;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = timeStamp;   
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


}
