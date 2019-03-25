/**
 * Acts as controller for this test program.
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(String s) {
        super(s);
        setSize(1280, 800);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(null);//Centers the window in the middle of the main screen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setVisible(true);

        ImageIcon icon = new ImageIcon("assets/pawn_blue.png");
        setIconImage(icon.getImage());

        OptionPanel options = new MainMenuPanel();
//        new BorderLayout(); //Does nothing?
        add(options, BorderLayout.CENTER);
        GamePanel gamePanel = new GamePanel(8, 8);

        options.getButton(0).addActionListener(e -> {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
            options.setVisible(false);
            add(gamePanel, BorderLayout.CENTER);
            validate();
        });

    }

}
