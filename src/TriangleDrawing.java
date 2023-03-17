import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TriangleDrawing extends JPanel {

    
    int[] xs;
    int[] ys;

    /**
     * Draws a triangle with sides = 100, in the center of the frame.
     * @param fr - The frame in which the triangle is to be centered
     */
    public TriangleDrawing(JFrame fr){
        xs = new int[] {fr.getWidth()/2, fr.getWidth()/2 + 50, fr.getWidth()/2 -50};
        ys = new int[] {fr.getHeight()/2 -43, fr.getHeight()/2 + 43, fr.getHeight()/2 + 43};
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawPolygon(xs, ys, 3);
    }
}
