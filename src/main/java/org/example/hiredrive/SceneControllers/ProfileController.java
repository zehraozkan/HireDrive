package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import org.example.hiredrive.SceneControllers.SuperSceneController;
import org.example.hiredrive.users.User;

public class ProfileController extends SuperSceneController {

    @FXML
    private Hyperlink addLicenseBtn;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Hyperlink job_btn;

    @FXML
    private Button logOutButton;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Circle profilePicCircleBig;

    @FXML
    private Label rateInfo;

    @FXML
    private ImageView ratingPng;

    @FXML
    private Hyperlink requestBtn;

    @FXML
    private Label userInfo;

    private SuperSceneController prevScene;
    private User user;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == addLicenseBtn) {

        }else if(event.getSource() == goMainPageScene) {

        }else if(event.getSource() == job_btn) {

        }

    }

    @Override
    public void setData(Object data){
        prevScene = (SuperSceneController) data;
        prevScene.hide(prevScene.getScene());
        user = prevScene.getUserData();
        this.update();
    }
    public void update(){
        userInfo.setText(user.getUsername() + " (" + user.getUserType() + ") ");
    }

}
