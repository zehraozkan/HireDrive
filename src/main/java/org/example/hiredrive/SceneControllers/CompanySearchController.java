package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

import java.util.ArrayList;

//Company searching for profiles
public class CompanySearchController extends SuperSceneController{

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
    private DatePicker endDateFilter;

    @FXML
    private TitledPane experienceLevelFilter;

    @FXML
    private TitledPane experienceLevelFilter1;

    @FXML
    private Slider experienceYear;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutButton;

    @FXML
    private Spinner<?> maxRate;

    @FXML
    private Spinner<?> minRate;

    @FXML
    private Button myProfileButton;

    @FXML
    private Circle profilePicCircle;

    @FXML
    private Button searchByFilterButton;

    @FXML
    private Button searchByNameButton;

    @FXML
    private TextField searchByNameTextArea;

    @FXML
    private TextField searchByNameTextArea1;

    @FXML
    private DatePicker startDateFilter;

    private Filter filter;
    private Company company;
    private ArrayList<Driver> filteredDriver;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {

        } else if(event.getSource() == logOutButton) {
            company = null;
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
        }else if(event.getSource() == searchByFilterButton) {
            createFilter();
            createScene("/org/example/hiredrive/Scenes/Filtered Company Adds.fxml", filteredDriver);
        }
    }

    @Override
    public void setData(Object o){
        company = (Company) o;
    }
    public void createFilter() {
        filter = new Filter();
        filteredDriver = filter.getMatchingDrivers();
    }

}
