package uno.gui;
import uno.gameplay.Game;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel{
    protected JPanel playerPanel;
    protected JPanel gameplayPanel;
    protected JPanel leftOppPanel;
    protected JPanel rightOppPanel;
    protected JPanel topOppPanel;
    private Game game;

    public GamePanel(Container c, int opponents,Game game) {
        this.playerPanel  = new PlayerPanel(game);
        this.gameplayPanel = new GameplayPanel(game);
        this.game = game;
        switch (opponents){
            case 3:
                this.leftOppPanel = new SidePlayer("Player 1");
                c.add(this.leftOppPanel,BorderLayout.WEST);
                this.rightOppPanel = new SidePlayer("Player 3");
                c.add(this.rightOppPanel,BorderLayout.EAST);
                this.topOppPanel = new TopPlayer("Player 2");
                c.add(this.topOppPanel,BorderLayout.NORTH);
                break;
            case 2:
                this.leftOppPanel = new SidePlayer("Player 1");
                c.add(this.leftOppPanel,BorderLayout.WEST);
                this.rightOppPanel = new SidePlayer("Player 2");
                c.add(this.rightOppPanel,BorderLayout.EAST);
                this.topOppPanel = new BlankPanel(800,188);
                c.add(this.topOppPanel,BorderLayout.NORTH);
                break;
            case 1:
                this.leftOppPanel = new BlankPanel(200,374);
                c.add(this.leftOppPanel,BorderLayout.WEST);
                this.rightOppPanel = new BlankPanel(200,374);
                c.add(this.rightOppPanel,BorderLayout.EAST);
                this.topOppPanel = new TopPlayer("Player 1");
                c.add(this.topOppPanel,BorderLayout.NORTH);
                break;
        }
        c.add(this.playerPanel,BorderLayout.SOUTH);
        c.add(this.gameplayPanel,BorderLayout.CENTER);
    }
}