package com.example.lab3.controllers;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import com.example.lab3.MainClient;
import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;
import com.example.lab3.domain.Student;
import com.example.lab3.service.DBHandlerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class AddGradeController {

    private DBHandlerInterface handler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//    @FXML
//    private Button addButton;
//
//    @FXML
//    private TextField examField;
//
//    @FXML
//    private TextField gradeField;
//
//    @FXML
//    private TextField studentField;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> examBox;

    @FXML
    private TextField markField;

    @FXML
    private ComboBox<String> studentBox;

    public AddGradeController() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(7788);
        handler = (DBHandlerInterface) registry.lookup(MainClient.UNIQUE_BINDING_NAME);
    }

    @FXML
    void initialize() throws RemoteException {
        ObservableList<String> studentsList = FXCollections.observableArrayList();
        List<Student> students = handler.getAllStudents();
        System.out.println(students);
        ArrayList<String> list = new ArrayList<>();
        for (Student student : students) {
            studentsList.add(student.toString());
        }

        studentBox.setItems(studentsList);
        final Student[] chosenStud = {null};
        studentBox.setOnAction(actionEvent -> {
            chosenStud[0] = students.stream().filter((stud) -> stud.toString().equals(studentBox.getValue())).toList().get(0);
        });

        ObservableList<String> examsList = FXCollections.observableArrayList();
        List<Exam> exams = handler.getAllExams();
        for(Exam exam : exams){
            examsList.add(exam.getExamName());
        }

        examBox.setItems(examsList );
        final Exam[] chosenExam = {null};
        examBox.setOnAction(actionEvent -> {
            chosenExam[0] = exams.stream().filter((ex) -> ex.getExamName().equals(examBox.getValue())).toList().get(0);
        });

        addButton.setOnAction(actionEvent -> {
            int id = 0;
            try {
                id = handler.getNewIdGrade();
            } catch (RemoteException | SQLException e) {
                throw new RuntimeException(e);
            }
            int mark = Integer.parseInt(markField.getText());

            Grade grade = new Grade(id, chosenStud[0].getId(), chosenExam[0].getId(), mark);

            try {
                handler.addGrade(grade);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            Stage st =(Stage) addButton.getScene().getWindow();
            st.close();
        });
    }

}
