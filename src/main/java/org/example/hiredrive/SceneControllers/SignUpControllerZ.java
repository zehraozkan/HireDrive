package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.UserConnection;

import java.sql.Date;
import java.time.LocalDate;

public class SignUpControllerZ {

    @FXML
    private Button codeButton;

    @FXML
    private TextField mailField1;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField passwordField;

    @FXML
    private CheckBox companyChc;

    @FXML
    void codeClicked(ActionEvent event) {
        
        //check the variables
        Stage main = (Stage) codeButton.getScene().getWindow();
            
        main.close();

        createScene("/org/example/hiredrive/Scenes/VerificationScene.fxml");

        //TODO somehow it checked the verification code and found that it maches

        String userType;
        if(companyChc.isSelected()){
            userType = "company";
        }
        else {
            userType = "driver";
        }

        UserConnection.addUser(nameField.getText(), surnameField.getText(), mailField1.getText(),passwordField.getText(), userType, Date.valueOf(LocalDate.now()));
    }

    //helper
    private void createScene(String path) {
        Stage stage = new Stage();
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);

            stage.setTitle("bkah blaj");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
