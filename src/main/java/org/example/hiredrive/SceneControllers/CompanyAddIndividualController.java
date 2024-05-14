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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    private String date;
    private String id;
    private String companyName;
    private String namesurname;
    private String experience;

    @FXML
    void btn_clicked(ActionEvent event) {
            if(event.getSource() == rating_btn){
                setNewRatingImage();
            }
       
    }

    private void setNewRatingImage() {
    }

   
}
