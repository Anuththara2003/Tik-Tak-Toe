package com.assignment.tictactoe.service.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginForm {

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private Button btGoNext;


    @FXML
    void btGoOnAction(ActionEvent event) throws IOException {
        MainAnchorPane.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/GamePage.fxml"));
        MainAnchorPane.getChildren().add(anchorPane);
    }


}
