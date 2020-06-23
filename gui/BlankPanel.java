package uno.gui;
import java.awt.*;
import javax.swing.*;

public class BlankPanel extends JPanel{
    private int width;
    private int height;

    public BlankPanel(int width,int height){
        setPreferredSize(new Dimension(width, height));
    }
}