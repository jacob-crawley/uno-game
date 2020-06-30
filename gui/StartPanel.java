package uno.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class StartPanel extends JPanel{
    protected JPanel titlePanel;
    protected JPanel centrePanel;
    protected JPanel namePanel;
    protected JPanel oppPanel;
    protected  JButton startButton;
    protected JTextField entryBox;
    protected ButtonGroup bGroup;
    protected JFrame parentFrame;

    public StartPanel(Container c,JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.titlePanel = new JPanel();
        this.addTitle(titlePanel);

        this.centrePanel = new JPanel();
        this.centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.Y_AXIS));
        this.addCentreContent(centrePanel);

        c.add(titlePanel,BorderLayout.PAGE_START);
        c.add(centrePanel,BorderLayout.CENTER);
        this.addStartButton(c);
        this.addListeners();
    }

    public void addTitle(JPanel p){
        JLabel title = new JLabel("UNO");
        title.setForeground(Color.RED);
        title.setFont(new Font("HANGING_BASELINE", Font.BOLD, 100));
        p.add(title);
    }

    public void addCentreContent(JPanel p){
        namePanel =  new JPanel();
        JLabel playerName =  new JLabel("Enter name:");
        playerName.setFont(new Font("HANGING_BASELINE", Font.PLAIN, 25));
        entryBox = new JTextField(15);
        namePanel.add(playerName);
        namePanel.add(entryBox);


        this.oppPanel = new JPanel();
        JLabel oppLabel = new JLabel("Select Number of Opponents:");
        oppLabel.setFont(new Font("HANGING_BASELINE", Font.PLAIN, 25));
        oppPanel.add(oppLabel);
        bGroup = new ButtonGroup();
        this.addRadioButton(oppPanel,bGroup,"1");
        this.addRadioButton(oppPanel,bGroup,"2");
        this.addRadioButton(oppPanel,bGroup,"3");

        p.add(namePanel);
        p.add(this.oppPanel);
    }

    public void addStartButton(Container c){
        this.startButton = new JButton("Start Game");
        c.add(startButton,BorderLayout.PAGE_END);
    }

    public void addRadioButton(JPanel p, ButtonGroup g, String name){
        JRadioButton b = new JRadioButton(name);
        b.setActionCommand(name);
        p.add(b);
        g.add(b);
    }

    public void addListeners(){
        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String name = entryBox.getText();
                    String noOfOpps = bGroup.getSelection().getActionCommand();
                    new GameFrame(name, Integer.parseInt(noOfOpps));
                    parentFrame.dispose();
                } catch (NullPointerException e) {
                    JLabel errorLabel = new JLabel("Please select no. of opponents");
                    errorLabel.setForeground(Color.RED);
                    centrePanel.add(errorLabel);
                }
            }
        });
    }
}