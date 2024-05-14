package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FilteredDriverAddsController extends SuperSceneController{

    @FXML
    private ChoiceBox<?> addOrderSelector;

    @FXML
    private VBox addShowFrame;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutButton;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Button searchByNameButton;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {


            Stage main = (Stage) goMainPageScene.getScene().getWindow();

            createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml");
            main.close();

        }
        else if(event.getSource() == logOutButton) {

            Stage main = (Stage) logOutButton.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");

        }
        else if(event.getSource() == myProfileButton) {

            Stage main = (Stage) myProfileButton.getScene().getWindow();

            main.close();
            createScene("/org/example/hiredrive/Scenes/Profile Page.fxml");

        }
    }

}
