package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class verificationController {

    @FXML
    private Button OKbtn;

    @FXML
    private TextField codeField;

    @FXML
    void okClicked(ActionEvent event) {

        if(codeField.getText().equals("123456")) {

            Stage main = (Stage) OKbtn.getScene().getWindow();
            
            main.close();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
            
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
