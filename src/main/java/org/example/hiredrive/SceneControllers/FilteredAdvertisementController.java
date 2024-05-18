package org.example.hiredrive.SceneControllers;

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
import org.example.hiredrive.advertisement.Advertisement;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

import java.io.IOException;
import java.util.ArrayList;

public class FilteredAdvertisementController extends SuperSceneController{

    private Driver driver;
    private Filter filter;
    private  SuperSceneController prevScene;
    private ArrayList<Advertisement> matchingAdds;

    @FXML
    private VBox add_box;


    @FXML
    private ChoiceBox<?> addOrderSelector;

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
        if (event.getSource()== myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/profilePageDriver.fxml", driver);
            main.close();

        }
        else if (event.getSource() == logOutButton){
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
            driver = null;
            main.close();
        }
        else if (event.getSource() == goMainPageScene) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/Search Page Driver.fxml", driver);
            main.close();
        }
    }

    @Override
    public void setData(Object data){
        prevScene = (SuperSceneController) data;
        driver = (Driver) prevScene.getUserData();
        filter = prevScene.getFilter();
        myProfileButton.setText(driver.getUsername());
        matchingAdds = filter.getMatchingAds();
        update();

    }
    public void update() {

        for (Advertisement add : matchingAdds) {
            try{

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/org/example/hiredrive/Scenes/Company Add Induvidual.fxml"));
                HBox profilePage = loader.load();

                CompanyAddIndividualController driverAddIndController = loader.getController();

                driverAddIndController.setData(add);

                add_box.getChildren().add(profilePage);

            }catch (IOException e){
                System.out.println(e);
            }
        }
    }




}
