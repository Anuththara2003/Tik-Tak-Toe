package com.assignment.tictactoe.service.Controller;


import com.assignment.tictactoe.service.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class BoardController implements BoardUI  {
    private HumanPlayer humanPlayer;
    private AIPlayer aiPlayer;
    private BoardImpl board;

    @FXML
    private Text AI;

    @FXML
    private Text tie;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    @FXML
    private Text human;

    @FXML
    private Label lab1;

    @FXML
    private Label lab2;

    @FXML
    private Label lab3;

    private int humanScore = 0;
    private int aiScore = 0;
    private int tieScore = 0;

    public BoardController() {
        board = new BoardImpl(this);
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AIPlayer(board);
    }

    @FXML
    public void initialize() {
        updateScoreUi();
    }

    @FXML
    void btOnAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String id = button.getId();
        String cell = id.substring(6);

        int row = -1;
        int col = -1;
        int count = 1;


        L1: for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (count == Integer.parseInt(cell)) {
                        row = i;
                        col = j;
                        break L1;
                    }
                }
                catch (Exception e) {

                }

                count++;
            }
        }

        humanPlayer.move(row, col);
        updateUi();

        if (board.checkWinner() != null) {
            notifyWinner(board.checkWinner().getWinnerPiece());
        } else if (board.isBoardFull()) {
            tieScore++;
            System.out.println("Tie");
            showAlert("Tie");
            updateScoreUi();
        } else {
            aiPlayer.findBestMove();
            board.printBoard();
            updateUi();

            if (board.checkWinner() != null) {
                notifyWinner(board.checkWinner().getWinnerPiece());
            }
        }
    }

    @Override
    public void update(int col, int row, Piece piece) {
        Button[][] button = {{button1, button2, button3}, {button4, button5, button6}, {button7, button8, button9}};

        if (piece == Piece.X) {
            button[row][col].setText("X");
        } else if (piece == Piece.O) {
            button[row][col].setText("O");
        } else {
            button[row][col].setText(" ");
        }
    }

    public void updateUi() {
        for (int i = 0; i < board.getPieces().length; i++) {
            for (int j = 0; j < board.getPieces()[i].length; j++) {
                update(j, i, board.getPieces()[i][j]);
            }
        }
    }

    @Override
    public void notifyWinner(Piece winner) {
        if (winner == Piece.X) {
            humanScore++;
            System.out.println("X is winner");
            showAlert("X is winner");
        } else if (winner == Piece.O) {
            aiScore++;
            System.out.println("O is winner");
            showAlert("O is winner");
        }
        updateScoreUi();
    }

    private void updateScoreUi() {

        try {
            human.setText("" + humanScore);
            AI.setText("" + aiScore);
            tie.setText("" + tieScore);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setOnCloseRequest((DialogEvent event) -> {
            board.initializeBoard();
            updateUi();
        });
        alert.showAndWait();
    }
}
