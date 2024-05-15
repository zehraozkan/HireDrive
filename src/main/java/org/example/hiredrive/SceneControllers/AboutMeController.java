package org.example.hiredrive.SceneControllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.users.Driver;

import static org.example.hiredrive.SceneControllers.SignInController.user;


public class AboutMeController extends SuperSceneController{
    @FXML
    private Button backBtn;

    @FXML
    private Button backBtn2;

    @FXML
    private Circle profilePicCircleBig;

    @FXML
    private Label rateInfo;

    @FXML
    private ImageView ratingPng;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    private Label userInfo;

    private Driver driver = (Driver) user;

    @FXML
    void btnClicked(ActionEvent event) {
        if (event.getSource() == backBtn2) {
            createScene("/org/example/hiredrive/Scenes/profilePageDriver.fxml");
            Stage main = (Stage) backBtn.getScene().getWindow();
            main.close();
        }

    }
    public void setData(Object data){
        driver = (Driver) data;
    }

}
