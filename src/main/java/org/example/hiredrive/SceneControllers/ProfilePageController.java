package org.example.hiredrive.SceneControllers;

import org.example.hiredrive.users.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class ProfilePageController extends SuperSceneController {

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

    private User user;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {
        } else if(event.getSource() == logOutButton) {
            user = null;

            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
        }else if(event.getSource() == myProfileButton) {
            createScene("/org/example/hiredrive/Scenes/Profile Page.fxml");
        }
    }
}
