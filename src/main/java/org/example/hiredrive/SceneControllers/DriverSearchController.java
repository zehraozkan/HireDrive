package org.example.hiredrive.SceneControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Driver;
import org.example.hiredrive.users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;


public class DriverSearchController extends SuperSceneController{


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
    private RadioButton coldChainCargo;

    @FXML
    private RadioButton dangerousGoodsCargo;

    @FXML
    private RadioButton heavyCargo;

    @FXML
    private RadioButton livestockCargo;

    @FXML
    private RadioButton smallParcelCargo;

    @FXML
    private RadioButton specialHandlingCargo;

    @FXML
    private RadioButton retailCargo;

    @FXML
    private RadioButton valuableCargo;

    @FXML
    private RadioButton vehicleCargo;

    @FXML
    private DatePicker endDateFilter;


    @FXML
    private Button goMainPageScene;

    @FXML
    private Button logOutBtn;

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
    private Slider ratingSlicer;

    @FXML
    private DatePicker startDateFilter;

    @FXML
    private ChoiceBox<String> toBox;

    @FXML
    private ChoiceBox<String> fromBox;


    private Driver driver;
    private ArrayList<CheckBox> checkedLicenses;
    private String checkedCargoType;
    private int ratingMin;
    private int ratingMax;
    private LocalDate minDate;
    private LocalDate maxDate;
    private Filter filter;

    @FXML
    void btn_clicked(ActionEvent event) {
        if (event.getSource()== myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/profilePageDriver.fxml", driver);
            main.close();

            vehicleCargo.isSelected();
        }else if(event.getSource()== searchBtn){
            Stage main = (Stage) searchBtn.getScene().getWindow();
            createFilter();
            createScene("/org/example/hiredrive/Scenes/Filtered Company Adds.fxml", driver);
            main.close();
        }
        else if (event.getSource() == logOutBtn){
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml");
            driver = null;
            main.close();
        }
    }

    public void createFilter() {
        filter = new Filter();
        checkedLicenses = new ArrayList<>();

        if (A.isSelected()) checkedLicenses.add(A);

        if (A1.isSelected()) checkedLicenses.add(A1);

        if (A2.isSelected()) checkedLicenses.add(A2);

        if (B.isSelected()) checkedLicenses.add(B);

        if (B1.isSelected()) checkedLicenses.add(B1);

        if (BE.isSelected()) checkedLicenses.add(BE);

        if (C1.isSelected()) checkedLicenses.add(C1);

        if (C1E.isSelected()) checkedLicenses.add(C1E);

        if (CE.isSelected()) checkedLicenses.add(CE);

        if (D.isSelected()) checkedLicenses.add(D);

        if (D1.isSelected()) checkedLicenses.add(D1);

        if (D1E.isSelected()) checkedLicenses.add(D1E);

        if (DE.isSelected()) checkedLicenses.add(DE);

        if (coldChainCargo.isSelected()) checkedCargoType = (coldChainCargo.getText());

        if (dangerousGoodsCargo.isSelected()) checkedCargoType = (dangerousGoodsCargo.getText());

        if (heavyCargo.isSelected()) checkedCargoType = (heavyCargo.getText());

        if (livestockCargo.isSelected()) checkedCargoType = (livestockCargo.getText());

        if (smallParcelCargo.isSelected()) checkedCargoType = (smallParcelCargo.getText());

        if (specialHandlingCargo.isSelected()) checkedCargoType  = (specialHandlingCargo.getText());

        if (retailCargo.isSelected()) checkedCargoType = (retailCargo.getText());

        if (valuableCargo.isSelected()) checkedCargoType = (valuableCargo.getText());

        if (vehicleCargo.isSelected()) checkedCargoType = (vehicleCargo.getText());

        ratingMin = (int) ratingSlicer.getValue() - 1;
        ratingMax = (int) ratingSlicer.getValue() + 1;

        if(ratingMin < 0) ratingMin = 0;
        if(ratingMax > 5) ratingMax = 5;


        minDate = startDateFilter.getValue();
        maxDate = endDateFilter.getValue();

        if(checkedLicenses!= null) filter.setLicenses(getSelectedLicenseNames(checkedLicenses));
        if(checkedCargoType!= null) filter.setCargoType(checkedCargoType);
        filter.setMinRate(ratingMin);
        filter.setMaxRate(ratingMax);
        if(minDate!= null) filter.setMinDeadline(Date.valueOf(minDate));
        if(maxDate!= null) filter.setMaxDeadline(Date.valueOf(maxDate));
    }

    @Override
    public void setData(Object data){
        driver = (Driver) data;
        update();
    }

    public void update() {
        myProfileButton.setText(driver.getUsername());
    }
    public Filter getFilter() {
        return filter;
    }
    @Override
    public User getUserData(){
        return driver;
    }



    public void initialize() {
        String[] cities = {"Adana", "Ankara", "Antalya", "Bursa", "Denizli", "Diyarbakır", "Erzurum",
                "Eskişehir", "Gaziantep", "Istanbul", "Izmir", "Kayseri", "Kocaeli", "Konya", "Mersin", "Samsun",
                "Şanlıurfa", "Tekirdağ", "Trabzon", "Zonguldak"};


        toBox.setItems(FXCollections.observableArrayList(cities));
        fromBox.setItems(FXCollections.observableArrayList(cities));

    }

    private ArrayList<String> getSelectedLicenseNames(ArrayList<CheckBox> checkedLicenses) {
        ArrayList<String> selectedLicenses = new ArrayList<>();
        for (CheckBox checkBox : checkedLicenses) {
            selectedLicenses.add(checkBox.getText());
        }
        return selectedLicenses;
    }

    private ArrayList<String> getSelectedCargoTypes(ArrayList<CheckBox> checkedCargoType) {
        ArrayList<String> selectedCargoTypes = new ArrayList<>();
        for (CheckBox checkBox : checkedCargoType) {
            selectedCargoTypes.add(checkBox.getText());
        }
        return selectedCargoTypes;
    }
}
