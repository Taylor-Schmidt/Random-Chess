import javax.swing.*;
import java.awt.*;

/**
 * This is the panel that the game will take place on.
 */

public class GamePanel extends JPanel {
    private BoardPanel boardPanel;
    private Board board;
    private int width;
    private int height;


    public GamePanel(int w, int h) {
        super();

        setBackground(Color.CYAN);

        //Creates BoardPanel
        board = new Board(16,true);
        width = w;
        height = h;

        //Testing to see if icons for a piece will be displayed correctly.
        Space space = new Space(new Pawn("black"));
        board.setSpace(space, 0, 0);

        boardPanel = new BoardPanel(w, h, board);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        add(boardPanel, gc);

    }
}
