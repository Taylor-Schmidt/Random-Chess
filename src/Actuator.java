/**
 * All classes that create a GUI should extend this class.
 * Provides methods which are called when the correlating action occurs.
 */
public abstract class Actuator {
    protected GameState gameState;//board gives us information on holes in board and locations of pieces
    protected Position selectedPosition;//Position of a piece selected by the player
    protected Position[] highlightedPositions; //All positions that are highlighted when a piece is selected
    protected MenuPage menuPage; //page of currently displayed, or last displayed menu if no menu is currently displayed

    /**
     * Initializes the Actuator with a gameState. Should open a window of some sort.
     * @param gameState initial gameState
     */
    public Actuator(GameState gameState){
        this.gameState = gameState;
    }

    /**
     * Displays a menu page (such as the main menu or a pause menu)
     */
    public abstract void showMenu(MenuPage menuPage);

    /**
     * Hides the current menuPage
     */
    public abstract void hideMenu();

    /**
     * Every time the gameState changes, it should be passed to the Actuator so that the information conveyed through
     * the UI matches what is stored internally.
     * Should be called before drawPieces.
     * @param gameState new gameState that is matched
     */
    public void update(GameState gameState){
        this.gameState = gameState;
    }

    /**
     * Displays the Spaces in the appropriate pieces.
     * Assuming that no space becomes null while the game is being played,
     */
    public abstract void drawBoard();

    /**
     * Displays the Pieces in the appropriate locations on the board.
     */
    public abstract void drawPieces();

    /**
     * Highlights a piece when you select it.
     * Stores in local data member selectedPosition
     * @param p position to highlight
     */
    public abstract void highlightSelected(Position p);

    /**
     * Removes highlight from selectedPosition.
     */
    public abstract void unHighlightSelected();

    /**
     * Draws a highlighted effect around pieces that a selected piece can move to.
     * Stores these positions in local data member highlightedPositions.
     * @param highlightedPositions stores selectedPiece.getAvailableMoves()
     */
    public abstract void highlightMoveOptions(Position[] highlightedPositions);

    /**
     * Removes highlight effect from highlightedPositions
     */
    public abstract void unHighlightMoveOptions();

    /**
     * Shows animation of piece at begin moving to end.
     * @param begin Position where the piece is at, and the position where the animation begins.
     * @param end Position where the animation ends.
     */
    public abstract void movePiece(Position begin, Position end);

}
