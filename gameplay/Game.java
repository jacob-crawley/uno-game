package uno.gameplay;
import java.util.*;

public class Game{
    private List<Player> players;
    private Deck deck;
    private int orderOfPlay;
    private List<Card> cardsPlayed;
    private int turn;
    private boolean gameWon;
    public Card topOfDeck;
    private CardColour currentColour;

    public Game(String name, int opps) {
        this.players = new ArrayList<Player>();
        this.cardsPlayed = new ArrayList<Card>();
        this.deck = new Deck();
        turn = 0;
        this.orderOfPlay = 1; // starts as 1 reverse changes it to -1

        // create player object for user
        players.add(new Player(name, PlayerType.USER));
        // create player objects for opponents
        for (int i=1; i<=opps; i++){
            String oppName = "Player "+String.valueOf(i);
            players.add(new Player(oppName,PlayerType.OPPONENT));
        }
        this.Deal(deck,players);
        topOfDeck = deck.pop();
        cardsPlayed.add(topOfDeck);
        currentColour = topOfDeck.getColour();
        gameWon = false;
    }

    public void playGame(){
        while (!gameWon){
            Player p = players.get(turn);
            if (p.getPlayerType().equals(PlayerType.USER)){
                // user turn
            } else {
                opponentTurn(p);
            }
        }
    }

    public void opponentTurn(Player p){
        List<Card> validCards =  new ArrayList<Card>();
        Card topCard = topOfDeck;
        for (Card c: p.hand){
            if (c.getColour().equals(currentColour) || c.getValue() == topCard.getValue() ||
                    c.getValue().substring(0, 5).equals("wild")) {
                validCards.add(c);
            }
        }

        if (validCards.isEmpty()){
            p.hand.add(deck.pop());
        } else {
            Random r = new Random();
            int cardChoice = r.nextInt(validCards.size());
            cardsPlayed.add(validCards.get(cardChoice));
            topOfDeck = validCards.get(cardChoice);
            currentColour = topOfDeck.getColour();
            p.hand.remove(p.hand.indexOf(validCards.get(cardChoice)));

        }

        if (p.hand.isEmpty()){
            gameWon = true;
        } else{
            List<String> specialCards = new ArrayList<String>(List.of("+2","skip","reverse","wild","wild +4"));
            if (specialCards.contains(topOfDeck.getValue())){
                switch(topOfDeck.getValue()){
                    case "+2":
                        plusTwo();
                        break;
                    case "skip":
                        skip();
                        break;
                    case "reverse":
                        //reverse();
                        break;
                    case "wild":
                        wild(p);
                        break;
                    case "wild +4":
                        wild(p);
                        plusTwo();
                        plusTwo();
                }
            } else{
                nextTurn();
            }
        }
    }

    /**
     * Increment turn by OrderOfPlay
     */
    private void nextTurn() {
        turn  = (turn +orderOfPlay) % (players.size());
    }

    // special card actions
    public void skip(){
        nextTurn();
        nextTurn();
    }

    public void plusTwo(){
        // give adjacent player 2 cards and skip turn
        nextTurn();
        Player p = players.get(turn);
        p.hand.add(deck.pop());
        p.hand.add(deck.pop());
        nextTurn();
    }

    public void reverse(){
        orderOfPlay = -(orderOfPlay);
    }

    public void wild(Player p){
        CardColour[] colours = CardColour.colours;
        CardColour[] colourChoices = new CardColour[colours.length-1];
        System.arraycopy(colours,0,colourChoices,0,colourChoices.length);

        if (p.getPlayerType().equals(PlayerType.OPPONENT)){
            Random r = new Random();
            int choice = r.nextInt(colourChoices.length);
            currentColour = colourChoices[choice];
        }
    }



    public void Deal(Deck d,List<Player> players){
        List<Card> cards = d.cards;
        // assign 7 cards to each player
        for (int i=0; i<7; i++){
            for (Player p: players){
                p.hand.add(cards.get(0));
                cards.remove(0);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getOrderOfPlay() {
        return orderOfPlay;
    }

    public List<Card> getCardsPlayed() {
        return cardsPlayed;
    }

    public static void main(String[] args) {
        Game g = new Game("gary",3);
    }
}