package org.example.hiredrive.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import org.example.hiredrive.users.Driver;

public class driverAddIndividiualController {

    @FXML
    private Label IDText;

    @FXML
    private HBox getHBox;

    @FXML
    private Label creationDateText;

    @FXML
    private Label experienceOfDriverText;

    @FXML
    private Label nameSurnameText;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private ImageView ratingPng;


    public void setData(Driver driver) {
        this.IDText.equals(driver.getUserId());
        this.nameSurnameText.equals(driver.getUsername());
        this.experienceOfDriverText.equals("Experience " + driver.getExperience());

    }

}
