package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EntranceController {

    @FXML
    private Button btn_signIn;

    @FXML
    private Button btn_signUp;

    @FXML
    private Button goBackScene;

    @FXML
    void buttonClicked(ActionEvent event) {
        if(event.getSource() == btn_signIn) {

            Stage main = (Stage) btn_signIn.getScene().getWindow();

            createScene("/org/example/hiredrive/Scenes/sign in scene.fxml");
            main.close();

        }
        else if(event.getSource() == btn_signUp) {

            Stage main = (Stage) btn_signUp.getScene().getWindow();
            
            main.close();
            createScene("/org/example/hiredrive/Scenes/sign up scene.fxml");
        
        }
        else if(event.getSource() == goBackScene) {

            Stage main = (Stage) btn_signUp.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/sign up scene.fxml");

        }

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
