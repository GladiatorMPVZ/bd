module com.example.kursovayabd {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires MaterialFX;
    requires lombok;

    opens people;
    opens com.example.kursovayabd to javafx.fxml;
    exports com.example.kursovayabd;
}