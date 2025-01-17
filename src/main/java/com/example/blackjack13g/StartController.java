package com.example.blackjack13g;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    public Button startButton;
    public Button rulesButton;
    public Button exitButton;

    //Scene Control
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private Label rulesLabel;

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

    public void showRulesScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RulesScreen.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) rulesButton.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
/*
            stage.setMinWidth(740);
            stage.setMinHeight(525);
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitApplication() {
        System.exit(0);
    }

    public void goBackToMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) rulesLabel.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
/*
            stage.setMinWidth(740);
            stage.setMinHeight(525);
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {}

    @FXML
    public void initialize() {
        if (rulesLabel != null) {
            rulesLabel.setText(RULES);
        }
    }
}
