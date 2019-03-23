import javax.swing.*;
import java.awt.*;

/**
 * This is the panel that the game will take place on.
 */

public class GamePanel extends JPanel {
    private BoardPanel boardPanel;
    private Board board;

    public GamePanel(int w, int h) {
        super();
        board=new Board();
        boardPanel= new BoardPanel(w,h);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        add(boardPanel, gc);

    }
}
