
 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 import java.util.*;
 import java.util.List;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

 public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;

    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private static Dimension TILE_PANEL_DIMENSION = new Dimension (10,10);


    private RandomColorTile colorTile = new RandomColorTile();
    public  Table()
    {

        this.gameFrame = new JFrame("Test Board");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);

        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

    }
    private class BoardPanel extends JPanel
    {
        //TilePanel[][] boardTiles;
        //int tempID = 0;
        final List <TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            for(int i = 0; i < 64; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
           // boardTiles = new TilePanel[8][8];
           // for(int i = 0; i < 8; i++)
           // {
           //     for(int j = 0; j < 8; j++)
            //    {
            //        final TilePanel tilepanel = new TilePanel(this,tempID,i);
            //        boardTiles[i][j] = tilepanel;
            //        add(tilepanel);
            //        tempID++;
             //   }

           // }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }


    }
    private class TilePanel extends JPanel
    {
        private final int tileID;
        TilePanel(final BoardPanel boardPanel ,final int tileID)
        {
            super(new GridBagLayout());
            this.tileID = tileID;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor(tileID);
            validate();

        }

        private void assignTilePieceIcon()
        {
            this.removeAll();
        }

        private void assignTileColor(int color)
        {
            if((color <= 7 || (color <= 23 & color >= 16 ) || (color <= 39 & color >= 32)|| (color <= 55 & color >= 48)))
            {
                setBackground(this.tileID % 2 == 0 ? colorTile.getLightColor(): colorTile.getDarkColor());
            }
            else if ((color <=15 & color >= 8)|| (color <= 31 & color >= 24) || (color <= 47 & color >= 40)|| (color <= 63 & color >= 56))
            {
                setBackground(this.tileID % 2 != 0 ? colorTile.getLightColor(): colorTile.getDarkColor());
            }
            else
            {
                setBackground(Color.BLACK);
            }


        }

    }
}
