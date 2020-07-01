package uno.gui;
import java.awt.*;
import javax.swing.*;

/**
 * @author jacob crawley
 * creates blank JPanel of specified size
 */
public class BlankPanel extends JPanel{
    public BlankPanel(int width,int height){
        setPreferredSize(new Dimension(width, height));
    }
}