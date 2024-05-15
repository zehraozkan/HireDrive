package org.example.hiredrive.SceneControllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import org.example.hiredrive.advertisement.Advertisement;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;


public class CompanyAddIndividualController extends SuperSceneController{

    @FXML
    private Label CompanyNameText;

    @FXML
    private Label IDText;

    @FXML
    private Label creationDateText;

    @FXML
    private Label experienceOfDriverText;

    @FXML
    private Label nameSurnameText;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private ImageView ratingPng;

    @FXML
    private Button rating_btn;

    @FXML
    void btn_clicked(ActionEvent event) {
            if(event.getSource() == rating_btn){
                setNewRatingImage();
            }
       
    }
    @FXML
    void mouse_clicked(MouseEvent event) {

    }

    private double rating;

    private void setNewRatingImage() {
        if(rating == 1){
            Image im = new Image("/org/example/hiredrive/pngs/1.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 1.5){
            Image im = new Image("/org/example/hiredrive/pngs/1.5.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 2){
            Image im = new Image("/org/example/hiredrive/pngs/2.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 2.5){
            Image im = new Image("/org/example/hiredrive/pngs/2.5.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 3){
            Image im = new Image("/org/example/hiredrive/pngs/3.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 3.5){
            Image im = new Image("/org/example/hiredrive/pngs/3.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 4){
            Image im = new Image("/org/example/hiredrive/pngs/4.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 4.5){
            Image im = new Image("/org/example/hiredrive/pngs/4.5.png",false);
            ratingPng.setImage(im);
        }
        else if(rating == 5){
            Image im = new Image("/org/example/hiredrive/pngs/5.png",false);
            ratingPng.setImage(im);
        }
    }
    @Override
    public void setData(Object data){
        Advertisement company = (Advertisement) data;
        this.IDText.setText(company.getAddTitle());
        this.nameSurnameText.setText(company.getContent());
        //this.experienceOfDriverText.setText("Experience " + driver.getExperience());

    }
}
