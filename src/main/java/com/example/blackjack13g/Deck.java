package com.example.blackjack13g;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private Map<Integer,String> cardMap = new HashMap<>();
    private String backCover;
    public Deck() {
        // 2 - 10 are the number cards, 11 - 13 Jack to King, and 14 is Ace
        int[] values = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        /*
        *Assigns each card with the correct image
        * image assignment based on this code | https://stackoverflow.com/questions/29109957/how-to-set-an-image-using-a-hash-map last visit: 18.01.2025
        */
        cardMap.put(2, "/images/cards/2-S.png");
        cardMap.put(3, "/images/cards/3-S.png");
        cardMap.put(4, "/images/cards/4-S.png");
        cardMap.put(5, "/images/cards/5-S.png");
        cardMap.put(6, "/images/cards/6-S.png");
        cardMap.put(7, "/images/cards/7-S.png");
        cardMap.put(8, "/images/cards/8-S.png");
        cardMap.put(9, "/images/cards/9-S.png");
        cardMap.put(10, "/images/cards/10-S.png");
        cardMap.put(11, "/images/cards/J-S.png");
        cardMap.put(12, "/images/cards/Q-S.png");
        cardMap.put(13, "/images/cards/K-S.png");
        cardMap.put(14, "/images/cards/A-S.png");

        //Make BackCover for Cards
        backCover = "/images/cards/BACK.png";

        for (int i = 0; i < 4; i++) {
            for (int value : values) {
                deck.add(new Card(value));
            }
        }

        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.remove(0);
    }

    public String getBackCover(){
        return backCover;
    }

    public String getCardImage(int value){
        return cardMap.getOrDefault(value,"/images/cards/default.png");
    }
}
