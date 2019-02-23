package pisti;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> cardsCaptured;
    private ArrayList<Card> pistiCaptured;
    private int score;
    private int capturedCardCount;

    public Player() {
        cardsInHand = new ArrayList<Card>();
        cardsCaptured = new ArrayList<Card>();
        pistiCaptured = new ArrayList<Card>();
        score = 0;
        capturedCardCount = 0;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public ArrayList<Card> getCardsCaptured() {
        return cardsCaptured;
    }

    public void setCardsCaptured(ArrayList<Card> cardsCaptured) {
        this.cardsCaptured = cardsCaptured;
    }

    public ArrayList<Card> getPistiCaptured() {
        return pistiCaptured;
    }

    public void setPistiCaptured(ArrayList<Card> pistiCaptured) {
        this.pistiCaptured = pistiCaptured;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCapturedCardCount() {
        return capturedCardCount;
    }

    public void setCapturedCardCount(int capturedCardCount) {
        this.capturedCardCount = capturedCardCount;
    }
    
    
}
