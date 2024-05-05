package org.example.hiredrive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);

    }

    @Override

    public void start(Stage stage) throws Exception {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/org/example/hiredrive/Scenes/entranceScene.fxml"));
            Scene scene = new Scene(root);

            stage.setTitle("bkah blaj");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
