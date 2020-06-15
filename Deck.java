package uno;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author jacobcrawley
 * Stores collection of card objects representing the deck
 */
public class Deck {
    public ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
        // creates two wildcards of each type
        for (int i=0; i<4; i++) {
            this.cards.add(new Card("b", "wild"));
            this.cards.add(new Card("b", "wild +4"));
        }
        String[] colours = {"r","g","b","y"};
        for (int j=0; j< colours.length; j++){
            String colour = colours[j];
            // add 1 zero card of each colour to deck
            this.cards.add(new Card(colour,"0"));
            // add 2 cards for numbers 1-9 for each colour
            for (int x=1; x <= 9; x++){
                this.cards.add(new Card(colour,String.valueOf(x)));
                this.cards.add(new Card(colour,String.valueOf(x)));
            }
            // add 2 skip and reverse cards
            for (int y=0; y<2; y++) {
                this.cards.add(new Card(colour,"skip"));
                this.cards.add(new Card(colour,"reverse"));
                this.cards.add(new Card(colour,"+2"));
            }
        }
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        System.out.println(d.cards.size());

    }
}