package uno.gui;
import org.w3c.dom.css.Rect;
import uno.gameplay.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class PlayerPanel extends JPanel{
    public List<Card> hand;
    public Map<Card, Rectangle> cardMap;
    public int panelHeight;
    public int panelWidth;
    private Game game;
    private Rectangle selectedRect;

    public PlayerPanel(Game game) {
        setPreferredSize(new Dimension(800, 188));
        this.game = game;
        this.panelHeight = 188;
        this.panelWidth = 800;
        this.hand = game.getPlayers().get(0).hand;
        this.cardMap = new HashMap<>();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Card card : cardMap.keySet()) {
                    Rectangle rect = cardMap.get(card);
                    if (rect.contains(e.getPoint())) {
                        if (game.getTurn() == 0){
                            game.setUserSelection(card);
                        }
                        System.out.println(game.getUserSelection());
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.getRectangles();
        for (Card card: cardMap.keySet()){
            Rectangle r = this.cardMap.get(card);
            if (r != null){
                Color paintColour = DrawCard.getPaintColour(card);
                g2.setColor(paintColour);
                g2.fill(r);
                g2.setColor(Color.BLACK);
                g2.draw(r);
                DrawCard.drawCardDetails(g2,card, r);
            }
        }
    }

    protected void getRectangles(){
        cardMap.clear();
        int cardHeight = (panelHeight - 20) / 2;
        int cardWidth = (int)(cardHeight *0.75);
        int xGap = (int)(cardWidth * 1.2); // space between cards
        int xPos = 5;
        int yPos = 5;

        for (Card c: this.hand){
            if (xPos > (this.panelWidth - cardWidth)){
                xPos = 5;
                yPos = yPos + (cardHeight +10);
            }
            Rectangle r = new Rectangle(xPos, yPos, cardWidth, cardHeight);
            this.cardMap.put(c,r);
            xPos += xGap;
        }
    }

}