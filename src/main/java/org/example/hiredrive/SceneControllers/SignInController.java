package org.example.hiredrive.SceneControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.UserConnection;

public class SignInController {

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signIn;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Text wrongPrompt;

    @FXML
    void LinkClicked(ActionEvent event) {
        createScene("/org/example/hiredrive/Scenes/HomePage.fxml");

    }

    @FXML
    void signInBtnClicked(ActionEvent event) {

        if(UserConnection.checkPassword(mailField.getText() , passwordField.getText())) {

            System.out.println("completed");
            Stage main = (Stage) signIn.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/HomePage.fxml");
            
            wrongPrompt.setVisible(false);
        }
        else {
            wrongPrompt.setVisible(true);
        }

    }
    //helper
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
