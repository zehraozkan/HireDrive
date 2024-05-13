package org.example.hiredrive.advertisement;

import org.example.hiredrive.Connection.AdvertisementConnection;
import org.example.hiredrive.Connection.RequestConnection;
import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

import java.sql.Date;
import java.util.ArrayList;

@SuppressWarnings("exports")
public class Advertisement {

    private int advertisementID;
    private Company owner;
    private String addTitle;
    private String content;
    private String requiredLicense;
    private int experience;
    private String cargoType;
    private Date dueDate;
    private ArrayList<Request> requests;


    //database constructor
    public Advertisement(int AdvertisementID, int company_id, String addTitle, String content, String cargoType, Date dueDate, ArrayList<Request> requests, String requiredLicense, int experience){
        this.advertisementID = AdvertisementID;
        this.owner = (Company) UserConnection.getUser(company_id);
        this.addTitle = addTitle;
        this.cargoType = cargoType;
        this.dueDate = dueDate;
        this.requests = requests;
        this.requiredLicense = requiredLicense;
        this.content = content;
        this.experience = experience;

    }
    //user view
    public Advertisement(Company owner, String addTitle,String addContent,String requiredLicense, String cargoType, Date dueDate, int experience){
        this.advertisementID = AdvertisementConnection.addAdvertisement(owner.getUserId(), addTitle, cargoType, addContent, dueDate, requiredLicense, experience);
        this.owner = owner;
        this.addTitle = addTitle;
        this.cargoType = cargoType;
        this.dueDate = dueDate;
        this.content = addContent;
        this.requiredLicense = requiredLicense;
        this.requests = new ArrayList<>();
        this.experience = experience;

    }

    public void deleteAdvertisement(){
        AdvertisementConnection.deleteAdvertisement(advertisementID);
    }

     //TODO addRequest
    public void addRequest(Request request){
        RequestConnection.sendJobRequestToAdd(request.getSender().getUserId(), request.getAdd().getAdvertisementID());
    }
    //TODO getAllRequests
    public void getAllRequests(Driver driver){
        RequestConnection.getRequestsFromDriver(driver.getUserId(),"PENDING");
        RequestConnection.getRequestsFromDriver(driver.getUserId(),"ACCEPTED");
        RequestConnection.getRequestsFromDriver(driver.getUserId(),"REJECTED");
        RequestConnection.getRequestsFromDriver(driver.getUserId(),null);
    }

    // Getter methods
    public int getAdvertisementID() {
        return advertisementID;
    }

    public Company getOwner() {
        return owner;
    }

    public String getAddTitle() {
        return addTitle;
    }

    public String getContent() {
        return content;
    }

    public String getCargoType() {
        return cargoType;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    // Setter methods
    public void setAdvertisementID(int advertisementID) {
        this.advertisementID = advertisementID;
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }

    public void setAddTitle(String addTitle) {
        this.addTitle = addTitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

}
