package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Company;

//Company searching for profiles
public class CompanySearchController extends SuperSceneController{

    @FXML
    private Slider ageSelector;

    @FXML
    private DatePicker endDateFilter;

    @FXML
    private TitledPane experienceLevelFilter;

    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutButton;

    @FXML
    private Spinner<?> maxDistanceFilter;

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

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {

        } else if(event.getSource() == logOutButton) {
            company = null;
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
        }else if(event.getSource() == searchByFilterButton) {

        }

    }

    @Override
    public void setData(Object o){
        company = (Company) o;
    }

}
