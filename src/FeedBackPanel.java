import javax.swing.*;
import java.awt.*;

    public class FeedBackPanel extends JPanel{
        public String feedback;
        FeedBackPanel() {
           setPreferredSize(new Dimension(100, 700));
           setMinimumSize(new Dimension(250, 650));

        }
        public void addlabel(String feedback){
            this.feedback = feedback;
            add(new JLabel(feedback));
        }
    }
