package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class verificationController extends SuperSceneController{

    @FXML
    private Button OKbtn;

    @FXML
    private TextField codeField;

    @FXML
    void okClicked(ActionEvent event) {


        int verCode =
        if(codeField.getText().equals("123456")) {

            Stage main = (Stage) OKbtn.getScene().getWindow();
            
            main.close();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
            
        }

    }

        //helper

    

}
