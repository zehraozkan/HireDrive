package org.example.hiredrive.advertisement;

import org.example.hiredrive.users.Driver;

import java.sql.Date;
import java.util.ArrayList;


public class Filter {

    private String from;
    private String destination;
    private int minExperienceLevel;
    private int maxExperienceLevel;
    private int minRate;
    private int maxRate;
    private ArrayList<String> licenses;
    private ArrayList<String> cargoType;
    private Date minDeadline;
    private Date maxDeadline;
    private boolean isUser;
    private boolean isAvailable;
    private ArrayList<Advertisement> resultantsAds;
    private ArrayList<Driver> resultantsDrivers;


  //null constructor, for displayment of every ad
    public Filter(){
        this.from = "%";
        this.destination = "%";
        this.minRate = 0;
        this.maxRate = 5;//MAX_VALUE is impossible
        this.minExperienceLevel = 0;//MIN_VALUE is impossible
        this.maxExperienceLevel = 1000;//MAX_VALUE is impossible
        this.resultantsAds = new ArrayList<Advertisement>();
        this.resultantsDrivers = new ArrayList<Driver>();
        this.cargoType = new ArrayList<String>();
        this.licenses = new ArrayList<String>();
    }

    public ArrayList<Advertisement> getMatchingAds(){
      return resultantsAds;
    }

    public ArrayList<Driver> getMatchingDrivers(){
      return resultantsDrivers;
    }

    public void setFrom(String from) {
      this.from = from;
  }

    public void setDestination(String destination) {
      this.destination = destination;
  }
  
    public void setCargoType(ArrayList<String> cargoType) {
      this.cargoType = cargoType;
  }

    public void setMinExperienceLevel(int minExperienceLevel) {
      this.minExperienceLevel = minExperienceLevel;
  }

    public void setMaxExperienceLevel(int maxExperienceLevel) {
      this.maxExperienceLevel = maxExperienceLevel;
  }

    public void setMinRate(int minRate) {
      this.minRate = minRate;
  }

    public void setMaxRate(int maxRate) {
      this.maxRate = maxRate;
  }

    public void setLicenses(ArrayList<String> licenses) {
      this.licenses = licenses;
  }

    public void setMinDeadline(Date minDeadline) {
      this.minDeadline = minDeadline;
  }

    public void setMaxDeadline(Date maxDeadline) {
      this.maxDeadline = maxDeadline;
  }

    public void setUser(boolean isUser) {
      this.isUser = isUser;
  }

    public void setAvailable(boolean isAvailable) {
      this.isAvailable = isAvailable;
  }

    public void setResultantsAds(ArrayList<Advertisement> resultantsAds) {
      this.resultantsAds = resultantsAds;
  }

    public void setResultantsDrivers(ArrayList<Driver> resultantsDrivers) {
      this.resultantsDrivers = resultantsDrivers;
  }

    public int setExperienceLevel(int expLevel) {
      return expLevel;
    }
}
