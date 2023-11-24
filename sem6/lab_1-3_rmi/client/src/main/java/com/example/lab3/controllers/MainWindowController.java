package com.example.lab3.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.lab3.MainClient;
import com.example.lab3.domain.Student;
import com.example.lab3.service.DBHandlerInterface;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController {
    private DBHandlerInterface handler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Student, Integer> colGroup;

    @FXML
    private TableColumn<Student, Integer> colId;

    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableColumn<Student, String> colSName;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteLosersButton;

    @FXML
    private Button gradesButton;

    @FXML
    private Button showButton;

    @FXML
    private TableView<Student> table;

    @FXML
    private TextArea textArea;

    public MainWindowController() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(7788);
        handler = (DBHandlerInterface) registry.lookup(MainClient.UNIQUE_BINDING_NAME);
    }

    @FXML
    void addStudent(ActionEvent event) throws IOException {
        Stage createStage = new Stage();
        createStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("add-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 377, 260);

        createStage.setTitle("Create Window");
        createStage.setScene(scene);
        createStage.showAndWait();

        update();
    }

    @FXML
    void goToGradesWindow(ActionEvent event) throws IOException {
        Stage createStage = new Stage();
        createStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("grades-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 492, 337);

        createStage.setTitle("Add Window");
        createStage.setScene(scene);
        createStage.showAndWait();

        //AddStudentController addController = fxmlLoader.getController();
        //update();
    }

    @FXML
    void initialize() throws RemoteException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        //table.getColumns().add(colId);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //table.getColumns().add(colName);
        colSName.setCellValueFactory(new PropertyValueFactory<>("surname"));
        //table.getColumns().add(colSName);
        colGroup.setCellValueFactory(new PropertyValueFactory<>("groupNumber"));
        //table.getColumns().add(colGroup);

        update();

        deleteButton.setOnAction(actionEvent -> {
            TableView.TableViewSelectionModel<Student> it = table.getSelectionModel();
            try {
                handler.deleteStudent(it.getSelectedItem().getId());
                update();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        showButton.setOnAction(ActionEvent -> {
            textArea.clear();
            textArea.appendText("Losers :\n");
            try {
                List<Student> losers = handler.getLosers();
                Set<Student> losersSet = new HashSet<>(losers);
                StringBuilder stringBuilder = new StringBuilder();
                for (Student loser : losersSet) {
                    stringBuilder.
                            append(loser.getName()).
                            append(" ").
                            append(loser.getSurname()).
                            append(" group №").
                            append(loser.getGroupNumber()).
                            append("\n");
                }
                textArea.appendText(stringBuilder.toString());
            } catch (SQLException | RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        deleteLosersButton.setOnAction(ActionEvent -> {
            try {
                handler.deleteLosers();
                textArea.clear();
                update();
            } catch (SQLException | RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void update() throws RemoteException {
        table.setItems(FXCollections.observableList(handler.getAllStudents()));
    }

}
