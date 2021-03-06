package uno.gui;
import uno.gameplay.Card;
import java.awt.*;

/**
 * @author jacob crawley
 * methods for drawing details of uno cardsd
 */

public class DrawCard{
    /**
     * draws card value in rectangle
     */
    public static void drawCardDetails(Graphics2D g2, Card card, Rectangle r){
        if (card.getColourString().equals("YELLOW")){
            g2.setColor(Color.BLACK);
        } else {
            g2.setColor(Color.WHITE);
        }

        String value = card.getValue();
        g2.setFont(new Font("HANGING_BASELINE", Font.BOLD, 15));
        g2.drawString(value,r.x+2,r.y+25);
    }

    /**
     * Get Color object to fill card with, based on its CardColour
     */
    public static Color getPaintColour(Card c){
        String cardColour = c.getColourString();
        Color paintColour = null;
        switch (cardColour) {
            case "BLACK":
                paintColour = Color.BLACK;
                break;
            case "RED":
                paintColour = Color.RED;
                break;
            case "GREEN":
                paintColour = Color.GREEN;
                break;
            case "BLUE":
                paintColour = Color.BLUE;
                break;
            case "YELLOW":
                paintColour = Color.YELLOW;
                break;
        }
        return paintColour;
    }
}