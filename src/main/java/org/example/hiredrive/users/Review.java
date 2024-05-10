package org.example.hiredrive.users;

import org.example.hiredrive.Connection.UserConnection;

public class Review {
    private int reviewID;
    private User reviewer;
    private String comment;
    private int rating;

    public Review(int reviewerID, String comment, int rating){
        setReviewID();
        this.reviewer = UserConnection.retrieveUser(reviewerID);
        this.comment = comment;
        this.rating = rating;
    }

    //getter methods
    public int getReviewID(){
        return this.reviewID;
    }

    public User getReviewer(){
        return this.reviewer;
    }

    public String getComment(){
        return this.comment;
    }

    public int getRating(){
        return this.rating;
    }

    //setter methods
    public void setRating(int rate){
        this.rating = rate;
    }

    public void setReviewID(){
        this.reviewID = (int) (Math.random() * 900000) + 100000;
    }
}
