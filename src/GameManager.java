/**
 * Main Driver for game loop.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Initialized the state of the game, and runs the game loop.
 */
class GameManager {
    static String black = "black";
    static String white = "white";

    private HashMap<String, String> preferences;

    private final String USE_ASCII = "use_ascii";

    /**
     * Runs the game loop
     */
    void run() {
        preferences = readPreferences();
        boolean useAsciiCharacters = false;
        if (preferences.containsKey(USE_ASCII)){
            useAsciiCharacters = Boolean.valueOf(preferences.get(USE_ASCII));
        } else {
            useAsciiCharacters = !asciiCompatCheck();
            preferences.put(USE_ASCII, Boolean.toString(useAsciiCharacters));
            writePreferences(preferences);
        }

        Board board = new Board();
        initBoardStandardChess(board);
        GameState currentState = new GameState("white", board, null);
        TextActuator actuator = new TextActuator(10, useAsciiCharacters);
        Scanner kb = new Scanner(System.in);

        boolean gameIsRunning = true;
        String s;
        Position pBefore, pAfter;
        //actuator.addLine("White goes first.");
        //start of game loop
        while (gameIsRunning) {

            //actuator.addLine("Which piece would you like to move?");

            //Checks if the current player is in check and alerts them if they are at the start of their turn.
            if(currentState.KingInCheck(currentState.getTurnColor())) {
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

//                actuator.addLine(pBefore + " " + pAfter); //Prints input in array coordinates

                    //Add a check to make sure entered move works.
                    Status status = board.getSpace(pBefore).getPiece().move(board, pBefore.row, pBefore.col, pAfter.row, pAfter.col);
                    //Check for check mate, if in check mate set gameIsRunning to false.
                    actuator.addLine(status.message);
                    if (board.getSpace(pBefore).getPiece() == null) {
                        currentState.ChangeTurn();
                    }
                }
//            }
            }
        }
    }

    private boolean asciiCompatCheck(){
        System.out.println("COMPATIBILITY CHECK!");
        System.out.println("Does the following character look like a question mark? (y/n)");
        System.out.println("♙");
        Scanner kb = new Scanner(System.in);
        String s = kb.nextLine();
        return s.toLowerCase().contains("s");
    }


    private void initBoardStandardChess(Board board) {
        Space[][] spaces = board.getBoard();
        for (int i = 0; i < board.getRows(); i++) {
            spaces[1][i] = new Space(new Pawn(black, 0));
            spaces[spaces.length - 2][i] = new Space(new Pawn(white, 0));
        }

        int[] emptyArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0}; //TODO: add values OR have classes have predefined values
        initStandardRow(spaces[0], black, emptyArray);
        initStandardRow(spaces[spaces.length - 1], white, emptyArray);
    }

    private void initStandardRow(Space[] row, String color, int[] valueArray) {
        row[0] = new Space(new Rook(color, valueArray[0]));
        row[1] = new Space(new Knight(color, valueArray[1]));
        row[2] = new Space(new Bishop(color, valueArray[2]));
        row[3] = new Space(new Queen(color, valueArray[3]));
        row[4] = new Space(new King(color, valueArray[4]));
        row[5] = new Space(new Bishop(color, valueArray[5]));
        row[6] = new Space(new Knight(color, valueArray[6]));
        row[7] = new Space(new Rook(color, valueArray[7]));
    }

    private static <T> void reverseArray(T[] array) {
        for (int i = 0; i < (array.length / 2) - 1; i++) {
            T temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private HashMap<String, String> readPreferences(){
        Scanner fileScanner;
        HashMap<String, String> preferences = new HashMap<>();

        try {
            fileScanner = new Scanner(new File("prefs.dat"));
        } catch (FileNotFoundException e) {
            return preferences;
        }


        while (fileScanner.hasNextLine()){
            String[] parts = fileScanner.nextLine().split(":");
            if (parts.length == 2){
                preferences.put(parts[0],parts[1]);
            }
        }

        return preferences;
    }

    private void writePreferences(HashMap<String, String> preferences){
        File file = new File("prefs.dat");
        try {
            file.createNewFile();
        } catch (IOException e) {
            return;
        }
        FileWriter fileWriter;
        try {
             fileWriter = new FileWriter(file);
        } catch (IOException e) {
            return;
        }

        for (String key: preferences.keySet()){
            try {
                fileWriter.write(key + ":" + preferences.get(key));
            } catch (IOException e) {
                return;
            }
        }
    }
}
