import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SnowFrame extends JFrame {
    
    private static final int MIN_DEPTH = 0;
    private static final int MAX_DEPTH = 10;
    private static final int DEFAULT_DEPTH = 5;

    JSlider slider; 
    JPanel panel;
    
    public SnowFrame(){
        super("Snowflake drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        
        panel = new KochSnowFlake(this,DEFAULT_DEPTH);

        slider = new JSlider(MIN_DEPTH, MAX_DEPTH, DEFAULT_DEPTH);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int depth = slider.getValue();
                removeAll();
                panel = new KochSnowFlake(getWidth(), getHeight(), depth);
                add(panel);
                revalidate();
            }
            
        });

        
        add(panel);
        add(slider, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
