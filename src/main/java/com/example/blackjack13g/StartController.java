package com.example.blackjack13g;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Label rulesLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button rulesButton;



    //Define Rules for Text Area
    private static final String RULES = """
            
            Objective: Get as close to 21 as possible without exceeding it.
            
            Card Values:
            Number cards = Face value (2-10)
            Aces = 1 or 11 (whichever is better for your hand).
            
            Gameplay:
            Dealer must hit until reaching at least 17.
            You can hit (take a card) or stand (keep your hand).
            
            Health Points (HP):
            Win: Dealer loses HP.
            Lose: You lose HP.
            
            Good luck! Stay alive!
            """;
    //Method for Rules Button Click
    public void showRulesScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RulesScreen.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) rulesButton.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setMinWidth(1280);
            stage.setMinHeight(720);

        } catch (IOException e) {
            throw new RuntimeException("Error loading RulesScreen.fxml",e);
        }
    }
    //Method for Exit Button Click
    public void exitApplication() {
        System.exit(0);
    }
    //Method to go Back to Main Menu from Rules Screen
    public void goBackToMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) rulesLabel.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setMinWidth(1280);
            stage.setMinHeight(720);

        } catch (IOException e) {
            throw new RuntimeException("Error loading StartScene.fxml",e);
        }
    }
    //Method for StartGame on ButtonClick to GameBoard
    public void goToGameBoard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) startButton.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.setMinWidth(1280);
            stage.setMinHeight(720);

        } catch (IOException e) {
            throw new RuntimeException("Error loading GameBoard.fxml",e);
        }
    }
    //Set Text for Rules Screen
    @FXML
    public void initialize() {
        if (rulesLabel != null) {
            rulesLabel.setText(RULES);
        }
    }
}
