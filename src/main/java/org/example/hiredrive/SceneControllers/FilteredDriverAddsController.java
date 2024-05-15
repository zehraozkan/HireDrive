package org.example.hiredrive.SceneControllers;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

import java.io.IOException;
import java.util.ArrayList;

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

    private SuperSceneController prevScene;
    private Company company;
    private Filter filter;
    private ArrayList<Driver> matchingDrivers;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {


            Stage main = (Stage) goMainPageScene.getScene().getWindow();

            createScene("/org/example/hiredrive/Scenes/Search Page Company.fxml");
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
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml");

        }
    }

    public void initialize() {

        if (matchingDrivers != null && !matchingDrivers.isEmpty()) {
            addShowFrame.getChildren().clear();

            for (Driver driver : matchingDrivers) {
                try{

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ScenesDriver Add Induvidiual.fxml"));
                    HBox profilePage = loader.load();
                    driverAddIndividiualController driverAddIndController = loader.getController();
                    driverAddIndController.setData(driver);
                    addShowFrame.getChildren().add(profilePage);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else {
            // Eşleşen sürücü yoksa bir mesaj göster
            Label noDriversLabel = new Label("No matching drivers found.");
            addShowFrame.getChildren().add(noDriversLabel);
        }

    }

    @Override
    public void setData(Object data) {
        prevScene = (SuperSceneController) data;
        company = (Company) prevScene.getUserData();
        filter = prevScene.getFilter();
        matchingDrivers = filter.getMatchingDrivers();
    }

}
