package org.example.hiredrive.advertisement;

import java.sql.Date;

public class Filter {
    private int jobID;
    private String location;
    private String description;
    private String industry;
    private String requirements;
    private Enum experienceLevel;
    private Date deadline;
    private boolean isActive;

    public Filter(int jobID, String description, Date deadline, boolean isActive){
        this.jobID = jobID;
        this.description = description;
        this.deadline = deadline;
        this.isActive = isActive;
    }

    private void updateListing(){
      //to be done
    }

    private void closeListing(){
      //to be done
    }

    private void setFilterCriteria(String requirements, String industry, String location, Enum experienceLevel){
        this.location = location;
        this.industry = industry;
        this.requirements = requirements;
        this.experienceLevel = experienceLevel;
    }
}
