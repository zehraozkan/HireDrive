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
            System.out.println(e.getMessage());
        }
    }

    public static class User {
        /**
         * CREATE TABLE users (
         *     user_id INT AUTO_INCREMENT PRIMARY KEY,
         *     user_name VARCHAR(40),
         *     user_surname VARCHAR(40),
         *     date_created DATE,
         *     user_mail VARCHAR(40) UNIQUE,
         *     user_password VARCHAR(20),
         *     user_type VARCHAR(10),
         *     rating DECIMAL(3,1) DEFAULT 0.0,
         *     available VARCHAR(3) DEFAULT 'yes'
         * );
         */


    }
}
