package org.example.hiredrive;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class HomePageController {

    @FXML
    private CheckBox CompanyCheck;

    @FXML
    private CheckBox DriverCheck;

    @FXML
    private TextArea resultArea;

    @FXML
    void Checked(ActionEvent event) {

        if(event.getSource() == DriverCheck){
            System.out.println("driver checked");
        }
    }

}
