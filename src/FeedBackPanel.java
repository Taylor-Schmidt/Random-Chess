import javax.swing.*;
import java.awt.*;

    public class FeedBackPanel extends JPanel{
        public String feedback;
        JLabel label= new JLabel();
        FeedBackPanel() {
           setPreferredSize(new Dimension(100, 700));
           setMinimumSize(new Dimension(250, 650));
           setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
           setBackground(Color.CYAN);

        }
        public void addlabel(String feedback){
            this.feedback = feedback;
            String true_color= true_color(feedback);
            label.setText(true_color);
            this.add(label);
            updateUI();
        }
        public String true_color(String false_color){
            String true_color;
            if(false_color.contains("black"))
                true_color = "It's blue turn";
            else
                true_color = "It's red turn";
            return  true_color;
        }
    }

