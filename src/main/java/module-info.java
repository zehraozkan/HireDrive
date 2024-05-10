module org.example.hiredrive {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.mail;
    requires mysql.connector.java;

    opens org.example.hiredrive to javafx.fxml;
    exports org.example.hiredrive;
    exports org.example.hiredrive.Connection;
    opens org.example.hiredrive.Connection to javafx.fxml;
    exports org.example.hiredrive.SceneControllers;
    opens org.example.hiredrive.SceneControllers to javafx.fxml;
    exports org.example.hiredrive.users;
    opens org.example.hiredrive.users to javafx.fxml;
    exports org.example.hiredrive.advertisement;
    opens org.example.hiredrive.advertisement to javafx.fxml;
    exports org.example.hiredrive.message;
    opens org.example.hiredrive.message to javafx.fxml;
}