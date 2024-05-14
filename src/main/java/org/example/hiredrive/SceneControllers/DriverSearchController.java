package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
    private CheckBox coldChainCargo;

    @FXML
    private CheckBox dangerousGoodsCargo;

    @FXML
    private CheckBox heavyCargo;

    @FXML
    private CheckBox livestockCargo;

    @FXML
    private CheckBox smallParcelCargo;

    @FXML
    private CheckBox specialHandlingCargo;

    @FXML
    private CheckBox retailCargo;

    @FXML
    private CheckBox valuableCargo;

    @FXML
    private CheckBox vehicleCargo;

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
    private Spinner<Integer> minPointRating;

    @FXML
    private Spinner<Integer> maxPointRating;

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
    private ArrayList<CheckBox> checkedLicenses;
    private ArrayList<CheckBox> checkedCargoType;
    private int ratingMin;
    private int ratingMax;
    private LocalDate minDate;
    private LocalDate maxDate;

    @FXML
    void btn_clicked(ActionEvent event) {
         if (event.getSource()== myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/profilePageDriver.fxml", this);
            main.close();

            vehicleCargo.isSelected();
        }else if(event.getSource()== searchBtn){




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

        checkedLicenses = new ArrayList<>();
        checkedCargoType = new ArrayList<>();

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

        if (coldChainCargo.isSelected()) checkedCargoType.add(coldChainCargo);

        if (dangerousGoodsCargo.isSelected()) checkedCargoType.add(dangerousGoodsCargo);

        if (heavyCargo.isSelected()) checkedCargoType.add(heavyCargo);

        if (livestockCargo.isSelected()) checkedCargoType.add(livestockCargo);

        if (smallParcelCargo.isSelected()) checkedCargoType.add(smallParcelCargo);

        if (specialHandlingCargo.isSelected()) checkedCargoType.add(specialHandlingCargo);

        if (retailCargo.isSelected()) checkedCargoType.add(retailCargo);

        if (valuableCargo.isSelected()) checkedCargoType.add(valuableCargo);

        if (vehicleCargo.isSelected()) checkedCargoType.add(vehicleCargo);

        ratingMin = (Integer) minPointRating.getValue();
        ratingMax = (Integer) maxPointRating.getValue();

        minDate = startDateFilter.getValue();
        maxDate = endDateFilter.getValue();

        Filter filter = new Filter();
        filter.setLicenses(getSelectedLicenseNames(checkedLicenses));
        filter.setCargoType(getSelectedCargoTypes(checkedCargoType));
        filter.setMinRate(ratingMin);
        filter.setMaxRate(ratingMax);
        filter.setMinDeadline(Date.valueOf(minDate));
        filter.setMaxDeadline(Date.valueOf(maxDate));

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
