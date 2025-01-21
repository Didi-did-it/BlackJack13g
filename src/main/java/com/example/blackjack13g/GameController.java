package com.example.blackjack13g;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Optional;


public class GameController {
    private Player player = new Player();
    private Player dealer = new Player();
    private Deck deck;

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
        playerDraw(0);

        if (player.getHandValue() > 21) {
            player.loseHP();
            endRound();
        }
    }

    private void playerDraw(int delay){
        PauseTransition pause = new PauseTransition(Duration.millis(delay));

        pause.setOnFinished(event -> {
            player.addCard(deck.draw());
            player.calculateHand();
            playerCardValue.setText(String.valueOf(player.getHandValue()));

            playerVisualizeCards();
        });

        pause.play();
    }

    private void dealerDraw(boolean hidden, int delay, boolean stand){
        PauseTransition pause = new PauseTransition(Duration.millis(delay));

        pause.setOnFinished(event -> {
            dealer.addCard(!hidden ? deck.draw() : deck.drawHidden());
            dealer.calculateHand();
            if (stand) {
                dealerCardValue.setText(String.valueOf(dealer.getHandValue()));
            }

            dealerVisualizeCards();
            if(stand && dealer.getHandValue() < 17){
                dealerDraw(false, 1000, true);
            }
        });

        pause.play();
    }

    public void playerVisualizeCards(){
        playerCardsContainer.getChildren().clear();

        for (Card card : player.getHand()){
            ImageView cardImage = new ImageView(new Image(getClass().getResourceAsStream(card.getImage())));
            cardImage.setFitWidth(120);
            cardImage.setPreserveRatio(true);
            playerCardsContainer.getChildren().add(cardImage);
        }
    }

    public void dealerVisualizeCards(){
        dealerCardsContainer.getChildren().clear();

        for (Card card : dealer.getHand()){
            ImageView cardImage = new ImageView(new Image(getClass().getResourceAsStream(card.getImage())));
            cardImage.setFitWidth(120);
            cardImage.setPreserveRatio(true);
            dealerCardsContainer.getChildren().add(cardImage);
        }
    }

    public void handleStand(){
        dealer.getHand().get(1).unhide();
        dealerVisualizeCards();

        if (dealer.getHandValue() < 17) {
            dealerDraw(false, 0, true);
        }


        if (dealer.getHandValue() > 21 || dealer.getHandValue() < player.getHandValue()) {
            dealer.loseHP();
            endRound();
        } else if (dealer.getHandValue() == player.getHandValue()) {
            endRound();
        } else {
            player.loseHP();
            endRound();
        }
    }

    public void runGame(){
        dealerCardValue.setText("");
        playerCardValue.setText("");
        player.clearHand();
        dealer.clearHand();
        dealerVisualizeCards();
        playerVisualizeCards();

        playerDraw(500);

        dealerDraw(false, 1000, false);

        playerDraw(1500);

        dealerDraw(true, 2000, false);

        PauseTransition pause = new PauseTransition(Duration.millis(2250));

        pause.setOnFinished(event -> {
            showButtons();
            if (player.getHandValue() ==  21 && dealer.getHandValue() == 21) {
                endRound();
            } else if (dealer.getHandValue() == 21) {
                player.loseHP();
                endRound();
            } else if (player.getHandValue() == 21){
                dealer.loseHP();
                endRound();
            }
        });

        pause.play();
    }

    public void endRound(){
        showHP();
        hideButtons();
        dealer.getHand().get(1).unhide();
        dealerCardValue.setText(String.valueOf(dealer.getHandValue()));
        dealerVisualizeCards();

        if (player.getHealth() == 0) {
            player.setHealth(3);
            dealer.setHealth(3);
            gameEndAlert("You died!");
        } else if (dealer.getHealth() == 0) {
            player.setHealth(3);
            dealer.setHealth(3);
            gameEndAlert("You survived!");
        } else {
            PauseTransition pause = new PauseTransition(Duration.millis(3500));

            pause.setOnFinished(event -> {
                runGame();
            });

            pause.play();
        }
    }

    public void gameEndAlert(String message){
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setResizable(false);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-alignment: center; -fx-font-size: 18px;");
        dialogPane.lookup(".content.label").setStyle("-fx-text-alignment: center; -fx-alignment: center;");

        ButtonType againButton = new ButtonType("Play Again", ButtonBar.ButtonData.OK_DONE);
        ButtonType mainButton = new ButtonType("Main Menu", ButtonBar.ButtonData.OK_DONE);
        ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(againButton, exitButton, mainButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == exitButton) {
            System.exit(0);
        } else if (result.isPresent() && result.get() == mainButton){
            goBackToMain();
        } else {
            initialize();
        }
    }

    public void initialize() {
        showHP();
        deck  = new Deck();
        runGame();
    }
}