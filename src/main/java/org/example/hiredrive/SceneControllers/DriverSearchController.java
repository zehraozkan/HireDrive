package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Driver;

public class DriverSearchController extends SuperSceneController{

    @FXML
    private DatePicker endDateFilter;
    
    @FXML
    private DatePicker startDateFilter;
    
    @FXML
    private TitledPane experienceLevelFilter;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutBtn;

    @FXML
    private Spinner<?> maxDistanceFilter;

    @FXML
    private Spinner<?> minDistanceFilter;

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
    private Filter filter;
    private Driver driver;

    

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {
            
        } else if (event.getSource() == logOutBtn) {
            driver = null;
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
        } else if (event.getSource() == searchBtn) {
            createFilter();
        }
    }
    //searching through advertisement
    public void createFilter(){

    }

    public void initialize() {
        myProfileButton.setText(driver.getUsername());
    }

    @Override
    public void setData(Object data){
        driver = (Driver) data;
        initialize();
    }

}