package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.SceneControllers.SuperSceneController;

import java.io.File;

public class profilePageDriverController {

    @FXML
    private CheckBox A;

    @FXML
    private CheckBox A1;

    @FXML
    private CheckBox A2;

    @FXML
    private CheckBox B;

    @FXML
    private CheckBox B1;

    @FXML
    private CheckBox BE;

    @FXML
    private CheckBox C1;

    @FXML
    private CheckBox C1E;

    @FXML
    private CheckBox CE;

    @FXML
    private CheckBox D;

    @FXML
    private CheckBox D1;

    @FXML
    private CheckBox D1E;

    @FXML
    private CheckBox DE;

    @FXML
    private Hyperlink aboutButton;

    @FXML
    private Hyperlink addLicencesButton;

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
    private ScrollPane scrollPane;

    @FXML
    private Label userInfo;

    void btn_clicked(ActionEvent event) {
        if(event.getSource() == addLicenseBtn) {

        }else if(event.getSource() == goMainPageScene) {
            createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml", user);
            Stage main = (Stage) goMainPageScene.getScene().getWindow();
            main.close();

        }else if(event.getSource() == addLicencesButton) {
            scrollPane.setDisable(false);
        }

    }
    @FXML
    void mouse_clicked(MouseEvent event) {
        if (event.getSource() == profilePicCircleBig) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Picture");
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                profilePicCircleBig.setFill(new ImagePattern(image));
                // Image im = new Image("/org/example/hiredrive/pngs/user.png",false);
                profilePicCircleBig.setFill(new ImagePattern(image));
            }
        }
    }

    @Override
    public void setData(Object data){
        prevScene = (SuperSceneController) data;
        user = prevScene.getUserData();
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

}
