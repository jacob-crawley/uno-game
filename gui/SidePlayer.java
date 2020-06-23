package uno.gui;
import uno.gameplay.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class SidePlayer extends JPanel{
    private int panelHeight;
    private int panelWidth;
    private String name;

    public SidePlayer(String name) {
        setPreferredSize(new Dimension(200, 374));
        this.name = name;
        this.panelHeight = 374;
        this.panelWidth = 200;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int headDiameter = 30;
        Shape headShape = new Ellipse2D.Double((this.panelWidth/2)-(headDiameter/2),10,
                headDiameter,headDiameter);
        g2.setColor(Color.BLACK);
        g2.fill(headShape);

        Shape bodyCurve = new Ellipse2D.Double((this.panelWidth/2)-(headDiameter/2),40,
                headDiameter,headDiameter);
        Shape body = new Rectangle((this.panelWidth/2)-(headDiameter/2),55,headDiameter,40);
        g2.fill(bodyCurve);
        g2.fill(body);
        g2.drawString(this.name,(this.panelWidth/2)-(headDiameter/2)-10,125);

    }


}