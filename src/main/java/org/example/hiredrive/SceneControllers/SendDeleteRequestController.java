package org.example.hiredrive.SceneControllers;

import org.example.hiredrive.Connection.RequestConnection;
import org.example.hiredrive.advertisement.Advertisement;
import  org.example.hiredrive.users.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SendDeleteRequestController extends SuperSceneController{

    @FXML
    private Button deleteRequestButton;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutButton;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Circle profilePicCircleBig;

    @FXML
    private ImageView ratingPng;

    @FXML
    private VBox requestDeletedFrame;

    @FXML
    private VBox requestOperationsFrame;

    @FXML
    private VBox requestSendedFrame;

    @FXML
    private Button sendRequestButton;

    private Driver driver;
    private Advertisement add;
    
    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {


            Stage main = (Stage) goMainPageScene.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml");
            

        }
        else if(event.getSource() == logOutButton) {

            Stage main = (Stage) logOutButton.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");

        }
        else if(event.getSource() == myProfileButton) {

            Stage main = (Stage) myProfileButton.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml");

        } 
        else if(event.getSource() == sendRequestButton){
            RequestConnection.sendJobRequestToAdd(driver.getUserId(), add.getAdvertisementID());
        }
        else if(event.getSource() == deleteRequestButton){
            RequestConnection.deleteRequest(driver.getUserId(), add.getAdvertisementID());
        }
    }

}
