
package pisti;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

       private ArrayList<Card> cards;
       private int cardPlace; // tracks place in deck for purpose of dealing 
    
    // deck of 52 cards from Card class
    public Deck() {
        cards = new ArrayList<>();
        for (int cardNumber = 1; cardNumber <= 52; cardNumber++) {
            cards.add(new Card(cardNumber));
        }
        cardPlace = -1;
    }
    
    // shuffle the deck
    public void shuffleCards() {
        Collections.shuffle(cards);
    }
    
    // deal next card in deck
    public Card dealCard() {
        cardPlace ++;
        return cards.get(cardPlace);
    }
}
