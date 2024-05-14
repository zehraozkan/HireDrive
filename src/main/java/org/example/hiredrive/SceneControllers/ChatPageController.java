package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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

    @FXML
    void btn_clicked(ActionEvent event) {
        if (event.getSource() == main_btn) {
            Stage main = (Stage) main_btn.getScene().getWindow();

        }
    }

    @FXML
    void sendButtonAction(ActionEvent event) {

    }

    @FXML
    void sendMethod(KeyEvent event) {

    }

}