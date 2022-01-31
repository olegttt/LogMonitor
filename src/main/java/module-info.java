module com.logmonitor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.logmonitor to javafx.fxml;
    exports com.logmonitor;
}