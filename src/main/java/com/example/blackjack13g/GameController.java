package com.example.blackjack13g;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    private Player player = new Player();
    private Dealer dealer = new Dealer();
    private Deck deck = new Deck();

    @FXML
    private Button mainButton;
    @FXML
    private Label playerHPLabel;
    @FXML
    private Label dealerHPLabel;
    @FXML
    private Label playerCardValue;
    @FXML
    private Label dealerCardValue;
    @FXML
    private HBox buttonContainer;


    //Method for Main Button Click
    public void goBackToMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) mainButton.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setMinWidth(1280);
            stage.setMinHeight(720);

        } catch (IOException e) {
            throw new RuntimeException("Error loading StartScene.fxml",e);
        }
    }
    //Method for Exit Button Click
    public void exitApplication() {
        System.exit(0);
    }

    public void showHP(){
        if (player != null) {
            int i = player.getHealth();
            playerHPLabel.setText(String.valueOf(i));
        }
        if (dealer != null) {
            int i = dealer.getHealth();
            dealerHPLabel.setText(String.valueOf(i));
        }
    }

    public void showButtons(){
        buttonContainer.getChildren().clear();

        Button hitButton = new Button("Hit");
        hitButton.setPrefHeight(20);
        hitButton.setPrefWidth(120);
        hitButton.setStyle(
                "-fx-font-family:'Courier New';"+
                "-fx-font-size: 16px;"
        );
        //hitButton.setOnAction(event -> handleHit());

        Button standButton = new Button("Stand");
        standButton.setPrefHeight(20);
        standButton.setPrefWidth(120);
        standButton.setStyle(
                "-fx-font-family:'Courier New';"+
                "-fx-font-size: 16px;"
        );
        //standButton.setOnAction(event -> handleHit());

        buttonContainer.getChildren().addAll(hitButton, standButton);
    }

    public void hideButtons(){
        buttonContainer.getChildren().clear();
    }

    public void initialize() {
        if (player != null) {
            showHP();
        }
        if (dealer != null) {
            showHP();
        }
    }
}