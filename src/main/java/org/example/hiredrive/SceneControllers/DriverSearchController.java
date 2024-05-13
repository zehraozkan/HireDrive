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

public class DriverSearchController {

    @FXML
    private DatePicker endDateFilter;

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

    @FXML
    private DatePicker startDateFilter;

    @FXML
    void btn_clicked(ActionEvent event) {

    }

}
