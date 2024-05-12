package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DriverSignUpController extends SuperSceneController{

    String user_type;
    @FXML
    private Button backBtn;

    @FXML
    private TextField mailField;

    @FXML
    private TextField nameField;

    @FXML
    private Button nextBtn;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField surnameField;

    @FXML
    void GetDriverPhoneNumber(ActionEvent event) {

    }

    @FXML
    void btnClicked(ActionEvent event) {
        user_type = "driver";

    }

    @FXML
    void getDriverAdress(ActionEvent event) {

    }

    @FXML
    void getDriverBornDate(ActionEvent event) {

    }

}
