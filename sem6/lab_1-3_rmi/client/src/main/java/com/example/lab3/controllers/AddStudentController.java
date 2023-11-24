package com.example.lab3.controllers;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.lab3.MainClient;
import com.example.lab3.domain.Student;
import com.example.lab3.service.DBHandlerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {
    private DBHandlerInterface handler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField groupField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField sNameField;

    public AddStudentController() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(7788);
        handler = (DBHandlerInterface) registry.lookup(MainClient.UNIQUE_BINDING_NAME);
    }

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
            int id = 0;
            try {
                id = handler.getNewId();
            } catch (SQLException | RemoteException e) {
                throw new RuntimeException(e);
            }
            String name = nameField.getText();
            String surname = sNameField.getText();
            int group = Integer.parseInt(groupField.getText());

            Student student = new Student(id, name, surname, group);

            try {
                handler.addStudent(student);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            Stage st =(Stage) addButton.getScene().getWindow();
            st.close();
        });

    }

}
