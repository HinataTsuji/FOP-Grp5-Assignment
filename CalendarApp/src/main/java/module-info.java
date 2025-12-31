module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.calendarapp to javafx.fxml;
    exports com.mycompany.calendarapp;
}
