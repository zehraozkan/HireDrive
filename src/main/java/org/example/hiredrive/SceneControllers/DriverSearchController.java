package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Driver;
import org.example.hiredrive.users.User;

public class DriverSearchController extends SuperSceneController{

    @FXML
    private DatePicker endDateFilter;

    @FXML
    private TitledPane experienceLevelFilter;

    @FXML
    private TitledPane experienceLevelFilter1;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutBtn;

    @FXML
    private Spinner<?> maxDistanceFilter1;

    @FXML
    private Spinner<?> minRate;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Button searchBtn;

    @FXML
    private Button searchByNameButton;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    private DatePicker startDateFilter;

    private Driver driver;
    private Filter filter;
    
    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {
            
        } else if (event.getSource() == searchBtn) {

        } else if (event.getSource()== myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/profilePageDriver.fxml", this);
            main.close();
        }
    }
    public void update() {
        myProfileButton.setText(driver.getUsername());
    }

    @Override
    public User getUserData(){
        return driver;
    }
    @Override
    public void setData(Object data){
        driver = (Driver) data;
        update();
    }


}
