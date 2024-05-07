package org.example.hiredrive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.example.hiredrive.Connection.UserConnection;

public class HomePageController {


    @FXML
    private CheckBox CompanyCheck;

    @FXML
    private CheckBox DriverCheck;

    @FXML
    private Label resultLabel;

    @FXML
    private Button getButton;

    @FXML
    void Checked(ActionEvent event) {

        if(event.getSource() == DriverCheck){
            System.out.println("driver checked");
        }
    }
    @FXML
    void buttonClicked(ActionEvent event) {

        resultLabel.setText(UserConnection.getAllUsers());
    }
}
