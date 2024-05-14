package org.example.hiredrive.SceneControllers;

import org.example.hiredrive.Connection.RequestConnection;
import org.example.hiredrive.users.Driver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RequestListController extends SuperSceneController{

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutButton;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private RadioButton receivedRequests;

    @FXML
    private ToggleGroup request;

    @FXML
    private Button searchByNameButton;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    private RadioButton sentRequests;

    private Driver driver;

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
        else if(event.getSource() == sentRequests) {
            RequestConnection.getRequestsFromDriver(driver.getUserId(), "PENDING");
            RequestConnection.getRequestsFromDriver(driver.getUserId(), "ACCEPTED");
            RequestConnection.getRequestsFromDriver(driver.getUserId(), "REJECTED");
        }
        else if(event.getSource() == receivedRequests) {
            // sürücüye gönderilen requestleri görmek için method lazım
        }

        
    }

    @FXML
    void search_name(ActionEvent event) {

    }

}
