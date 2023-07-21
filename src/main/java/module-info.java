module com.example.recipebookapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.recipebookapplication to javafx.fxml;
    exports com.example.recipebookapplication;
    exports com.example.domain;
    opens com.example.domain to javafx.fxml;
}