package uno.gui;
import uno.gameplay.CardColour;
import uno.gameplay.Game;
import java.awt.*;
import javax.swing.*;

/**
 * @author jacobcrawley
 * Frame for wildcard colour
 */

public class WildcardFrame extends JFrame{
    private Game game;
    private CardColour[] colourChoices;

    public WildcardFrame(Game game, CardColour[] colourChoices,GameplayPanel gPanel){
        setSize(250, 250);
        Container contentPane = this.getContentPane();
        WildcardPanel panel = new WildcardPanel(game,colourChoices,this,gPanel);
        contentPane.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}