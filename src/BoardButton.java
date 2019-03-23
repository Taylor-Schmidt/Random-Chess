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
        //setSize(50, 50);

        if(0==((xPos+yPos)%2)) {
            setBackground(Color.BLACK);
        }
        else {
            setBackground(Color.WHITE);
        }
    }

    public int getX() {
        return xPos;
    }
    public int getY() {
        return yPos;
    }
}
