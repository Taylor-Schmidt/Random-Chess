import javax.swing.*;

/**
 * Driver for UI.
 */
public class Main {
    public static void main(String[] args) {
        AudioManager.getInstance().playTestSound();
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
