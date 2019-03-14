import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Main Driver for game loop.
 * Initialized the state of the game, and runs the game loop.
 */
class GameManager {
    private static String black = "black";
    private static String white = "white";

    private HashMap<String, String> preferences;

    private final String USE_ASCII = "use_ascii";

    private ArrayList<GameState> gameStates = new ArrayList<>();

    /**
     * Runs the game loop
     */
    void run() {

        //Reads preferences file.
        //If ASCII chess character behavior is not defined, updates it to reflect
        preferences = readPreferences();
        boolean useAsciiCharacters;
        if (preferences.containsKey(USE_ASCII)) {
            useAsciiCharacters = Boolean.valueOf(preferences.get(USE_ASCII));
        } else {
            useAsciiCharacters = asciiCompatCheck();
            preferences.put(USE_ASCII, Boolean.toString(useAsciiCharacters));
            writePreferences(preferences);
        }

        //Initialize basic classes
        Board board = new Board();
        GameState currentState = new GameState(white, board, null);
        gameStates.add(new GameState(currentState));
        TextActuator actuator = new TextActuator(10, useAsciiCharacters);

        //If you add a file named test to the root folder, the game launches in test mode.
        File testFile = new File("test");
        if (testFile.exists()) {
            System.out.println("TEST Board setup");
//            currentState.changeTurn();
            board.getSpace(new Position(Position.parsePosition("H8"))).setPiece(new King(black));
//            board.getSpace(new Position(Position.parsePosition("F7"))).setPiece(new King(white));
            board.getSpace(new Position(Position.parsePosition("f6"))).setPiece(new Queen(white));
        } else {
//      Initializes board with standard piece layout.
            initBoardStandardChess(board);
        }
        Scanner kb = new Scanner(System.in);

        currentState.setFiftyMoveDrawCounter(49);

        boolean gameIsRunning = true;
        //actuator.addLine("White goes first.");
        //start of game loop
        while (gameIsRunning) {

            //Checks if the current player is in check and alerts them if they are at the start of their turn.
            boolean isInCheck = board.kingInCheck(currentState.getTurnColor());
            boolean hasAvailableMove = hasAvailableMove(board, currentState.getTurnColor());

            //Game over checks and notifies if king is in check
            actuator.addLine(currentState.getFiftyMoveDrawCounter());
            if (isThreeFoldDraw()) {
                actuator.addLine("It's a threefold repetition; the same position occurred three times, with the same player to move.");
                actuator.addLine("The game has ended in a draw.");
                gameIsRunning = false;
            } else if (currentState.fiftyMoveDraw()) {
                actuator.addLine("There has been fifty moves without a capture or a pawn moving.");
                actuator.addLine("The game has ended in a draw.");
                gameIsRunning = false;
            } else if (isInCheck) {
                if (hasAvailableMove) {
                    actuator.addLine("The " + currentState.getTurnColor() + " King is in check.");
                } else {
                    actuator.addLine("It is checkmate for " + currentState.getTurnColor() + ".");
                    String winningColor = (currentState.getTurnColor().equals("white")) ? "Black" : "White";
                    actuator.addLine(winningColor + " wins.");
                    gameIsRunning = false;
                }
            } else {
                if (!hasAvailableMove) {
                    actuator.addLine("It's a stalemate.");
                    actuator.addLine("The game has ended in a draw.");
                    gameIsRunning = false;
                }
            }

            if (gameIsRunning) {
                board = currentState.getBoard();
                actuator.addLine("It is " + currentState.getTurnColor() + "'s turn. Enter the move you want to make(Ex. B1,A3): ");
                actuator.printBoard(board);
                currentState = getUserInput(kb, currentState, actuator);
            } else {
                actuator.printBoard(board);
            }

        }

        kb.close();
    }

    private void initBoardStandardChess(Board board) {
        Space[][] spaces = board.getBoard();
        for (int i = 0; i < board.getRows(); i++) {
            spaces[1][i] = new Space(new Pawn(black));
            spaces[spaces.length - 2][i] = new Space(new Pawn(white));
        }

        initStandardRow(spaces[0], black);
        initStandardRow(spaces[spaces.length - 1], white);
    }

    private void initStandardRow(Space[] row, String color) {
        row[0] = new Space(new Rook(color));
        row[1] = new Space(new Knight(color));
        row[2] = new Space(new Bishop(color));
        row[3] = new Space(new Queen(color));
        row[4] = new Space(new King(color));
        row[5] = new Space(new Bishop(color));
        row[6] = new Space(new Knight(color));
        row[7] = new Space(new Rook(color));
    }

