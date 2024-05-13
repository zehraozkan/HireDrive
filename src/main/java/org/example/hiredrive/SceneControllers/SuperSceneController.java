package org.example.hiredrive.SceneControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.hiredrive.users.User;

public abstract class SuperSceneController {
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
    public void createScene(String path, Object Data) {
        Stage stage = new Stage();
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

            root = loader.load();
            SuperSceneController controller = loader.getController();

            Scene scene = new Scene(root);

            stage.setTitle("bkah blaj");
            stage.setScene(scene);
            controller.setData(Data);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setData(Object Data) {}
    public User getUserData() {return null;}
    public String getMail() {return null;}

   // public abstract void sendData();
}
