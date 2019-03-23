/**
 * Acts as controller for this test program.
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(String s) {
        super(s);
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        OptionPanel options = new OptionPanel();
        add(options, new BorderLayout().CENTER);
    }

}
