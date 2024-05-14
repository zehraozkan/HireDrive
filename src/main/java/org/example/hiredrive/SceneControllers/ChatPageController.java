package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.MessageConnection;
import org.example.hiredrive.message.Chat;
import org.example.hiredrive.users.Company;

import java.util.ArrayList;

public class ChatPageController extends SuperSceneController {

    @FXML
    private Button buttonSend1;

    @FXML
    private Button logOutButton;

    @FXML
    private Button main_btn;

    @FXML
    private TextArea messageBox1;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Circle profilePicCircle1;

    @FXML
    private Circle profilePicCircle11;

    @FXML
    private Button searchByNameButton;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    private Button view_profile_btn;

    private ArrayList<Company> messageSendedCompanies;


    @FXML
    void btn_clicked(ActionEvent event) {
        if (event.getSource() == main_btn) {
            Stage main = (Stage) main_btn.getScene().getWindow();

        }
    }

    @FXML
    void sendButtonAction(ActionEvent event) {
        //sendMessage();
    }

    @FXML
    void sendMethod(KeyEvent event) {

    }

}