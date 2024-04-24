package org.example.hiredrive;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

        if(DatabaseCheck.checkPassword(mailField.getText() , passwordField.getText())) {

            System.out.println("completed");
            
            wrongPrompt.setVisible(false);

        }
        else {
            
            wrongPrompt.setVisible(true);
        }

    }

}
