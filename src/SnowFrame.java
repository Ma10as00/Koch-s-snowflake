import java.awt.BorderLayout;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SnowFrame extends JFrame {

    private static final int MIN_DEPTH = 0;
    private static final int MAX_DEPTH = 10;
    private static final int DEFAULT_DEPTH = 3;

    JSlider slider; 
    JPanel panel;
    
    /**
     * Constructs a {@code JFrame} with {@code size = (width, height)}.
     * The Frame contains a drawing of Koch's snowflake, and a slider to adjust the depth of the snowflake.
     * <p>
     * Initial drawing will be of depth {@code SnowFrame.DEFAULT_DEPTH}, 
     * and the slider will have the inclusive limits {@code SnowFrame.MIN_DEPTH} and {@code SnowFrame.MAX_DEPTH}.
     * <p>
     * The slider will be either horizontal in the top of the Frame, or vertical to the left in the Frame, depending on the Frame's width and height.
     * <p>
     * The Frame will be displayed in the center of the screen.
     * @param width - The frame's width
     * @param height - The frame's height
     */
    public SnowFrame(int width, int height){
        super("Koch's Snowflake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        
        panel = new KochSnowFlake(this,DEFAULT_DEPTH);
        add(panel);

        if(width>height){
            slider = setupSlider(JSlider.VERTICAL);
            add(slider, BorderLayout.WEST);
        }else{
            slider = setupSlider(JSlider.HORIZONTAL);
            add(slider, BorderLayout.NORTH);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Constructs a slider, with ticks and labels. The slider draws a new Snowflake with the appropriate depth when the cursor is moved. 
     * @param orientation - Indicates whether the slider is vertical or horizontal
     * @return The slider
     * @throws IllegalArgumentExpetion - If orientation is not equal to {@code JSlider.HORIZONTAL} or {@code JSlider.VERTICAL}. 
     */
    private JSlider setupSlider(int orientation){
        //Throw exception if orientation is invalid
        if(orientation != JSlider.HORIZONTAL && orientation != JSlider.VERTICAL){
            throw new IllegalArgumentException("Integer does neither indicate HORIZONTAL nor VERTICAL.");
        }

        slider = new JSlider(orientation, MIN_DEPTH, MAX_DEPTH, DEFAULT_DEPTH);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setSnapToTicks(true);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(MIN_DEPTH, new JLabel(Integer.toString(MIN_DEPTH)));
        labels.put(MAX_DEPTH, new JLabel(Integer.toString(MAX_DEPTH)));
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(new ChangeDepth());

        return slider;
    }

    /**
     * ChangeListener that draws a new Snowflake with the appropriate depth when a change is made.
     */
    public class ChangeDepth implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            int depth = slider.getValue();
                remove(panel);
                panel = new KochSnowFlake(getWidth(), getHeight(), depth);
                add(panel);
                revalidate();
        }

    }
}
