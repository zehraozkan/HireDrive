package org.example.hiredrive.SceneControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.hiredrive.message.MailManager;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.User;

public class CompanySignUpController extends SuperSceneController{

    @FXML
    private TextField cNameField;

    @FXML
    private Button goBackScene;

    @FXML
    private TextField mailField;

    @FXML
    private Button nextBtn;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField phoneField;

    protected String name;
    protected String pass;
    protected String mail;
    protected String phone;


    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == nextBtn){
            name = cNameField.getText();
            pass = password1.getText();
            phone = phoneField.getText();
            mail = mailField.getText();
            String pass2 = password2.getText();

            if(!pass2.equals(pass)) throw new IllegalArgumentException("Passwords do not match");
            if(!MailManager.isValidEmail(mail)) throw new IllegalArgumentException("Illegal mail address");

            createScene("/org/example/hiredrive/Scenes/email authentication.fxml", this);

            Stage main = (Stage) nextBtn.getScene().getWindow();
            main.close();

        } else if (event.getSource() == goBackScene) {
            Stage main = (Stage) goBackScene.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/sign up scene.fxml");
            main.close();
        }
    }

    @FXML
    void initialize() {
        assert cNameField != null : "fx:id=\"cNameField\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert goBackScene != null : "fx:id=\"goBackScene\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert mailField != null : "fx:id=\"mailField\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert nextBtn != null : "fx:id=\"nextBtn\" was not injected: check your FXML file 'sign up as company.fxml'.";
        assert phoneField != null : "fx:id=\"phoneField\" was not injected: check your FXML file 'sign up as company.fxml'.";

    }

    @Override
    public User getUserData() {
        return new Company(name, pass, mail, phone);
    }
    @Override
    public String getMail(){
        return mail;
    }
}
