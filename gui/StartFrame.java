package uno.gui;
import java.awt.*;
import javax.swing.*;

/**
 * @author jacob crawley
 * JFrame for start page
 */

public class StartFrame extends JFrame {
    public StartFrame() {
        setTitle("UNO");
        setSize(550, 325);
        Container contentPane = this.getContentPane();
        StartPanel p = new StartPanel(contentPane,this);
    }
}