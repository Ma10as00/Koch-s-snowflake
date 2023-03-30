import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class KochSnowFlake extends JPanel {
    
    private Point2D p1;
    private Point2D p2;
    private Point2D p3;
    private int depth;
    
    /**
     * Constructs a Koch's snowflake.
     * @param fr - The frame to draw the snowflake on
     * @param depth - Number of iterations to run the algorithm
     * @param length - The length of the sides in the original triangle (when depth=0)
     */
    public KochSnowFlake(JFrame fr, int depth, int length){
        p1 = new Point2D.Double(fr.getWidth()/2 + length/2, fr.getHeight()/2 + length/100*43);
        p2 = new Point2D.Double(fr.getWidth()/2 -length/2, fr.getHeight()/2 + length/100*43);
        p3 = new Point2D.Double(fr.getWidth()/2,fr.getHeight()/2-length/100*43);
        this.depth = depth;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawSnowFlake(p1, p2, depth, (Graphics2D) g);
        drawSnowFlake(p2, p3, depth, (Graphics2D) g);
        drawSnowFlake(p3, p1, depth, (Graphics2D) g);
    }

    /**
     * Method running Koch's algorithm on a line between two points. Kurve will be drawn downwards, if p1 is the leftmost of the endpoints, and upwards if p2 is the leftmost.
     * @param p1 - First endpoint of the kurve
     * @param p2 - Second endpoint of the kurve
     * @param depth - Number of iterations to run
     * @param g - The Graphics handling the drawing of lines.
     */
    private void drawSnowFlake(Point2D p1, Point2D p2, int depth, Graphics2D g){
        
        if(depth == 0){
            g.draw(new Line2D.Double(p1, p2));
            return;
        }
        double dist = p1.distance(p2);
        double length = dist/3; //Length of new lines

        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        double difX = x2-x1;   
        double difY = y2-y1;
        
        //Punktet på den nye trekanten som ligger nærmest p1 
        double x3 = x1+(difX/3);    
        double y3 = y1+(difY/3);
        Point2D p3 = new Point2D.Double(x3, y3);

        //Punktet på den nye trekanten som ligger nærmest p2
        double x4 = x2-(difX/3);
        double y4 = y2-(difY/3);
        Point2D p4 = new Point2D.Double(x4, y4);


        /**
         * Points should be orientated like this:
         * 
        p1 - - p3      p4 - - p2
                \    /
                 \  /
                  p5
        */

        //Angle between x-axis and the line (p1,p2)
        double ang = Math.atan2(difY, difX);
        //Angle between x-axis and the line (p3,p5)
        ang -= Math.PI/3;
        
        //Toppunktet for den nye trekanten
        double x5 = x3 + length * Math.cos(ang);
        double y5 = y3 + length * Math.sin(ang);
        Point2D p5 = new Point2D.Double(x5, y5);

        //Recursive calls
        drawSnowFlake(p1, p3, depth-1, g);
        drawSnowFlake(p3, p5, depth-1, g);
        drawSnowFlake(p5, p4, depth-1, g);
        drawSnowFlake(p4, p2, depth-1, g);
    }
}
