package uno;
import uno.gui.StartFrame;
import javax.swing.*;

public class RunUno{
    public RunUno(){
        JFrame f = new StartFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        RunUno r = new RunUno();
    }
}