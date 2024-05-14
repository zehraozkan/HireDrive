package org.example.hiredrive.SceneControllers;

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

    private Driver driver;
    private ArrayList<CheckBox> checkedLicenses;
    private CheckBox checkedCargoType;
    private int ratingMin;
    private int ratingMax;
    private LocalDate minDate;
    private LocalDate maxDate;
    private Filter filter;

    @FXML
    void btn_clicked(ActionEvent event) {
        if (event.getSource()== myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/profilePageDriver.fxml", this);
            main.close();

            vehicleCargo.isSelected();
        }else if(event.getSource()== searchBtn){

            createFilter();
        }
    }


    public void update() {
        myProfileButton.setText(driver.getUsername());
    }

    @Override
    public User getUserData(){
        return driver;
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

        if (coldChainCargo.isSelected()) checkedCargoType.equals(coldChainCargo.getText());

        if (dangerousGoodsCargo.isSelected()) checkedCargoType.equals(dangerousGoodsCargo.getText());

        if (heavyCargo.isSelected()) checkedCargoType.equals(heavyCargo.getText());

        if (livestockCargo.isSelected()) checkedCargoType.equals(livestockCargo.getText());

        if (smallParcelCargo.isSelected()) checkedCargoType.equals(smallParcelCargo.getText());

        if (specialHandlingCargo.isSelected()) checkedCargoType.equals(specialHandlingCargo.getText());

        if (retailCargo.isSelected()) checkedCargoType.equals(retailCargo.getText());

        if (valuableCargo.isSelected()) checkedCargoType.equals(valuableCargo.getText());

        if (vehicleCargo.isSelected()) checkedCargoType.equals(vehicleCargo.getText());

        ratingMin = (int) ratingSlicer.getValue() - 1;
        ratingMax = (int) ratingSlicer.getValue() + 1;

        if(ratingMin < 0) ratingMin = 0;
        if(ratingMax > 5) ratingMax = 5;



        minDate = startDateFilter.getValue();
        maxDate = endDateFilter.getValue();

        filter.setLicenses(getSelectedLicenseNames(checkedLicenses));
        filter.setCargoType(checkedCargoType.getText());
        filter.setMinRate(ratingMin);
        filter.setMaxRate(ratingMax);
        filter.setMinDeadline(Date.valueOf(minDate));
        filter.setMaxDeadline(Date.valueOf(maxDate));
    }

    @Override
    public void setData(Object data){

        driver = (Driver) data;
        update();


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



    /*public ArrayList<CheckBox> getCheckedLicenses(){
        return checkedLicenses;
    }

    public ArrayList<CheckBox> getCheckedCargoType(){
        return checkedCargoType
    }

    public int getRatingMin(){
        return ratingMin;
    }
    public int getRatingMax(){
        return ratingMax;
    }
    public LocalDate getMinDate(){
        return minDate;
    }

    public LocalDate getMaxDate(){
        return minDate;
    }*/






}
