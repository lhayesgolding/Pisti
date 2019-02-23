package pisti;

import javafx.scene.image.ImageView;


public class Card {

    private ImageView cardImage;
    private int rankOfCard;
    private int suitOfCard; //0=spades, 1=hearts, 2=diamonds, 3=clubs
    
    // each card has an image, rank, and suit
    public Card(int cardNumber) {
        String cardNumberStr = String.valueOf(cardNumber);
        String cardFile = "cards/" + cardNumberStr + ".png";
        cardImage = new ImageView(cardFile);
        rankOfCard = cardNumber % 13;
        suitOfCard = cardNumber / 13;
        if (cardNumber % 13 == 0)
            suitOfCard -= 1;
    }

    public ImageView getCardImage() {
        return cardImage;
    }

    public int getRankOfCard() {
        return rankOfCard;
    }

    public int getSuitOfCard() {
        return suitOfCard;
    }
    
    
}
