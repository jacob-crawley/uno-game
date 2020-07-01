package uno.gui;
import uno.gameplay.CardColour;
import uno.gameplay.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author jacobcrawley
 * Displays coponents for wildcard colour choice
 */

public class WildcardPanel extends JPanel{
    private Game game;
    private CardColour[] colourChoices;
    protected ButtonGroup bGroup;
    protected JButton submitButton;
    protected JFrame parentFrame;
    private GameplayPanel gPanel;

    public WildcardPanel(Game game, CardColour[] colourChoices, JFrame parentFrame,GameplayPanel gPanel) {
        this.game = game;
        this.colourChoices = colourChoices;
        this.parentFrame = parentFrame;
        this.gPanel = gPanel;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Select a colour");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("HANGING_BASELINE", Font.BOLD, 30));
        this.add(title);

        addButtons();
        this.submitButton = new JButton("Submit");
        this.add(submitButton);
        addListeners(this);
    }
    // create radiobuttons for each colour
    private void addButtons(){
        bGroup = new ButtonGroup();
        for (CardColour c: colourChoices){
            addRadioButton(this, bGroup,c.toString());
        }
    }
    public void addRadioButton(JPanel p, ButtonGroup g, String name){
        JRadioButton b = new JRadioButton(name);
        b.setActionCommand(name);
        p.add(b);
        g.add(b);
    }

    public void addListeners(JPanel panel){
        this.submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    game.setCurrentColour(bGroup.getSelection().getActionCommand());
                    gPanel.refreshPanels();
                    parentFrame.dispose();
                } catch (NullPointerException e) {
                    JLabel errorLabel = new JLabel("Please select a colour");
                    errorLabel.setForeground(Color.RED);
                    panel.add(errorLabel);
                }
            }
        });
    }
}