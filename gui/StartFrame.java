package uno.gui;
import java.awt.*;
import javax.swing.*;

public class StartFrame extends JFrame {
    public StartFrame() {
        setTitle("UNO");
        setSize(550, 325);
        Container contentPane = this.getContentPane();
        StartPanel p = new StartPanel(contentPane,this);
    }

    public static void main(String[] args) {
        JFrame f = new StartFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}