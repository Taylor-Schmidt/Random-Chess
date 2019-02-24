import java.util.Scanner;

public class GameManager {
    private static String black = "black";
    private static String white = "white";

    public static void main(String[] args) {
        Board board = new Board();

        Space[][] spaces = board.getBoard();
        initBoardStandardChess(spaces);

        GameState currentState = new GameState(0, board);
        TextActuator actuator = new TextActuator(0);
        Scanner kb = new Scanner(System.in);

        boolean gameIsRunning = true;
        Turn turn = new Turn();
        String s;
        Position PBefore, PAfter;
        //actuator.addLine("White goes first.");
        //start of game loop
        while (gameIsRunning){

            //actuator.addLine("Which piece would you like to move?");
            actuator.printBoard(spaces);
            System.out.println("Enter the move you want to make(Ex. B1,A3): ");
            s=kb.nextLine();
            String[] split = s.split(",");
            PBefore = Position.parsePosition(split[0]);
            PAfter = Position.parsePosition(split[1]);
            System.out.println(PBefore.row + " " + PBefore.col + " " + PAfter.row + " " + PAfter.col);
            //Add a check to make sure entered move works.
            spaces[PBefore.row][PBefore.col].getpiece().move(spaces, PBefore.row, PBefore.col, PAfter.row, PAfter.col);
            //Check for check mate, if in check mate set gameIsRunning to false.



        }

    }


    private static void initBoardStandardChess(Space[][] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            spaces[1][i] = new Space(new Pawn(black, 0));
            spaces[spaces.length - 2][i] = new Space(new Pawn(white, 0));
        }

        int[] emptyArray = new int[]{0,0,0,0,0,0,0,0}; //TODO: add values OR have classes have predefined values
        initStandardRow(spaces[0], black, emptyArray);
        initStandardRow(spaces[spaces.length - 1], white, emptyArray);
    }

    private static void initStandardRow(Space[] row, String color, int[] valueArray) {
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
