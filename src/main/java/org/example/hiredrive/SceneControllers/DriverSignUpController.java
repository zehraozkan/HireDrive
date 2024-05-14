package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.hiredrive.message.MailManager;
import org.example.hiredrive.users.Driver;
import org.example.hiredrive.users.User;

public class DriverSignUpController extends SuperSceneController {

    @FXML
    private TextField experienceField;

    @FXML
    private Button backBtn;

    @FXML
    private TextField mailField;

    @FXML
    private TextField nameField;

    @FXML
    private Button nextBtn;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField phoneField;



    protected String name;
    protected String surname;
    protected String pass;
    protected String mail;
    protected String phone;
    protected int experience;



    @FXML
    void btnClicked(ActionEvent event) {
        if (event.getSource() == nextBtn) {
            name = nameField.getText();
            surname = surnameField.getText();
            pass = password1.getText();
            phone = phoneField.getText();
            mail = mailField.getText();
            experience = Integer.parseInt(experienceField.getText());

            String pass2 = password2.getText();

            if (!pass2.equals(pass)) throw new IllegalArgumentException("Passwords do not match");
            if (!MailManager.isValidEmail(mail)) throw new IllegalArgumentException("Illegal mail address");

            createScene("/org/example/hiredrive/Scenes/email authentication.fxml", this);

            Stage main = (Stage) nextBtn.getScene().getWindow();
            main.close();

        }
        else if (event.getSource() == backBtn) {
            Stage main = (Stage) backBtn.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/sign up scene.fxml");
            main.close();
        }
    }
    @Override
    public User getUserData() {
        return new Driver(name, surname, pass , mail, phone, experience);
    }
    @Override
    public String getMail(){
        return mail;
    }
}
