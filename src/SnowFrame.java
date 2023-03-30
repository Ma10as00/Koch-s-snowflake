import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnowFrame extends JFrame {
    
    public SnowFrame(){
        super("Snowflake drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        JPanel panel = new KochSnowFlake(this,4, 500);
        
        JComboBox<Integer> depthOptions = new JComboBox<>();
        for (int i = 0; i <= 10; i++){
            depthOptions.addItem(i);
        }
        
        depthOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
        });

        panel.add(depthOptions);
        add(panel);
        setVisible(true);
    }
}
