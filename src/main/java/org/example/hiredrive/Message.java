package org.example.hiredrive;

import java.time.LocalDateTime;
import java.util.*;

public class Message {
    //private int messageID;
    private boolean isRead;
    private User sender;
    private User receiver;
    private String content;
    private LocalDateTime timeStamp;
    
    public Message( User sender, User receiver, String content) {

        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = LocalDateTime.now();   
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
