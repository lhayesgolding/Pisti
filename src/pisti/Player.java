package pisti;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> cardsCaptured;
    private ArrayList<Card> pistiCaptured;
    private int score;
    
    public Player() {
        cardsInHand = new ArrayList<Card>();
        cardsCaptured = new ArrayList<Card>();
        pistiCaptured = new ArrayList<Card>();
        score = 0;
    }

    public Card getCardInHand(int i) {
        return cardsInHand.get(i);
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Card> getCardsCaptured() {
        return cardsCaptured;
    }
    
    public int getCardsInHandSize() {
        return cardsInHand.size();
    }
    
    public int getCardsCapturedSize() {
        return cardsCaptured.size();
    }
    
    public int getPistiCapturedSize() {
        return pistiCaptured.size();
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
    
    public void addCardToHand(Card card) {
        cardsInHand.add(card);
    }
    
    public void removeCardFromHand(Card card) {
        cardsInHand.remove(card);
    }
    
    public void addAllToPistiCaptured(ArrayList<Card> addedArray) {
        pistiCaptured.addAll(addedArray);
    }
    
    public void addAllToCardsCaptured(ArrayList<Card> addedArray) {
        cardsCaptured.addAll(addedArray);
    }
}
