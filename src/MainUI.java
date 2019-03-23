/**
 * Driver for UI.
 */

import javax.swing.*;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame("Random Chess");
        });
    }

}
