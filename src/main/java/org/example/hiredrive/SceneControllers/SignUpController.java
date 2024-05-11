package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignUpController {

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
            createScene("/org/example/hiredrive/Scenes/sign up as driver.fxml");
        }

    }

    public void createScene(String path) {
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