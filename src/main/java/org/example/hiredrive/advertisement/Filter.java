package org.example.hiredrive.advertisement;

import java.sql.Date;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class Filter {
    private String from;
    private String destination;
    private int minExperienceLevel;
    private int maxExperienceLevel;
    private int minRate;
    private int maxRate;
    private ArrayList<String> licenses;
    private Date minDeadline;
    private Date maxDeadline;
    private boolean isUser;
    private boolean isAvailable;
    private ArrayList<Advertisement> resultantsAds;
    private ArrayList<Driver> resultantsDrivers;


  //null constructor, for displayment of every ad
    public Filter(){
        this.minRate = 0;
        this.maxRate = 5;//MAX_VALUE is impossible
        this.minExperienceLevel = 0;//MIN_VALUE is impossible
        this.maxExperienceLevel = 1000;//MAX_VALUE is impossible
        this.resultantsAds = new ArrayList<Advertisement>();
        this.resultantsDrivers = new ArrayList<Driver>();
        this.licenses = new ArrayList<String>();
    }
    


    private void updateListing(){
      //to be done
    }

    private void closeListing(){
      //to be done
    }

    private void setFilterCriteria(String requirements, String industry, String location, String experienceLevel){
        this.location = location;
        this.industry = industry;
        this.requirements = requirements;
        this.experienceLevel = experienceLevel;
    }

    public String getCargoType(){

    }
    public String getRequiredLicense(){
      
    }
    public String getExperience(){
      
    }
}