    private GameState getUserInput(Scanner kb, GameState currentState, TextActuator actuator) {
        Board board = currentState.getBoard();

        String s = kb.nextLine();
        actuator.addLine(s);
        String[] split = s.split(",");

        if (split.length != 2) {
            actuator.addLine("The input must be in the following form: A1,A2");
        } else {
            Position pBefore = Position.parsePosition(split[0]);
            Position pAfter = Position.parsePosition(split[1]);

            if (pBefore == null || pAfter == null) {
                actuator.addLine("The input '" + s + "' could not be understood.");
                actuator.addLine("The input must be in the following form: A1,A2");
            } else if (!(board.positionIsWithinBounds(pBefore) && board.positionIsWithinBounds(pAfter))) {
                actuator.addLine("The input must be within the bounds of the board.");
            } else if (!Piece.isASpace(board, pBefore)) {
                actuator.addLine("The space " + Position.parsePosition(pBefore) + " does not exist.");
            } else if (!Piece.hasAPiece(board, pBefore)) {
                actuator.addLine("The space " + Position.parsePosition(pBefore) + " does not contain a piece.");
            } else if (!(currentState.getTurnColor().equals(board.getSpace(pBefore).getPiece().getColor()))) {
                actuator.addLine("The selected piece is the wrong color. Please select a " +
                        currentState.getTurnColor() + " piece.");
            } else {
                gameStates.add(new GameState(currentState));

                //De-links references
                currentState = new GameState(currentState);
                board = currentState.getBoard();

                //Add a check to make sure entered move works.
                Piece currentPiece = board.getSpace(pBefore).getPiece();
                Status status = currentPiece.move(board, pBefore.row, pBefore.col, pAfter.row, pAfter.col);

                if (status.status.equals(Status.STATUS_BAD)) {
                    actuator.addLine(status.message);
                } else if (board.kingInCheck(currentState.getTurnColor())) {//Check for check; if player is in check, revert.
                    actuator.addLine("You cannot move there! You cannot put your king in check.");

                    //updates references to refer to the last legal state.
                    currentState = new GameState(gameStates.get(gameStates.size() - 1));
                    gameStates.remove(gameStates.size() - 1);
//                    board = currentState.getBoard();
                } else {
                    actuator.addLine(status.message);

                    //If the selected piece has moved, update board, and change turns.
                    if (board.getSpace(pBefore).getPiece() == null) {
                        currentState.changeTurn();
                        currentState.setLastMove(pAfter);

                        //If a piece was captured, adds that piece to the list of captured pieces in the state.
                        Piece previousPiece = gameStates.get(gameStates.size() - 1).getBoard().getSpace(pAfter).getPiece();

                        //TODO: en passante special case
                        if (previousPiece != null) {
                            currentState.addTakenPiece(previousPiece);
                            currentState.resetFiftyMoveDrawCounter();
                            actuator.addLine("resetting because a piece was captured ");
                        } else if (currentPiece.getType() == Piece.ChessPieceType.PAWN) {
                            currentState.resetFiftyMoveDrawCounter();
                            actuator.addLine("resetting because a pawn moved");
                        } else {
                            currentState.incrementFiftyMoveDrawCounter();
                            actuator.addLine("incrementing move counter");
                        }
                    }
                }
            }
        }

        return currentState;
    }


    /**
     * Returns true if the player has a possible move that doesn't put the king in check
     *
     * @param board
     * @param turnColor color of the current playerco
     * @return
     */
    private boolean hasAvailableMove(Board board, String turnColor) {
        boolean hasAvailableMove = false;

        //iterate through every space on the board
        for (int i = 0; i < board.getRows() && !hasAvailableMove; i++) {
            for (int j = 0; j < board.getCols() && !hasAvailableMove; j++) {
                Position position = new Position(i, j);
                Space space = board.getSpace(position);

                //Check for a piece at that location
                if (space != null) {
                    Piece piece = space.getPiece();
                    if (piece != null) {
                        if (piece.getColor().equals(turnColor)) {
                            HashSet<Position> moves = piece.getAvailableMoves(board, position);

                            Iterator<Position> movesIterator = moves.iterator();
                            while (movesIterator.hasNext() && !hasAvailableMove) {
                                Position move = movesIterator.next();
                                if (piece.legalMove(board, move)) {
                                    Board tempBoard = new Board(board);
                                    Status status = tempBoard.getSpace(position).getPiece().move(tempBoard, position, move);

                                    hasAvailableMove = !status.status.equals(Status.STATUS_BAD) && !tempBoard.kingInCheck(turnColor);

                                }
                            }
                        }
                    }
                }
            }
        }

        return hasAvailableMove;
    }

    /**
     * Indicates whether a Three-Fold Repetition Draw has occurred.
     * A Three-Fold Repetition is when the same position occurs three times, with the same player to move.
     *
     * @return true if the repetition has occurred.
     */
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


    private static <T> void reverseArray(T[] array) {
        for (int i = 0; i < (array.length / 2) - 1; i++) {
            T temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private HashMap<String, String> readPreferences() {
        Scanner fileScanner;
        HashMap<String, String> preferences = new HashMap<>();

        try {
            fileScanner = new Scanner(new File("prefs.dat"));
        } catch (FileNotFoundException e) {
            return preferences;
        }

        while (fileScanner.hasNextLine()) {
            String next = fileScanner.nextLine();
            String[] parts = next.split(":");
            if (parts.length == 2) {
                preferences.put(parts[0], parts[1]);
            }
        }

        return preferences;
    }

    private void writePreferences(HashMap<String, String> preferences) {
        File file = new File("prefs.dat");
        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
        } catch (IOException e) {
            return;
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            return;
        }

        for (String key : preferences.keySet()) {
            try {
                String s = key + ":" + preferences.get(key);
                fileWriter.write(s);
            } catch (IOException e) {
                return;
            }
        }

        try {
            fileWriter.close();
        } catch (IOException ignored) {

        }
    }

    private boolean asciiCompatCheck() {
        System.out.println("COMPATIBILITY CHECK!");
        System.out.println("Does the following character look like a question mark? (y/n)");
        System.out.println("♙");
        Scanner kb = new Scanner(System.in);
        String s = kb.nextLine();
        return s.toLowerCase().contains("n");
    }
}
