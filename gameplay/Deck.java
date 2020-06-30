package uno.gameplay;

import java.util.*;

/**
 * @author jacobcrawley
 * Stores collection of card objects representing the deck
 */
public class Deck {
    public List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
        // creates 4 wildcards of each type
        for (int i=0; i<4; i++) {
            this.cards.add(new Card("BLACK", "wild"));
            this.cards.add(new Card("BLACK", "wild +4"));
        }
        String[] colours = {"RED","GREEN","BLUE","YELLOW"};
        for (String c : colours) {
            // add 1 zero card of each colour to deck
            this.cards.add(new Card(c, "0"));
            // add 2 cards for numbers 1-9 for each colour
            for (int x = 1; x <= 9; x++) {
                this.cards.add(new Card(c, String.valueOf(x)));
                this.cards.add(new Card(c, String.valueOf(x)));
            }
            // add 2 skip and reverse cards
            for (int y = 0; y < 2; y++) {
                this.cards.add(new Card(c, "skip"));
                this.cards.add(new Card(c, "reverse"));
                this.cards.add(new Card(c, "+2"));
            }
        }
        Deck.Shuffle(this.cards);
    }

    /**
     * Randomly reorders cards
     * @param cards list of cards
     */
    public static List<Card> Shuffle(List<Card> cards){
        Collections.shuffle(cards);
        return cards;
    }

    public Card pop(){
        if (this.cards.isEmpty()){
            return null;
        }
        return this.cards.remove(cards.size()-1);
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        Deck.Shuffle(d.cards);
        for (Card c: d.cards){
            System.out.println(c);
        }

    }
}