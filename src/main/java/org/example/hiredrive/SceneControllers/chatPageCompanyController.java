package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;
import org.example.hiredrive.users.User;

public class chatPageCompanyController  extends  SuperSceneController {

    @FXML
    private Button buttonSend;

    @FXML
    private VBox chatBox;

    @FXML
    private Button logOutButton;

    @FXML
    private Button main_btn;

    @FXML
    private TextArea messageBox;

    @FXML
    private Button myProfileButton;

    @FXML
    private VBox profileBox;

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

    private User user;
    private SuperSceneController prevScene;
    /*
    @FXML
    void btn_clicked(ActionEvent event) {

        if(event.getSource() == main_btn) {
            createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml", user);
            Stage main = (Stage) goMainPageScene.getScene().getWindow();
            main.close();
        }else if (event.getSource() == logOutButton) {
            user = null;
            prevScene = null;
            createScene("/org/example/hiredrive/Scenes/entranceScene");
            Stage main = (Stage) logOutButton.getScene().getWindow();
            main.close();

        }else if (event.getSource() == myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml", user);
            main.close();
        }
    }

    @Override
    public void setData(Object data){
        prevScene = (SuperSceneController) data;
        user = (Company) prevScene.getUserData();
        update();
    }
    public void update(){
        myProfileButton.setDisable(true);
        myProfileButton.setText(user.getUsername());
        userInfo.setText(user.getUsername() + " (" + user.getUserType() + ") ");
        try {
            rateInfo.setText(user.getRating() + " rated " + UserConnection.getTotalRated(user.getUserId()));
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void sendButtonAction(ActionEvent event) {
        //sendMessage();
    }

    @FXML
    void sendMethod(KeyEvent event) {

    }*/

}
