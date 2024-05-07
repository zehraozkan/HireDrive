package org.example.hiredrive;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.UserConnection;

public class SignInController {

    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signIn;

    @FXML
    private Text wrongPrompt;


    @FXML
    void signInClicked(ActionEvent event) {

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
