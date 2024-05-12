package org.example.hiredrive.SceneControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CompanySignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cNameField;

    @FXML
    private Button goBackScene;

    @FXML
    private TextField mailField;

    @FXML
    private Button nextBtn;

    @FXML
    private PasswordField passField1;

    @FXML
    private PasswordField passField2;

    @FXML
    private TextField phoneField;

    @FXML
    void btn_clicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cNameField != null : "fx:id=\"cNameField\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert goBackScene != null : "fx:id=\"goBackScene\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert mailField != null : "fx:id=\"mailField\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert nextBtn != null : "fx:id=\"nextBtn\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert passField1 != null : "fx:id=\"passField1\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert passField2 != null : "fx:id=\"passField2\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert phoneField != null : "fx:id=\"phoneField\" was not injected: check your FXML file 'sign up as company.fxml'.";

    }

}
