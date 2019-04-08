import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This will be the panel containing the chess board and is where the game is played.
 */

public class BoardPanel extends JPanel {

    private int width;      //Represents the max number of columns.
    private int height;     //Represents the max number of rows.
    private BoardButton[][] boardButtons;

    private Position selectedPosition;
    private HashSet<Position> highlightedSpaces = new HashSet<>();
    private GameState gameState;
    private ArrayList<GameState> gameStates = new ArrayList<>();
    private boolean canPlay=true;
    private GamePanel gamePanel;
    private Board board;

    public BoardPanel(int w, int h, GamePanel gamePanel) {
        super(new GridLayout(w, h));
        width = w;
        height = h;
        setPreferredSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(650, 650));
        boardButtons = new BoardButton[height][width];
        ColorGenerator color = new ColorGenerator();

        this.board = gamePanel.getBoard();
        this.gamePanel=gamePanel;
        gameState = new GameState("white", board, null);
        gameStates.add(gameState);

        new TextActuator().printBoard(board); //TextActuator as board debug print
        System.out.println("It is " + gameState.getTurnColor() + "'s turn.");
        gamePanel.feedBackPanel.addlabel("It is " + gameState.getTurnColor() + "'s turn.");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Piece piece;
                Space s;
                if (board.getSpace(i, j) != null) {
                    piece = board.getSpace(i, j).getPiece();
                    s = board.getSpace(i, j);
                } else {
                    piece = null;
                    s = null;
                }
                BoardButton button = new BoardButton(this, i, j, color, s, piece);

                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    System.out.println("Clicked piece at " + button.getXPos() + " " + button.getYPos());

                    Position currentPosition = new Position(finalI, finalJ);

                    //Checks if the space you clicked on is null
                    if (board.getSpace(currentPosition) != null && canPlay) {
                        //Checks if you have already selected a space, if you haven't checks if there is a piece on that space.
                        if (selectedPosition != null || board.getSpace(currentPosition).getPiece() != null) {

                            if (selectedPosition != null) {
                                if (highlightedSpaces.contains(currentPosition)) {

                                    Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                                    currentPiece.move(board, selectedPosition, currentPosition);
                                    BoardButton oldButton = boardButtons[selectedPosition.row][selectedPosition.col];

                                    //If a piece lands on an effect tile, the effect is done.
                                    if(board.getSpace(currentPosition).getEffect()!=null){
                                        board.getSpace(currentPosition).doEffect(board.getSpace(currentPosition));
                                        gamePanel.feedBackPanel.addlabel(gameState.getTurnColor() + " landed on a " + board.getSpace(currentPosition).getEffect().getType());
                                        currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                                    }

                                    oldButton.setNewIcon(null);
                                    oldButton.updateUI();
                                    button.setNewIcon(currentPiece);

                                    unhighlightSpaces();
                                    gameState.changeTurn();
                                    if (board.kingInCheck(gameState.getTurnColor())) {
                                        if (!gameState.hasAvailableMove(board)) {
                                            System.out.println("Checkmate; " + gameState.getTurnColor() + " loses.");
                                            gamePanel.feedBackPanel.addlabel(gameState.getTurnColor() + " is in checkmate.");
                                            canPlay=false;

                                            //TODO::Create a button that does the following function to restart the game.
                                            resetGame();

                                        }
                                        System.out.println(gameState.getTurnColor() + " is in check.");
                                    }
                                    System.out.println("It is " + gameState.getTurnColor() + "'s turn.");
                                    gamePanel.feedBackPanel.addlabel("It is " + gameState.getTurnColor() + "'s turn.");
                                } else {
                                    unhighlightSpaces();

                                    selectedPosition = currentPosition;
                                    Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                                    if (currentPiece != null && currentPiece.getColor().equals(gameState.getTurnColor())) {
                                        highlightSpaces(currentPiece.getAvailableMoves(board, finalI, finalJ));
                                    } else {
                                        unhighlightSpaces();
                                    }
                                }

                            } else {
                                selectedPosition = currentPosition;

                                Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                                if (currentPiece != null && currentPiece.getColor().equals(gameState.getTurnColor())) {
                                    highlightSpaces(currentPiece.getAvailableMoves(board, finalI, finalJ));
                                } else {
                                    unhighlightSpaces();
                                }

                            }
                        } else {
                            unhighlightSpaces();
                        }
                    } else if(board.getSpace(currentPosition) == null){
                        System.out.println("That is not a usable space.");
                    }
                    else{
                        System.out.println("Game over");
                    }


                });

                add(button);
                boardButtons[i][j] = button;
            }
        }
    }


    public void highlightSpaces(HashSet<Position> spacesToHighlight) {
        highlightedSpaces = spacesToHighlight;
        for (Position p : highlightedSpaces) {
            boardButtons[p.row][p.col].setHighlight(true);
        }
    }

    public void unhighlightSpaces() {
        for (Position p : highlightedSpaces) {
            boardButtons[p.row][p.col].setHighlight(false);
        }
        highlightedSpaces.clear();
        selectedPosition = null;
    }

    public BoardButton getButton(int x, int y) {
        return boardButtons[x][y];
    }

    public void resetGame(){
        for(int i = 0; i<16; i++){
            for(int j = 0; j<16; j++){
                if(board.getSpace(i,j)!=null){
                    board.getSpace(i,j).setPiece(null);
                    boardButtons[i][j].setNewIcon(null);
                }
            }
        }

        canPlay=true;
        Space space;
        space = new Space(new Rook("black"));
        board.setSpace(space, 4, 4);
        space = new Space(new Knight("black"));
        board.setSpace(space, 4, 5);
        space = new Space(new Bishop("black"));
        board.setSpace(space, 4, 6);
        space = new Space(new Queen("black"));
        board.setSpace(space, 4, 7);
        space = new Space(new King("black"));
        board.setSpace(space, 4, 8);
        space = new Space(new Bishop("black"));
        board.setSpace(space, 4, 9);
        space = new Space(new Knight("black"));
        board.setSpace(space, 4, 10);
        space = new Space(new Rook("black"));
        board.setSpace(space, 4, 11);
        for (int i = 4; i < 12; i++) {
            space = new Space(new Pawn("black"));
            board.setSpace(space, 5, i);
        }

        space = new Space(new Rook("white"));
        board.setSpace(space, 11, 4);
        space = new Space(new Knight("white"));
        board.setSpace(space, 11, 5);
        space = new Space(new Bishop("white"));
        board.setSpace(space, 11, 6);
        space = new Space(new King("white"));
        board.setSpace(space, 11, 7);
        space = new Space(new Queen("white"));
        board.setSpace(space, 11, 8);
        space = new Space(new Bishop("white"));
        board.setSpace(space, 11, 9);
        space = new Space(new Knight("white"));
        board.setSpace(space, 11, 10);
        space = new Space(new Rook("white"));
        board.setSpace(space, 11, 11);
        for (int i = 4; i < 12; i++) {
            space = new Space(new Pawn("white"));
            board.setSpace(space, 10, i);
        }

        for(int i = 0; i<16; i++){
            for(int j = 0; j<16; j++) {
                if(board.getSpace(i,j)!=null) {
                    boardButtons[i][j].setNewIcon(board.getSpace(i, j).getPiece());
                    boardButtons[i][j].updateUI();
                }
            }
        }

        if(gameState.getTurnColor().equals("black"))
            gameState.changeTurn();

    }
}
