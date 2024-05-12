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
    private String userType;
    private SuperSceneController prevScene;
    User user;

    @FXML
    private Button OKbtn;

    @FXML
    private TextField codeField;

    @FXML
    void okClicked(ActionEvent event) {


        int verCode = MailManager.sendVerificationMail(email);
        if(codeField.getText().equals(verCode + "")) {

            if(userType.equals("user")){

            } else if (userType.equals("company")) {
                user = new Company();
            }
            user = new
            Stage main = (Stage) OKbtn.getScene().getWindow();
            
            main.close();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
            
        }

    }

    @FXML
    public void initialise() {
        //email = codeField.getText();
    }

    public void setData(Object data) {
        if(data instanceof CompanySignUpController) {
            prevScene = (CompanySignUpController) data;
            userType = (CompanySignUpController)(prevScene.userType);
        } else if (data instanceof DriverSignUpController) {
            prevScene = (DriverSignUpController) data;
        }

    }

    

}
