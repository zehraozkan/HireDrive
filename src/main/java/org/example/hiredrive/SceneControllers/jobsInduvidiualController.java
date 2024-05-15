package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.example.hiredrive.users.Driver;

public class jobsInduvidiualController extends  SuperSceneController{

    @FXML
    private Label IDText;

    @FXML
    private Button chatButton;

    @FXML
    private HBox getHBox;

    @FXML
    private Label nameSurnameText;

    @FXML
    private ImageView ratingPng;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == chatButton) {
            createScene("");
        }
    }

    public void setData(Driver driver) {

        this.IDText.setText(driver.getUserId() + "");
        this.nameSurnameText.setText(driver.getUsername());
    }

}
