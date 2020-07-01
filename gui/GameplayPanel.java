package uno.gui;
import uno.gameplay.Card;
import uno.gameplay.Game;
import uno.gameplay.Player;
import uno.gameplay.PlayerType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 * @author jacob crawley
 * JPanel in the centre of the GameFrame containing
 * the deck and action buttons
 */

public class GameplayPanel extends JPanel{
    private Game game;
    public int panelHeight;
    public int panelWidth;
    public JButton pickupButton;
    public JButton playButton;
    public JButton restartButton;
    private PlayerPanel playerPanel;
    private JFrame parentFrame;
    private List<JPanel> oppPanels;

    public GameplayPanel(Game game, PlayerPanel playerPanel, JFrame parentFrame,List<JPanel> oppPanels) {
        setPreferredSize(new Dimension(400, 374));
        this.parentFrame = parentFrame;
        this.game = game;
        this.panelHeight = 374;
        this.panelWidth = 400;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.playerPanel = playerPanel;
        this.oppPanels = oppPanels;

        this.playButton = new JButton("Play Card");
        this.pickupButton = new JButton("Pick up");

        this.add(playButton);
        this.add(pickupButton);

        addListeners();
    }

    // add action listeners to the action buttons
    public void addListeners(){
        GameplayPanel thisPanel = this;
        this.playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Player currentPlayer = game.getCurrentPlayer();
                if (currentPlayer.getPlayerType().equals(PlayerType.USER)) {
                    game.playCard(currentPlayer,thisPanel);

                } else {
                    game.opponentTurn(currentPlayer,thisPanel);
                    refreshPanels();

                }

            }
        });
        this.pickupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Player currentPlayer = game.getCurrentPlayer();
                if (currentPlayer.getPlayerType().equals(PlayerType.USER)) {
                    if (game.deck.isEmpty()){
                        game.reshuffleDeck();
                    }
                    currentPlayer.hand.add(game.deck.pop());
                    game.nextTurn();
                    refreshPanels();
                }
            }
        });
    }

    /**
     * repaints all the panels in the frame
      */
    public void refreshPanels(){
        this.repaint();
        this.playerPanel.resetCardSelection();
        this.playerPanel.repaint();
        for (JPanel p: oppPanels){
            p.repaint();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int cardHeight = (panelHeight - 20) / 4;
        int cardWidth = (int)(cardHeight *0.75);
        drawDeck(g2,cardHeight,cardWidth);
        drawTopCard(g2,cardHeight,cardWidth);
        if (game.isGameWon()){
            addWinnerText(g2, cardHeight, cardWidth);
            addRestartButton();
        } else {
            addTurnText(g2, cardHeight, cardWidth);
            addColourText(g2, cardHeight, cardWidth);
            List<Player> players = game.getPlayers();
            int onUno = 0;
            for (Player p: players){
                if (p.hand.size() == 1){
                    addUnoText(g2,(onUno+cardHeight),cardWidth,p.getName());
                    onUno += 25;
                }
            }
        }
    }

    /**
     * When game is won, remove buttons and add new one to start a new game
     */
    private void addRestartButton(){
        this.remove(playButton);
        this.remove(pickupButton);

        restartButton = new JButton("Play Again");
        this.add(restartButton);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new StartFrame();
                parentFrame.dispose();
            }
        } );

    }

    // Add text to display name of the winner
    private void addWinnerText(Graphics2D g2, int cardHeight,int cardWidth){
        Player currentPlayer = game.getCurrentPlayer();
        g2.setColor(Color.BLACK);
        g2.drawString(currentPlayer.getName() + " wins!",
                (panelHeight / 2) - (cardWidth /2) - 20,cardHeight+50);
    }

    // Show user who has current turn
    private void addTurnText(Graphics2D g2, int cardHeight,int cardWidth){
        Player currentPlayer = game.getCurrentPlayer();
        String turnText = currentPlayer.getName()+"'s turn";
        g2.setColor(Color.BLACK);
        g2.drawString(turnText,(panelHeight / 2) - (cardWidth /2) - 20,cardHeight+50);
    }

    // Displays current colour
    private void addColourText(Graphics2D g2, int cardHeight,int cardWidth){
        g2.setColor(Color.BLACK);
        g2.drawString("Current Colour: "+game.getCurrentColour().toString(),
                (panelHeight / 2) - (cardWidth /2) - 20,cardHeight+75);
    }

    // Displays message when a player only has one card left
    private void addUnoText(Graphics2D g2, int cardHeight,int cardWidth,String name) {
        g2.setColor(Color.BLACK);
        g2.drawString(name + " is on UNO!",
                (panelHeight / 2) - (cardWidth / 2) - 20, cardHeight + 100);
    }



    private void drawTopCard(Graphics2D g2, int cardHeight, int cardWidth){
        int xPos = (panelHeight / 2) - (cardWidth /2);
        int yPos = 5;
        Card topCard = game.getTopOfDeck();
        Rectangle r = new Rectangle(xPos, yPos, cardWidth, cardHeight);
        Color paintColour = DrawCard.getPaintColour(topCard);
        g2.setColor(paintColour);
        g2.fill(r);
        g2.setColor(Color.BLACK);
        g2.draw(r);
        DrawCard.drawCardDetails(g2,topCard, r);
    }

    private void drawDeck(Graphics2D g2, int cardHeight, int cardWidth){
        int xPos = (panelHeight / 2) - (cardWidth /2) - 20;
        int yPos = 20;

        for (int i=0; i<3; i++){
            Rectangle r = new Rectangle(xPos, yPos, cardWidth, cardHeight);
            g2.setColor(Color.BLACK);
            g2.draw(r);
            g2.setColor(Color.WHITE);
            g2.fill(r);
            xPos += 5;
            yPos -= 5;
        }
    }
}