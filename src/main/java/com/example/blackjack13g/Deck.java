package com.example.blackjack13g;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    protected String backCover;
    public Deck() {
        // 2 - 10 are the number cards, 11 - 13 Jack to King, and 14 is Ace
        int[] values = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (int i = 0; i < 4; i++) {
            for (int value : values) {
                deck.add(new Card(value));
            }
        }

        Collections.shuffle(deck);
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.remove(0);
    }


}
