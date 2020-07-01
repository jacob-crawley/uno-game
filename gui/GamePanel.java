package uno.gui;
import uno.gameplay.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * @author jacob crawley
 * Contains each section of the game
 */

public class GamePanel extends JPanel{
    protected PlayerPanel playerPanel;
    protected JPanel gameplayPanel;
    protected JPanel leftOppPanel;
    protected JPanel rightOppPanel;
    protected JPanel topOppPanel;
    private Game game;
    private JFrame parentFrame;

    public GamePanel(Container c, int opponents,Game game, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.playerPanel  = new PlayerPanel(game);
        this.game = game;
        List<JPanel> oppPanels = new ArrayList<JPanel>();

        // set left, right and top panels based on selected no. of opponents
        switch (opponents){
            case 3:
                this.leftOppPanel = new SidePlayer(this.game,this.game.getPlayers().get(1));
                c.add(this.leftOppPanel,BorderLayout.WEST);
                this.rightOppPanel = new SidePlayer(this.game,this.game.getPlayers().get(3));
                c.add(this.rightOppPanel,BorderLayout.EAST);
                this.topOppPanel = new TopPlayer(this.game,this.game.getPlayers().get(2));
                c.add(this.topOppPanel,BorderLayout.NORTH);

                oppPanels.add(leftOppPanel);
                oppPanels.add(rightOppPanel);
                oppPanels.add(topOppPanel);

                break;
            case 2:
                this.leftOppPanel = new SidePlayer(this.game,this.game.getPlayers().get(1));
                c.add(this.leftOppPanel,BorderLayout.WEST);
                this.rightOppPanel = new SidePlayer(this.game,this.game.getPlayers().get(2));
                c.add(this.rightOppPanel,BorderLayout.EAST);
                this.topOppPanel = new BlankPanel(800,188);
                c.add(this.topOppPanel,BorderLayout.NORTH);
                oppPanels.add(leftOppPanel);
                oppPanels.add(rightOppPanel);
                break;
            case 1:
                this.leftOppPanel = new BlankPanel(200,374);
                c.add(this.leftOppPanel,BorderLayout.WEST);
                this.rightOppPanel = new BlankPanel(200,374);
                c.add(this.rightOppPanel,BorderLayout.EAST);
                this.topOppPanel = new TopPlayer(this.game,this.game.getPlayers().get(1));
                c.add(this.topOppPanel,BorderLayout.NORTH);
                oppPanels.add(topOppPanel);
                break;
        }
        this.gameplayPanel = new GameplayPanel(game, this.playerPanel, parentFrame, oppPanels);
        c.add(this.playerPanel,BorderLayout.SOUTH);
        c.add(this.gameplayPanel,BorderLayout.CENTER);
    }

    // Writes the number of cards in each opponents hand at specified coordinates
    public static void writeNumberOfCards(Graphics2D g2, int x, int y,int value){
        g2.setColor(Color.BLACK);
        g2.drawString("Cards: "+value,x,y);
    }

}