module org.example.hiredrive {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.example.hiredrive to javafx.fxml;
    exports org.example.hiredrive;
}