package uno.gameplay;
public class Card{
    private CardColour colour;
    private String value;

    public Card(String colour, String value){
        this.colour = CardColour.valueOf(colour.toUpperCase());
        this.value = value;
    }

    public CardColour getColour() {
        return colour;
    }

    public String getColourString() { return colour.getColour();}
    public String getValue() {
        return value;
    }

    public String toString(){
        return this.colour + ":" + this.value;
    }
}