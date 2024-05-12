package org.example.hiredrive.SceneControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SuperSceneController {
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
