/**
 * Acts as controller for this test program.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame(String s) {
        super(s);
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        OptionPanel options = new OptionPanel();
        add(options, new BorderLayout().CENTER);
        GamePanel gamePanel=new GamePanel(8,8);

        options.getStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options.setVisible(false);
                add(gamePanel, new BorderLayout().CENTER);
                validate();
            }
        });

    }

}
