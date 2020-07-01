package uno.gameplay;

/**
 * @author jacob crawley
 * States whether player is user controlled
 * or computer controlled opponent
 */
public enum PlayerType{
    USER("user"), OPPONENT("opp");
    private String type;
    PlayerType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}