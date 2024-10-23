module com.assignment.tictactoe.service.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.assignment.tictactoe.service.Controller to javafx.fxml;
    exports com.assignment.tictactoe.service;
}