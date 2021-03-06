package uno.gui;
import uno.gameplay.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

/**
 * @author jacobcrawley
 * Draws opponet for panel in north position
 */

public class TopPlayer extends JPanel{
    private int panelHeight;
    private int panelWidth;
    private String name;
    private Game game;
    private Player player;

    public TopPlayer(Game game,Player player) {
        setPreferredSize(new Dimension(800, 188));
        this.game = game;
        this.player = player;
        this.name = player.getName();
        this.panelHeight = 188;
        this.panelWidth = 800;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int headDiameter = 30;
        int xPos = (this.panelWidth/2)-(headDiameter/2);
        Shape headShape = new Ellipse2D.Double(xPos,10,
                headDiameter,headDiameter);
        g2.setColor(Color.BLACK);
        g2.fill(headShape);

        Shape bodyCurve = new Ellipse2D.Double(xPos,40,
                headDiameter,headDiameter);
        Shape body = new Rectangle(xPos,55,headDiameter,40);
        g2.fill(bodyCurve);
        g2.fill(body);
        g2.drawString(this.name,xPos-10,125);
        GamePanel.writeNumberOfCards(g2,xPos-10,150,player.hand.size());

    }
}