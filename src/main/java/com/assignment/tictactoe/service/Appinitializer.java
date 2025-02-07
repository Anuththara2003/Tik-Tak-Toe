package com.assignment.tictactoe.service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Appinitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"));

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();

    Image image = new Image(getClass().getResourceAsStream("/image/image.jpg"));
    stage.getIcons().add(image);
    stage.setResizable(false);
    stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

