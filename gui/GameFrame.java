package uno.gui;
import uno.gameplay.Game;

import java.awt.*;
import javax.swing.*;

/**
 * @author jacob crawley
 * JFrame where the game is played
 */

public class GameFrame extends JFrame {
    private Game game;
    public GamePanel gamePanel;

    public GameFrame(String name, int players) {
        setTitle("UNO");
        setPreferredSize(new Dimension(800, 750));
        Container contentPane = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.game = new Game(name,players);
        this.gamePanel = new GamePanel(contentPane,players,game,this);
    }
}