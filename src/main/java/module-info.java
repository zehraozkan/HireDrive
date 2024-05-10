module org.example.hiredrive {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.mail;

    opens org.example.hiredrive to javafx.fxml;
    exports org.example.hiredrive;
    exports org.example.hiredrive.Connection;
    opens org.example.hiredrive.Connection to javafx.fxml;
    exports org.example.hiredrive.SceneControllers;
    opens org.example.hiredrive.SceneControllers to javafx.fxml;
}