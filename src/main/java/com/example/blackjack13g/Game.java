package com.example.blackjack13g;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        Scanner sc = new Scanner(System.in);

        while (true) {
            player.clearHand();
            dealer.clearHand();
            player.addCard(deck.draw());
            dealer.addCard(deck.draw());
            player.addCard(deck.draw());
            dealer.hiddenCard(deck.draw());
            player.calculateHand();
            dealer.calculateHand();

            if (player.getHandValue() != 21) {

                if (dealer.getHandValue() == 21) {
                    player.loseHP();
                } else {
                    //players draws
                    while (player.getHandValue() <= 21) {
                        System.out.println("add or fold");
                        if (sc.nextLine().equals("add")) {
                            player.addCard(deck.draw());
                        } else {
                            //dealer draws
                            // <--- hier methode zum aufdecken
                            while (dealer.getHandValue() < 17) {
                                dealer.addCard(deck.draw());
                                dealer.calculateHand();
                            }

                            if (dealer.getHandValue() > 21) {
                                dealer.loseHP();
                            } else if (dealer.getHandValue() < player.getHandValue()) {
                                dealer.loseHP();
                            } else {
                                player.loseHP();
                            }
                            break;
                        }
                        player.calculateHand();
                    }

                    if (player.getHandValue() > 21) {
                        player.loseHP();
                    }
                }
            } else {
                dealer.loseHP();
            }

            if (player.getHealth() == 0) {
                System.out.println("You died!");
                return;
            }
            if (dealer.getHealth() == 0) {
                System.out.println("You survived!");
                return;
            }

        }
    }
}

