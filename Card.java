package uno;
public class Card{
    private String colour;
    private String value;

    public Card(String colour, String value){
        this.colour = colour;
        this.value = value;
    }

    public String toString(){
        return this.colour + ":" + this.value;
    }
}