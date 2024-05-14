package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.hiredrive.message.MailManager;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.User;

public class verificationController extends SuperSceneController{

    private String email;
    private SuperSceneController prevScene;
    private int verificationCode;
    User user;

    @FXML
    private Button verifyButton;

    @FXML
    private Button backBtn;

    @FXML
    private TextField codeField;

    @FXML
    void btn_clicked(ActionEvent event) {

        if(event.getSource() == verifyButton){
            if(codeField.getText().equals(verificationCode + "")) {
                user = prevScene.getUserData();
                System.out.println("creted user successfully");
                MailManager.messageReturn("Your account has been created! Welcome to HireDrive.", email);
                if(user.getUserType().equals("driver")){
                    createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml", user);
                    Stage main = (Stage) verifyButton.getScene().getWindow();
                    main.close();
                } else if (user.getUserType().equals("company")) {
                    createScene("/org/example/hiredrive/Scenes/Search Page Company.fxml", user);
                    Stage main = (Stage) verifyButton.getScene().getWindow();
                    main.close();
                }
            }
        } else if (event.getSource() == backBtn) {
            Stage main = (Stage) backBtn.getScene().getWindow();
            main.close();
            createScene("/org/example/hiredrive/Scenes/sign up as company.fxml");
        }
    }

    @FXML
    public void initialise() {

    }

    public void setData(Object data) {
        if(data instanceof CompanySignUpController) {
            prevScene = (CompanySignUpController) data;
        } else if (data instanceof DriverSignUpController) {
            prevScene = (DriverSignUpController) data;
        }
        this.email = prevScene.getMail();
        verificationCode = MailManager.sendVerificationMail(email);
        System.out.println(verificationCode);

    }
}
