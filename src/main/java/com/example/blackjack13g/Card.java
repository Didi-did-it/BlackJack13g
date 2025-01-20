package com.example.blackjack13g;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private int value;
    private String image;

    public Card(int value) {
        this.value = value;
        switch (value) {
            case 2 -> image = "/images/cards/2-S.png";
            case 3 -> image = "/images/cards/3-S.png";
            case 4 -> image = "/images/cards/4-S.png";
            case 5 -> image = "/images/cards/5-S.png";
            case 6 -> image = "/images/cards/6-S.png";
            case 7 -> image = "/images/cards/7-S.png";
            case 8 -> image = "/images/cards/8-S.png";
            case 9 -> image = "/images/cards/9-S.png";
            case 10 -> image = "/images/cards/10-S.png";
            case 11 -> image = "/images/cards/J-S.png";
            case 12 -> image = "/images/cards/Q-S.png";
            case 13 -> image = "/images/cards/K-S.png";
            case 14 -> image = "/images/cards/A-S.png";
        }
    }

    public String getImage() {
        return image;
    }

    public void getBackCover(){

    }

    public int getValue() {
        return value;
    }
}

