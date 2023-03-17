import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnowFrame extends JFrame {
    
    public SnowFrame(){
        super("Snowflake drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JPanel panel = new TriangleDrawing(this);
        add(panel);

        Action nextStep = new NextStepAction("Next step");

        JButton nextStepButton = new JButton(nextStep);

        panel.add(nextStepButton);
        setVisible(true);
    }
}
