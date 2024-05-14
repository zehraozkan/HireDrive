package org.example.hiredrive.SceneControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.users.User;

public class SignInController extends SuperSceneController{

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signIn;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Label wrongPrompt;

    private User user;

    @FXML
    void LinkClicked(ActionEvent event) {

        Stage main = (Stage) signUpLink.getScene().getWindow();

        createScene("/org/example/hiredrive/Scenes/sign up scene.fxml");

        main.close();

    }

    @FXML
    void signInBtnClicked(ActionEvent event) {

        if(UserConnection.checkPassword(mailField.getText() , passwordField.getText())) {

            System.out.println("completed");
            Stage main = (Stage) signIn.getScene().getWindow();
            user = UserConnection.getUser(mailField.getText());

            //TODO OOP mehot can only be added after deleting old ones
            if(user.getUserType().equals("driver")){
                createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml", user);

            }
            else{
                createScene("/org/example/hiredrive/Scenes/Search Page Company.fxml");
            }
            main.close();
            wrongPrompt.setVisible(false);
        }
        else {
            wrongPrompt.setVisible(true);
        }
    }
}
