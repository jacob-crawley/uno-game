package uno.gameplay;
import java.util.*;

public class Player{
    private String name;
    public List<Card> hand;
    private PlayerType PlayerType;

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return PlayerType;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Player(String name,  PlayerType type) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.PlayerType = type;
    }
}