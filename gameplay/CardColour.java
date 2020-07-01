package uno.gameplay;

/**
 * @author jacobcrawley
 * Enum for colour of a card object
 */
public enum CardColour{
    RED("RED"),
    GREEN("GREEN"),
    BLUE("BLUE"),
    YELLOW("YELLOW"),
    BLACK("BLACK");
    private String value;

    CardColour(String value) {
        this.value = value;
    }
    public String getColour() {
        return value;
    }
    public static CardColour[] colours = new CardColour[]{
            RED, GREEN, BLUE, YELLOW, BLACK
    };

    // Create a CardColour value from a string
    public static CardColour fromString(String value){
        for (CardColour c: CardColour.values()){
            if (c.getColour().equals(value.toUpperCase())){
                return c;
            }
        }
        return null;
    }


}