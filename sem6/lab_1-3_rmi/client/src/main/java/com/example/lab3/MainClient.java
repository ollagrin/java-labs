package com.example.lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainClient extends Application {
    public static final String UNIQUE_BINDING_NAME = "StudentService";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 707, 447);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}