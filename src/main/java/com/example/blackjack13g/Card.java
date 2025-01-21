package com.example.blackjack13g;

public class Card {
    private int value;
    private String image;
    private String backImage = "/images/cards/BACK.png";
    private boolean hidden;

    public Card(int value, boolean hidden) {
        this.hidden = hidden;
        this.value = value;
        //this switch chooses the image card based on the value of the card
        switch (value) {
            case 2 -> image = "/images/cards/2.png";
            case 3 -> image = "/images/cards/3.png";
            case 4 -> image = "/images/cards/4.png";
            case 5 -> image = "/images/cards/5.png";
            case 6 -> image = "/images/cards/6.png";
            case 7 -> image = "/images/cards/7.png";
            case 8 -> image = "/images/cards/8.png";
            case 9 -> image = "/images/cards/9.png";
            case 10 -> image = "/images/cards/10.png";
            case 11 -> image = "/images/cards/J.png";
            case 12 -> image = "/images/cards/Q.png";
            case 13 -> image = "/images/cards/K.png";
            case 14 -> image = "/images/cards/A.png";
        }
    }

    public int getValue() {
        return value;
    }

    public String getImage() {
        if (hidden) {
            return backImage;
        }
        return image;
    }

    //methods used to swap the hidden boolean
    public void hide() {
        hidden = true;
    }

    public void unhide() {
        hidden = false;
    }
}

