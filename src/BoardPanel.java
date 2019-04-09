import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This will be the panel containing the chess board and is where the game is played.
 */

public class BoardPanel extends JPanel {

    private BoardButton[][] boardButtons;

    private Position selectedPosition; //Piece that corresponds to the highlighted spaces
    private HashSet<Position> highlightedSpaces = new HashSet<>();
    private GameState currentState;
    private ArrayList<GameState> gameStates = new ArrayList<>();
    private boolean canPlay = true;
    private Board board;

    @SuppressWarnings("Duplicates")
    BoardPanel(int w, int h, GamePanel gamePanel) {
        super(new GridLayout(w, h));
        //Represents the max number of columns.
        //Represents the max number of rows.
        setPreferredSize(new Dimension(700, 700));
        setMinimumSize(new Dimension(650, 650));
        setBackground(Color.CYAN);


        boardButtons = new BoardButton[h][w];
        ColorGenerator color = new ColorGenerator();

        this.board = gamePanel.getBoard();
        currentState = new GameState("white", board, null);
        gameStates.add(currentState);

        new TextActuator().printBoard(board); //TextActuator as board debug print
        System.out.println("It is " + currentState.getTurnColor() + "'s turn.");
        gamePanel.feedBackPanel.addlabel("It is " + currentState.getTurnColor() + "'s turn.");

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Space s = board.getSpace(i,j);
                Piece piece;
                if (s != null) {
                    piece = s.getPiece();
                } else {
                    piece = null;
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

                        if (selectedPosition != null) {
                            //If the move is valid, then
                            if (highlightedSpaces.contains(currentPosition)) {
                                currentState.setBoard(board);
                                gameStates.add(new GameState(currentState));

                                //De-links currentState reference
                                currentState = new GameState(currentState);
                                //board = currentState.getBoard();

                                Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                                Piece previousPiece;
                                if (board.getSpace(currentPosition).getPiece() != null) {
                                    previousPiece = board.getSpace(currentPosition).getPiece();
                                } else {
                                    previousPiece = null;
                                }

                                currentPiece.move(board, selectedPosition, currentPosition);

                                //If a piece lands on an effect tile, the effect is done.
                                if (board.getSpace(currentPosition).getEffect() != null) {
                                    board.getSpace(currentPosition).doEffect(board.getSpace(currentPosition));
                                    gamePanel.feedBackPanel.addlabel(currentState.getTurnColor() + " landed on a " + board.getSpace(currentPosition).getEffect().getType());
                                    currentPiece = board.getSpace(currentPosition).getPiece();
                                }

                                //TODO: this logic should be incorporated into highlightSpaces()
                                if (board.kingInCheck(currentState.getTurnColor())) {
                                    gamePanel.feedBackPanel.addlabel("You cannot move there! You cannot put your king in check.");

                                    //updates references to refer to the last legal state.
                                    currentState = new GameState(gameStates.get(gameStates.size() - 1));
                                    board = currentState.getBoard();

                                    //Remove state to prevent duplicates; it is added again at the beginning of the loop
                                    gameStates.remove(gameStates.size() - 1);
                                } else {

                                    //Updates board in currentState.
                                    currentState = new GameState(currentState.getTurnColor(), board, currentPosition);

                                    BoardButton oldButton = boardButtons[selectedPosition.row][selectedPosition.col];

                                    oldButton.setNewIcon(null);
                                    oldButton.updateUI();
                                    //In case pawn gets turned into a a queen.
                                    if (currentPiece != null && currentPiece.getType() == Piece.ChessPieceType.PAWN) {
                                        currentPiece = board.getSpace(currentPosition).getPiece();
                                    }
                                    boardButtons[currentPosition.row][currentPosition.col].setNewIcon(currentPiece);
                                    boardButtons[currentPosition.row][currentPosition.col].updateUI();

                                    //If a piece was captured, adds that piece to the list of captured pieces in the state.
                                    //Piece previousPiece = gameStates.get(gameStates.size() - 1).getBoard().getSpace(currentPosition).getPiece();

                                    if (previousPiece != null) {
                                        currentState.addTakenPiece(previousPiece);
                                        currentState.resetFiftyMoveDrawCounter();
                                    /*} else if (currentPiece.getType() == Piece.ChessPieceType.PAWN) {
                                        currentState.resetFiftyMoveDrawCounter(); */
                                    } else {
                                        currentState.incrementFiftyMoveDrawCounter();
                                    }

                                    unhighlightSpaces();
                                    currentState.changeTurn();

                                    //Checks if the current player is in check and alerts them if they are at the start of their turn.
                                    boolean isInCheck = board.kingInCheck(currentState.getTurnColor());
                                    boolean hasAvailableMove = currentState.hasAvailableMove(board);

                                    if (isInCheck) {
                                        if (!hasAvailableMove) {
                                            System.out.println("Checkmate; " + currentState.getTurnColor() + " loses.");
                                            gamePanel.feedBackPanel.addlabel(currentState.getTurnColor() + " is in checkmate.");
                                            canPlay = false;

                                            //TODO: might have to add turn switch
                                            gameOver(currentState.getTurnColor());

                                            gamePanel.newGame();
                                        } else {
                                            System.out.println(currentState.getTurnColor() + " is in check.");
                                            gamePanel.feedBackPanel.addlabel(currentState.getTurnColor() + " is in check.");
                                        }

                                    } else if (!hasAvailableMove) {
                                        gamePanel.feedBackPanel.addlabel("It's a stalemate.");
                                        gamePanel.feedBackPanel.addlabel("The game has ended in a draw.");

                                        gameOver(null);
                                    } else if (isThreeFoldDraw()) {
                                        gamePanel.feedBackPanel.addlabel("It's a threefold repetition; the same " +
                                                "position occurred three times, with the same player to move.");
                                        gamePanel.feedBackPanel.addlabel("The game has ended in a draw.");

                                        gameOver(null);

                                    } else if (currentState.fiftyMoveDraw()) {
                                        gamePanel.feedBackPanel.addlabel("There has been fifty moves without a capture or a pawn moving.");
                                        gamePanel.feedBackPanel.addlabel("The game has ended in a draw.");

                                        gameOver(null);
                                    } else {
                                        System.out.println("It is " + currentState.getTurnColor() + "'s turn.");
                                        gamePanel.feedBackPanel.addlabel("It is " + currentState.getTurnColor() + "'s turn.");
                                    }
                                }
                            } else {
                                unhighlightSpaces();

                                selectedPosition = currentPosition;
                                Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                                if (currentPiece != null && currentPiece.getColor().equals(currentState.getTurnColor())) {
                                    highlightSpaces(currentState.availableMoves(board, currentPiece, currentPosition));
                                } else {
                                    unhighlightSpaces();
                                }
                            }

                        } else if (board.getSpace(currentPosition).getPiece() != null) {
                            selectedPosition = currentPosition;

                            Piece currentPiece = board.getSpace(selectedPosition.row, selectedPosition.col).getPiece();
                            if (currentPiece != null && currentPiece.getColor().equals(currentState.getTurnColor())) {
                                highlightSpaces(currentState.availableMoves(board, currentPiece, currentPosition));
                            } else {
                                unhighlightSpaces();
                            }

                        } else {
                            unhighlightSpaces();
                        }
                    } else if (board.getSpace(currentPosition) == null) {
                        System.out.println("That is not a usable space.");
                    } else {
                        System.out.println("Game over");
                    }
                });

                add(button);
                boardButtons[i][j] = button;
            }
        }
    }

    private void highlightSpaces(HashSet<Position> spacesToHighlight) {
        highlightedSpaces = spacesToHighlight;
        for (Position p : highlightedSpaces) {
            boardButtons[p.row][p.col].setHighlight(true);
        }
    }

    private void unhighlightSpaces() {
        for (Position p : highlightedSpaces) {
            boardButtons[p.row][p.col].setHighlight(false);
        }
        highlightedSpaces.clear();
        selectedPosition = null;
    }

    private void gameOver(String winnerColor) {
        String os = System.getProperty("os.name");
        String[] options = {"New game", "Exit to main menu", "Exit to " + os};
        String statusMessage = (winnerColor != null) ? (winnerColor.equals("white") ? "Blue" : "Red") + " wins!" : "It's a draw!";
        JOptionPane.showOptionDialog(this, statusMessage, "Game over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        //TODO: make the options do what they say they do
    }

    public BoardButton getButton(int x, int y) {
        return boardButtons[x][y];
    }

    /**
     * Indicates whether a Three-Fold Repetition Draw has occurred.
     * A Three-Fold Repetition is when the same position occurs three times, with the same player to move.
     *
     * @return true if the repetition has occurred.
     */
    @SuppressWarnings("Duplicates")
    private boolean isThreeFoldDraw() {
        if (gameStates.size() > 8) {
            GameState currentState = gameStates.get(gameStates.size() - 1);

            int repetitions = 0;
            for (int i = 0; i < gameStates.size() - 2; i++) {
                if (gameStates.get(i).equals(currentState)) {
                    repetitions++;
                    if (repetitions == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
