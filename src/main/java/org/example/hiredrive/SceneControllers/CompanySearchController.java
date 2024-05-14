package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
    private Spinner<Integer> maxRate;

    @FXML
    private Spinner<Integer> minRate;

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

    private Company company;
    private int ratingMin;
    private int ratingMax;
    private ArrayList<CheckBox> checkedLicenses;
    private int minExperienceLevel;
    private int maxExperienceLevel;
    private int expLevel;


    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == goMainPageScene) {

        } else if(event.getSource() == logOutButton) {
            company = null;
            createScene("/org/example/hiredrive/Scenes/entranceScene.fxml", this);
            Stage main = (Stage) logOutButton.getScene().getWindow();
            main.close();

            setData();

        }else if(event.getSource() == searchByFilterButton) {
            
            //createScene("/org/example/hiredrive/Scenes/Filtered Company Adds.fxml", filteredDriver);
        }else if(event.getSource() == searchByNameButton) {

        }else if (event.getSource() == myProfileButton) {
            Stage main = (Stage) myProfileButton.getScene().getWindow();
            createScene("/org/example/hiredrive/Scenes/ProfilePageCompany.fxml", this);
            main.close();
        }
    }

    
    public void setData(){

        checkedLicenses = new ArrayList<CheckBox>();
        Filter filter = new Filter();

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

        ratingMin = (Integer) minRate.getValue();
        ratingMax = (Integer) maxRate.getValue();

        minExperienceLevel = (int) experienceYear.getMin();
        maxExperienceLevel = (int) experienceYear.getMax();
        expLevel = (int) experienceYear.getValue();


        filter.setLicenses(getSelectedLicenseNames(checkedLicenses));
        filter.setMinRate(ratingMin);
        filter.setMaxRate(ratingMax);
        filter.setMinExperienceLevel(minExperienceLevel);
        filter.setMaxExperienceLevel(maxExperienceLevel);
        filter.setExperienceLevel(expLevel);
    }

    private ArrayList<String> getSelectedLicenseNames(ArrayList<CheckBox> checkedLicenses) {
        ArrayList<String> selectedLicenses = new ArrayList<>();
        for (CheckBox checkBox : checkedLicenses) {
            selectedLicenses.add(checkBox.getText());
        }
        return selectedLicenses;
    }
}
