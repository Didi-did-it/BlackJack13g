package com.example.blackjack13g;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int handValue;
    private  int health;
    private final List<Card> hand;

    public Player() {
        hand = new ArrayList<>();
        health = 3;
        handValue = 0;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHandValue() {
        return handValue;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public void loseHP() {
        health--;
    }

    //calculates the value of the hand, considers ace's aswell
    public void calculateHand() {
        handValue = 0;
        int aceCount = 0;
        if (!hand.isEmpty()) {
            for (Card card : hand) {
                int cardValue = card.getValue();

                if (cardValue <= 10) {
                    handValue += cardValue;
                } else if (cardValue <= 13) {
                    handValue += 10;
                } else {
                    handValue += 11;
                    aceCount++;
                }
            }

            while (handValue > 21 && aceCount > 0) {
                handValue -= 10;
                aceCount--;
            }
        }
    }
}
