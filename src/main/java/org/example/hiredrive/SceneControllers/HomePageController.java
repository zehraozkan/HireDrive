package org.example.hiredrive.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.example.hiredrive.Connection.UserConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private CheckBox CompanyCheck;

    @FXML
    private CheckBox DriverCheck;

    @FXML
    private Label resultLabel;

    @FXML
    private Label accountInfo;


    @FXML
    private Button getButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set default text on the label
        accountInfo.setText("");
    }

    @FXML
    void Checked(ActionEvent event) {

        if(event.getSource() == DriverCheck){
            System.out.println("driver checked");
        }
    }
    @FXML
    void buttonClicked(ActionEvent event) {

       // resultLabel.setText(UserConnection.getAllUsers("driver"));
    }
}
