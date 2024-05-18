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
import org.example.hiredrive.users.Driver;

import java.io.File;

public class profilePageDriverController extends  SuperSceneController{

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
    private Hyperlink chat_btn;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label userInfo;

    private Driver user;
    private SuperSceneController prevScene;

    @FXML
    void btn_clicked(ActionEvent event) {

        if(event.getSource() == addLicencesButton) {

            if(scrollPane.isDisable()){
                scrollPane.setDisable(false);
                scrollPane.setVisible(true);
            }else{
                scrollPane.setDisable(true);
                scrollPane.setVisible(false);
            }


        }else if(event.getSource() == goMainPageScene) {
            createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml", user);
            Stage main = (Stage) goMainPageScene.getScene().getWindow();
            main.close();

        }else if(event.getSource() == job_btn) {

        } else if (event.getSource() == logOutButton) {
            user = null;
            prevScene = null;
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml",user);
            Stage main = (Stage) logOutButton.getScene().getWindow();
            main.close();

        }
        else if(event.getSource() == chat_btn) {
            createScene("/org/example/hiredrive/Scenes/Chat Page.fxml");
            Stage main = (Stage) chat_btn.getScene().getWindow();
            main.close();
        }
        else if (event.getSource() == aboutButton){
            createScene("/org/example/hiredrive/Scenes/AboutMe.fxml",user);
            Stage main = (Stage) aboutButton.getScene().getWindow();
            main.close();
        }
        else if(event.getSource() == requestBtn) {
            createScene("/org/example/hiredrive/Scenes/Request List Page.fxml", user);
            Stage main = (Stage) requestBtn.getScene().getWindow();
            main.close();
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
                 Image im = new Image("/org/example/hiredrive/pngs/user.png",false);
                profilePicCircleBig.setFill(new ImagePattern(image));
            }
        }
    }

    @Override
    public void setData(Object data){
        //prevScene = (SuperSceneController) data;
        user = (Driver)data;
        userInfo.setText(user.getUsername());
       // rateInfo.setText(user.getRating() + "");

        update();
    }
    public void update(){
        myProfileButton.setDisable(true);
        myProfileButton.setText(user.getUsername());
        userInfo.setText(user.getUsername() + " \n(" + user.getUserType() + ") ");
        setNewRatingImage(user.getRating());
        try {
            rateInfo.setText(user.getRating() + " rated " + UserConnection.getTotalRated(user.getUserId()));
        }catch(Exception e){
            System.out.println(e);
        }
        updateChecKbox();
    }

    private void updateChecKbox(){
        for(String license : user.getLicenses()){
            if(license.equals("A")){
                A.setSelected(true);
            }else if(license.equals("A1")){
                A1.setSelected(true);
            }else if(license.equals("A2")){
                A2.setSelected(true);
            }else if(license.equals("B")){
                B.setSelected(true);
            }else if(license.equals("BE")){
                BE.setSelected(true);
            }else if(license.equals("B1")){
                B1.setSelected(true);
            }else if(license.equals("C1")){
                C1.setSelected(true);
            }else if(license.equals("C1E")){
                C1E.setSelected(true);
            }else if(license.equals("CE")){
                CE.setSelected(true);
            }else if(license.equals("D")){
                D.setSelected(true);
            }else if(license.equals("D1E")){
                D1.setSelected(true);
            }else if(license.equals("DE")){
                DE.setSelected(true);
            }
        }
    }
    private void setNewRatingImage(double rating) {
        if(rating <= 1){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/1.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 1.5){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/1.5.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 2){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/2.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 2.5){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/2.5.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 3){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/3.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 3.5){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/3.5.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 4){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/4.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 4.5){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/4.5.png"));
            ratingPng.setImage(im);
        }
        else if(rating <= 5){
            Image im = new Image(getClass().getResourceAsStream("/org/example/hiredrive/pngs/5.png"));
            ratingPng.setImage(im);
        }
    }


}
