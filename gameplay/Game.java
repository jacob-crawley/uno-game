package uno.gameplay;
import uno.gui.GameplayPanel;
import uno.gui.WildcardFrame;
import java.util.*;

/**
 * @author jacob crawley
 * Holds all the details of an uno game
 */

public class Game{
    private List<Player> players;
    public Deck deck;
    private int orderOfPlay;
    private List<Card> cardsPlayed;
    private int turn;
    private boolean gameWon;
    private Card topOfDeck;
    private CardColour currentColour;
    private Card userSelection;
    private final List<String> SPECIAL_CARDS = new ArrayList<String>(List.of("+2","skip","reverse","wild","wild +4"));

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
        this.Deal();
        boolean validTopCard = false;
        while (!validTopCard){
            Card topCard =  deck.pop();
            if (!topCard.getValue().equals("wild")||!topCard.getValue().equals("wild +4")){
                topOfDeck = topCard;
                validTopCard = true;
            }
        }

        cardsPlayed.add(topOfDeck);
        currentColour = topOfDeck.getColour();
        gameWon = false;
        userSelection = null;
    }

    // Return list of all cards in hand that can be played
    public List<Card> getValidCards(List<Card> hand){
        List<Card> validCards =  new ArrayList<Card>();
        Card topCard = topOfDeck;
        for (Card c: hand){
            if (c.getColour().equals(currentColour) || c.getValue().equals(topCard.getValue()) ||
                    c.getValue().equals("wild")||c.getValue().equals("wild +4")) {
                validCards.add(c);
            }
        }
        return validCards;
    }

    // Returns boolean showing if selected card can be played
    public boolean isValidCard(Card c){
        if (c.getColour().equals(currentColour) || c.getValue().equals(topOfDeck.getValue()) ||
                c.getValue().equals("wild")||c.getValue().equals("wild +4")) {
            return true;
        }
        return false;
    }

    // plays user selected card
    public void playCard(Player currentPlayer, GameplayPanel panel){
        List<Card> validCards = getValidCards(currentPlayer.hand);
        if (validCards.contains(this.userSelection)){
            cardsPlayed.add(userSelection);
            topOfDeck = userSelection;
            currentColour = topOfDeck.getColour();
            currentPlayer.hand.remove(currentPlayer.hand.indexOf(userSelection));
            userSelection = null;
            if (currentPlayer.hand.isEmpty()){
                gameWon = true;
            } else {
                if (SPECIAL_CARDS.contains(topOfDeck.getValue())){
                    specialCardActions(topOfDeck,currentPlayer,panel);
                } else{
                    nextTurn();
                    panel.refreshPanels();
                }
            }
        }
    }

    // Takes opponent turn by selecting a random valid card or picking a card up
    public void opponentTurn(Player p,GameplayPanel panel){
        Player currentPlayer = this.getCurrentPlayer();
        List<Card> validCards = getValidCards(p.hand);

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
            if (SPECIAL_CARDS.contains(topOfDeck.getValue())){
                specialCardActions(topOfDeck,currentPlayer,panel);
            } else{
                nextTurn();
            }
        }
    }

    // Actions for non-numerical cards
    private void specialCardActions(Card card,Player currentPlayer,GameplayPanel panel){
        switch(card.getValue()){
            case "+2":
                plusCards(2);
                panel.refreshPanels();
                break;
            case "skip":
                skip();
                panel.refreshPanels();
                break;
            case "reverse":
                reverse();
                nextTurn();
                panel.refreshPanels();
                break;
            case "wild":
                wild(currentPlayer,panel);
                nextTurn();
                break;
            case "wild +4":
                wild(currentPlayer,panel);
                plusCards(4);

        }
    }

    // Goes to next player by incrementing turn by orderOfPlay
    public void nextTurn() {
        turn  = (turn +orderOfPlay) % (players.size());
        if (turn == -1){
            turn = players.size() -1;
        }
    }

    // special card actions

    // skip adjacent player - increment turn twice
    public void skip(){
        nextTurn();
        nextTurn();
    }

    // assign specifed number of cards to the next player
    public void plusCards(int numCards){
        // give adjacent player 2 cards and skip turn
        nextTurn();
        Player p = players.get(turn);
        for (int i=0; i<numCards; i++){
            p.hand.add(deck.pop());
        }
        nextTurn();
    }

    // reverses the order of play
    public void reverse(){
        orderOfPlay = -(orderOfPlay);
    }

    // selects new colour when a wildcard is played
    public void wild(Player p,GameplayPanel panel){
        CardColour[] colours = CardColour.colours;
        // create new array of colours without black
        CardColour[] colourChoices = new CardColour[colours.length-1];
        System.arraycopy(colours,0,colourChoices,0,colourChoices.length);

        if (p.getPlayerType().equals(PlayerType.OPPONENT)){
            Random r = new Random();
            int choice = r.nextInt(colourChoices.length);
            currentColour = colourChoices[choice];
        } else {
           WildcardFrame colourMenu = new WildcardFrame(this,colourChoices,panel);
        }
    }

    /**
     * Assigns 7 cards to the hand of each player
     */
    public void Deal(){
        List<Card> cards = deck.cards;
        // assign 7 cards to each player
        for (int i=0; i<7; i++){
            for (Player p: players){
                p.hand.add(cards.get(0));
                cards.remove(0);
            }
        }
    }

    /**
     * Used when deck runs out - shuffle cardsPlayed
     * and move to deck so game can continue
     */
    public void reshuffleDeck(){
        Deck.shuffle(cardsPlayed);
        for (int i=0; i < cardsPlayed.size(); i++){
            deck.addCard(cardsPlayed.get(i));
            cardsPlayed.remove(i);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public Card getTopOfDeck() {
        return topOfDeck;
    }

    public CardColour getCurrentColour() {
        return currentColour;
    }

    public void setCurrentColour(String colour){
        currentColour = CardColour.fromString(colour);
    }

    public void setUserSelection(Card userSelection) {
        this.userSelection = userSelection;
    }

    public Player getCurrentPlayer(){
        return this.players.get(turn);
    }
}