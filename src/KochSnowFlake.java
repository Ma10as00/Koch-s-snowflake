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
     * Constructs a Koch's snowflake centered within a frame, given the frame's width and height
     * @param frWidth - Frame's width
     * @param frHeight - Frame's height
     * @param depth - Number of iterations to run the algorithm through
     */
    public KochSnowFlake(int frWidth, int frHeight, int depth){
        double sides = Math.min(frWidth, frHeight) / 1.5;
        double height = sides*Math.cos(Math.PI/6);   
        //Height of little triangle under the big one is height/3, 
        //so the original triangle must be lifted height/6 for snowflake to be centered
        p1 = new Point2D.Double(frWidth/2 + sides/2, frHeight/2 + height/2 - height/6);
        p2 = new Point2D.Double(frWidth/2 - sides/2, frHeight/2 + height/2 - height/6);
        p3 = new Point2D.Double(frWidth/2,           frHeight/2 - height/2 - height/6);
        this.depth = depth;
    }

    /**
     * Constructs a Koch's snowflake, centered in the given frame.
     * @param fr - The frame to draw the snowflake on
     * @param depth - Number of iterations to run the algorithm through
     */
    public KochSnowFlake(JFrame fr, int depth){
        this(fr.getWidth(), fr.getHeight(), depth);
    }

    /**
     * Draws this Snowflake, applying the recursive algorithm for Koch's curve on all sides in a triangle. 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawKochCurve(p1, p2, depth, (Graphics2D) g);
        drawKochCurve(p2, p3, depth, (Graphics2D) g);
        drawKochCurve(p3, p1, depth, (Graphics2D) g);
    }

    /**
     * Method running Koch's algorithm on a line between two points. Kurve will be drawn downwards, if p1 is the leftmost of the endpoints, and upwards if p2 is the leftmost.
     * @param p1 - First endpoint of the kurve
     * @param p2 - Second endpoint of the kurve
     * @param depth - Number of iterations to run
     * @param g - The Graphics handling the drawing of lines.
     */
    private void drawKochCurve(Point2D p1, Point2D p2, int depth, Graphics2D g){
        
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
        
        //Point on the new triangle closest to p1 
        double x3 = x1+(difX/3);    
        double y3 = y1+(difY/3);
        Point2D p3 = new Point2D.Double(x3, y3);

        //Point on the new triangle closest to p2
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
        
        //Last point of new triangle
        double x5 = x3 + length * Math.cos(ang);
        double y5 = y3 + length * Math.sin(ang);
        Point2D p5 = new Point2D.Double(x5, y5);

        //Recursive calls
        drawKochCurve(p1, p3, depth-1, g);
        drawKochCurve(p3, p5, depth-1, g);
        drawKochCurve(p5, p4, depth-1, g);
        drawKochCurve(p4, p2, depth-1, g);
    }
}
