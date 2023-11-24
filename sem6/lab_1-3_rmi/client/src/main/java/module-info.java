module com.example.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;


    opens com.example.lab3 to javafx.fxml;
    exports com.example.lab3;
    exports com.example.lab3.controllers;
    opens com.example.lab3.controllers to javafx.fxml;
    exports com.example.lab3.domain;
    opens com.example.lab3.domain to javafx.fxml;
}