package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignUpController extends SuperSceneController{

    @FXML
    private Button btnBack;

    @FXML
    private Button signUpCompany;

    @FXML
    private Button signUpDriver;

    @FXML
    void btnClicked(ActionEvent event) {
        if(event.getSource() == signUpCompany){

        } else if (event.getSource() == signUpDriver) {
            Stage main = (Stage) signUpDriver.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/sign up as driver.fxml");

            main.close();
        }

    }
}