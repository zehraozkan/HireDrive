package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

import java.io.IOException;
import java.util.ArrayList;

public class JobsController extends SuperSceneController{

    @FXML
    private RadioButton finishedJobs;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutButton;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private ToggleGroup request;

    @FXML
    private Button searchByNameButton;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    private RadioButton unfinishedJobs;
    private SuperSceneController prevScene;
    private Company company;
    private ArrayList<Driver> matchingDrivers;


    @FXML
    void btn_clicked(ActionEvent event) {
        if (event.getSource() == myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml");
            main.close();
        }
        else if(event.getSource() == goMainPageScene) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/Search Page Company.fxml");
            main.close();

        } else if(event.getSource() == logOutButton) {
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
            Stage main = (Stage) logOutButton.getScene().getWindow();
            main.close();
        }
    }

    @FXML
    void search_name(ActionEvent event) {

    }/*
    public void update() {

        for (Driver driver : matchingDrivers) {
            try{

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/org/example/hiredrive/Scenes/DriverAddInduvidiual.fxml"));
                HBox profilePage = loader.load();

                driverAddIndividiualController driverAddIndController = loader.getController();

                driverAddIndController.setData(driver);

                addShowFrame.getChildren().add(profilePage);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setData(Object data) {
        prevScene = (SuperSceneController) data;
        company = (Company) prevScene.getUserData();
        matchingDrivers = company.getWorksWith();
        update();
    }*/

}
