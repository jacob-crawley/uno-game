package uno.gui;
import uno.gameplay.Card;
import uno.gameplay.Game;

import java.awt.*;
import javax.swing.*;

public class GameplayPanel extends JPanel{
    private Game game;
    public int panelHeight;
    public int panelWidth;

    public GameplayPanel(Game game) {
        setPreferredSize(new Dimension(400, 374));
        this.game = game;
        this.panelHeight = 374;
        this.panelWidth = 400;

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int cardHeight = (panelHeight - 20) / 4;
        int cardWidth = (int)(cardHeight *0.75);
        int xPos = (panelHeight / 2) - (cardWidth /2);
        int yPos = 5;

        Card topCard = game.topOfDeck;
        Rectangle r = new Rectangle(xPos, yPos, cardWidth, cardHeight);
        Color paintColour = DrawCard.getPaintColour(topCard);
        g2.setColor(paintColour);
        g2.fill(r);
        g2.setColor(Color.BLACK);
        g2.draw(r);
        DrawCard.drawCardDetails(g2,topCard, r);

    }
}