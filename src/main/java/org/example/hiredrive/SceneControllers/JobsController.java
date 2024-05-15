package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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

    @FXML
    void btn_clicked(ActionEvent event) {
        if (event.getSource() == myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml", this);
            main.close();
        }
        else if(event.getSource() == goMainPageScene) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml", this);
            main.close();

        } else if(event.getSource() == logOutButton) {
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml", this);
            Stage main = (Stage) logOutButton.getScene().getWindow();
            main.close();
        }
    }

    @FXML
    void search_name(ActionEvent event) {

    }

}
