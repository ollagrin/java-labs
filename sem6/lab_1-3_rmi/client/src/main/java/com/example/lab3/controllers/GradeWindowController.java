package com.example.lab3.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.example.lab3.MainClient;
import com.example.lab3.service.DBHandlerInterface;
import com.example.lab3.domain.Grade;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GradeWindowController {
    private DBHandlerInterface handler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Grade, String> colExam;

    @FXML
    private TableColumn<Grade, Integer> colGrade;

    @FXML
    private TableColumn<Grade, Integer> colGroup;

    @FXML
    private TableColumn<Grade, String> colName;

    @FXML
    private TableColumn<Grade, String> colSurname;

    @FXML
    private TableView<Grade> gradeTable;

    public GradeWindowController() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(7788);
        handler = (DBHandlerInterface) registry.lookup(MainClient.UNIQUE_BINDING_NAME);
    }

    @FXML
    void initialize() throws RemoteException {
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        colGroup.setCellValueFactory(new PropertyValueFactory<>("groupNumber"));
        colExam.setCellValueFactory(new PropertyValueFactory<>("exam"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("mark"));
        update();

        addButton.setOnAction(ActionEvent->{
            Stage createStage = new Stage();
            createStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("add-grade-window.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 352, 248);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            createStage.setTitle("Add Grade Window");
            createStage.setScene(scene);
            createStage.showAndWait();

            try {
                update();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void update() throws RemoteException {
        gradeTable.setItems(FXCollections.observableList(handler.getAllGrades()));

    }

}
