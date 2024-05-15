package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import org.example.hiredrive.advertisement.Advertisement;
import org.example.hiredrive.advertisement.Filter;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;

import java.util.ArrayList;

public class FilteredAdvertisementController extends SuperSceneController{

    private Driver driver;
    private Filter filter;
    private  SuperSceneController prevScene;
    private ArrayList<Advertisement> matchingAdds;

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

    }

    @Override
    public void setData(Object data){
        prevScene = (SuperSceneController) data;
        driver = (Driver) prevScene.getUserData();
        filter = prevScene.getFilter();
        matchingAdds = filter.getMatchingAds();

    }


}
