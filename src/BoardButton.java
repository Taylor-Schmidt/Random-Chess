/**
 * These arethe buttons for the spaces on the board.
 */

import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton {

    private int xPos;      // x coordinate of the button.
    private int yPos;      // y coordinate of the button.

    public BoardButton(int x, int y) {
        super();
        xPos=x;
        yPos=y;

        if(0==((xPos+yPos)%2)) {
            setBackground(Color.BLACK);
        }
        else {
            setBackground(Color.WHITE);
        }
    }

    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }
}
