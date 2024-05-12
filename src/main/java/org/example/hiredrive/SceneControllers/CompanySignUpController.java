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
    private PasswordField passField1;

    @FXML
    private PasswordField passField2;

    @FXML
    private TextField phoneField;

    protected String name;
    protected String pass;
    protected String mail;
    protected String phone;
    protected String user_type;


    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == nextBtn){
            name = cNameField.getText();
            pass = passField1.getText();
            phone = phoneField.getText();
            mail = mailField.getText();
            user_type = "company";
            String pass2 = passField2.getText();
            if(!pass2.equals(pass)) throw new IllegalArgumentException("Passwords do not match");
            if(!MailManager.isValidEmail(mail)) throw new IllegalArgumentException("Illegal mail address");

            createScene("/org/example/hiredrive/Scenes/email authentication.fxml", this);

            Stage main = (Stage) nextBtn.getScene().getWindow();
            main.close();

        }
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
