import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main Driver for game loop.
 * Initialized the state of the game, and runs the game loop.
 */
class GameManager {
    private static String black = "black";
    private static String white = "white";

    private HashMap<String, String> preferences;

    private final String USE_ASCII = "use_ascii";

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
        ArrayList<GameState> gameStates = new ArrayList<>();
        gameStates.add(new GameState(currentState));
        TextActuator actuator = new TextActuator(10, useAsciiCharacters);

        //If you add a file named test to the root folder, the game launches in test mode.
//        File testFile = new File("test");
//        if (testFile.exists()) {
//            System.out.println("TEST Board setup");
//            board.getSpace(new Position(Position.parsePosition("D1"))).setPiece(new King(white));
//            board.getSpace(new Position(Position.parsePosition("B3"))).setPiece(new Bishop(black));
//        } else {
//            Initializes board with standard piece layout.
            initBoardStandardChess(board);
//        }
        Scanner kb = new Scanner(System.in);

        boolean gameIsRunning = true;
        String s;
        Position pBefore, pAfter;
        //actuator.addLine("White goes first.");
        //start of game loop
        while (gameIsRunning) {

            //Stores a copy of the currentGameState for later retrieval.
//            System.out.println(currentState.equals(gameStates.get(gameStates.size() - 1)));

            //Checks if the current player is in check and alerts them if they are at the start of their turn.
            if (currentState.kingInCheck(currentState.getTurnColor())) {
                actuator.addLine("The " + currentState.getTurnColor() + " King is in check.");
            }

            actuator.addLine("It is " + currentState.getTurnColor() + "'s turn. Enter the move you want to make(Ex. B1,A3): ");
            actuator.printBoard(board);

            s = kb.nextLine();
            actuator.addLine(s);
            String[] split = s.split(",");

            if (split.length != 2) {
                actuator.addLine("The input must be in the following form: A1,A2");
            } else {
                pBefore = Position.parsePosition(split[0]);
                pAfter = Position.parsePosition(split[1]);

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
                    Status status = board.getSpace(pBefore).getPiece().move(board, pBefore.row, pBefore.col, pAfter.row, pAfter.col);

                    if (status.status.equals(Status.STATUS_BAD)){
                        actuator.addLine(status.message);
                    } else if (currentState.kingInCheck(currentState.getTurnColor())) {//Check for check; if player is in check, revert.
                        actuator.addLine("You cannot move there! You cannot put your king in check.");

                        //updates references to refer to the last legal state.
                        currentState = new GameState(gameStates.get(gameStates.size() - 1));
                        gameStates.remove(gameStates.size() - 1);
                        board = currentState.getBoard();
                    } else {
                        actuator.addLine(status.message);

                        //If the selected piece has moved, update board, and change turns.
                        if (board.getSpace(pBefore).getPiece() == null) {
                            currentState.changeTurn();
                            currentState.setLastMove(pAfter);

                            //If a piece was captured, adds that piece to the list of captured pieces in the state.
                            Piece previousPiece = gameStates.get(gameStates.size() - 1).getBoard().getSpace(pAfter).getPiece();
                            if (previousPiece != null) {
                                currentState.addTakenPiece(previousPiece);
                            }
                        }
                    }
                }
//            }
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
