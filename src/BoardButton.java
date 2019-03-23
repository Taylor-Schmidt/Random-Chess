/**
 * These arethe buttons for the spaces on the board.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardButton extends JButton {

    private int xPos;      // x coordinate of the button.
    private int yPos;      // y coordinate of the button.

    public BoardButton(int x, int y, RandomColorTile c) {
        super();
        xPos=x;
        yPos=y;

        if(0==((xPos+yPos)%2)) {
            setBackground(c.getLightColor());
        }
        else {
            setBackground(c.getDarkColor());
        }
    }

    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }

    public void setNewIcon(Piece p) {
        if(!(p==null)){
            if(p.getType()== Piece.ChessPieceType.PAWN) {
                setIcon(new ImageIcon("assets/pawn_blue.png"));
            }
            updateUI();
        }
    }
    public void addListener(){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Use this to program functionality of buttons.
                System.out.println(xPos + " " + yPos);
            }
        });
    }
}
