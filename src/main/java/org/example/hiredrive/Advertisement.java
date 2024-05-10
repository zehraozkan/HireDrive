package org.example.hiredrive;

import org.example.hiredrive.Connection.AdvertisementConnection;
import org.example.hiredrive.Connection.UserConnection;

import java.sql.Date;
import java.util.ArrayList;

public class Advertisement {

    private int advertisementID;
    private Company owner;
    private String addTitle;
    private String cargoType;
    private Date dueDate;
    private ArrayList<Request> requests;


    public Advertisement(int AdvertisementID, int company_id, String addTitle, String cargoType, Date dueDate){
        this.advertisementID = AdvertisementID;
        this.owner = (Company) UserConnection.retrieveUser(company_id);
        this.addTitle = addTitle;
        this.cargoType = cargoType;
        this.dueDate = dueDate;
        this.requests = new ArrayList<>();

    }

    //user view
    public Advertisement(Company owner, String addTitle,String addContent, String cargoType, Date dueDate){
        this.advertisementID = AdvertisementConnection.addAdvertisement(owner.userId, addTitle, cargoType, addContent, dueDate);
        this.owner = owner;
        this.addTitle = addTitle;
        this.cargoType = cargoType;
        this.dueDate = dueDate;
        this.requests = new ArrayList<>();

    }
    public Advertisement(){

    }
}