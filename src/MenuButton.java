import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.Border;

public class MenuButton extends JButton {
    private String imageName;
    private ImageIcon icon;

    public MenuButton(String s) {
        super();
        imageName=s;
        icon=new ImageIcon(imageName+".png");
        setIcon(icon);
        setSize(300,100);
        Border b = new LineBorder(Color.WHITE,0);
        setBorder(b);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon=new ImageIcon(imageName +"_push.png");
                setIcon(icon);
                updateUI();
            }
        });
    }
}
