
package uno.gameplay;
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