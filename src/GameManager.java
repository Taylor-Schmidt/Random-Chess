/**
 * Main Driver for game loop.
 */

import java.util.Scanner;

/**
 * Initialized the state of the game, and runs the game loop.
 */
class GameManager {
    static String black = "black";
    static String white = "white";


    /**
     * Runs the game loop
     */
    void run() {
        Board board = new Board();
        initBoardStandardChess(board);
        GameState currentState = new GameState("white", board, null);
        TextActuator actuator = new TextActuator(10);
        Scanner kb = new Scanner(System.in);

        boolean gameIsRunning = true;
        String s;
        Position pBefore, pAfter;
        //actuator.addLine("White goes first.");
        //start of game loop
        while (gameIsRunning) {

            //actuator.addLine("Which piece would you like to move?");
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
                } else if (!(currentState.getTurnColor().equals(board.getSpace(pBefore).getpiece().getColor()))) {
                    actuator.addLine("The selected piece is the wrong color. Please select a " +
                            currentState.getTurnColor() + " piece.");
                } else {

//                actuator.addLine(pBefore + " " + pAfter); //Prints input in array coordinates

                    //Add a check to make sure entered move works.
                    Status status = board.getSpace(pBefore).getpiece().move(board, pBefore.row, pBefore.col, pAfter.row, pAfter.col);
                    //Check for check mate, if in check mate set gameIsRunning to false.
                    actuator.addLine(status.message);
                    if (board.getSpace(pBefore).getpiece() == null) {
                        currentState.ChangeTurn();
                    }
                }
//            }
            }
        }
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
}
