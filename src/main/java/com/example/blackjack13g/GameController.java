package com.example.blackjack13g;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    @FXML
    private HBox playerCardsContainer;
    @FXML
    private HBox dealerCardsContainer;


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
        hitButton.setOnAction(event -> handleHit());

        Button standButton = new Button("Stand");
        standButton.setPrefHeight(20);
        standButton.setPrefWidth(120);
        standButton.setStyle(
                "-fx-font-family:'Courier New';"+
                "-fx-font-size: 16px;"
        );
        standButton.setOnAction(event -> handleStand());

        buttonContainer.getChildren().addAll(hitButton, standButton);
    }

    public void hideButtons(){
        buttonContainer.getChildren().clear();
    }

    public void handleHit(){
        player.addCard(deck.draw());
        player.calculateHand();
        updateCardValues();



        if (player.getHandValue() > 21) {
            player.loseHP();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkGameOver();
        }
        showButtons();
    }

    public void handleStand(){
        while (dealer.getHandValue() < 17) {
            dealer.addCard(deck.draw());
            dealer.calculateHand();
        }

        updateCardValues();


        if (dealer.getHandValue() > 21 || dealer.getHandValue() < player.getHandValue()) {
            dealer.loseHP();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkGameOver();
        } else if (dealer.getHandValue() == player.getHandValue()) {
            dealer.loseHP();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkGameOver();
        } else {
            player.loseHP();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkGameOver();
        }
    }

    public void runGame(){
        player.clearHand();
        dealer.clearHand();

        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.hiddenCard(deck.draw());
        player.calculateHand();
        dealer.calculateHand();

        updateCardValues();

        if (player.getHandValue() ==  21) {
            dealer.loseHP();
            checkGameOver();
        } else if (dealer.getHandValue() == 21) {
            player.loseHP();
            checkGameOver();
        } else {
            showButtons();
        }
    }

    public void updateCardValues(){
        playerCardValue.setText(String.valueOf(player.getHandValue()));
        dealerCardValue.setText(String.valueOf(dealer.getHandValue()));
        visualizeCards();
    }

    public void visualizeCards(){
        playerCardsContainer.getChildren().clear();
        dealerCardsContainer.getChildren().clear();

        for (Card card : player.getHand()){
            ImageView cardImage = new ImageView(new Image(getClass().getResourceAsStream(card.getImage())));
            cardImage.setFitWidth(90);
            cardImage.setPreserveRatio(true);
            playerCardsContainer.getChildren().add(cardImage);
        }

        for (Card card : dealer.getHand()){
            ImageView cardImage = new ImageView(new Image(getClass().getResourceAsStream(card.getImage())));
            cardImage.setFitWidth(90);
            cardImage.setPreserveRatio(true);
            dealerCardsContainer.getChildren().add(cardImage);
        }
    }

    public void checkGameOver(){
        showHP();
        hideButtons();

        if (player.getHealth() == 0) {
            player.setHealth(3);
            dealer.setHealth(3);
            System.out.print("You died!");
        } else if (dealer.getHealth() == 0) {
            player.setHealth(3);
            dealer.setHealth(3);
            System.out.print("You survived!");
        } else {
            runGame();
        }
    }



    public void initialize() {
        showHP();
        deck.shuffle();
        runGame();
    }
}