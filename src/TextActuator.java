import java.util.LinkedList;

/**
 * Used to output chess board (and a "console" output of text) to System.out
 */
public class TextActuator {

    private LinkedList<String> consoleQueue = new LinkedList<>(); //List of items to be printed in console.
    private int numberOfConsoleLines; //Number of lines of consoleQueue to print when printing console
    private int oldsize = 0;

    /**
     * Creates an instance of TextActuator
     * @param size Max lines that the attache console print can have
     */
    TextActuator(int size){
        numberOfConsoleLines = size;
    }

    /**
     * Prints Spaces to Board
     * Null Spaces are not able to be stepped on
     * Spaces with null getPiece() have no piece on them
     * @param board Instance of Board
     */
    void printBoard(Board board) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        int rows = board.getRows();
        int cols = board.getCols();
        Space[][] spaces = board.getBoard();

        printSpaceArray(spaces, rows, cols);

        printConsole();
    }

    void printSpaceArray(Space[][] spaces, int rows, int cols){
        printLetterRow(rows);
        for (int i = 0; i < rows; i++) {
            Space[] row = spaces[i];
            printHorizontalDivider(rows);

            int rowNum = rows - i;

            System.out.print(rowNum + "\t");
            for (int j = 0; j < cols; j++) {
                Space space = row[j];
                if (space == null) {
                    System.out.print("|##\t");
                } else {
                    System.out.print("| ");
                    Piece piece = space.getpiece();
                    if (piece == null) {
                        System.out.print("\t");
                    } else {
                        char c = ' ';
                        switch (space.getpiece().getType()) {
                            case KING:
                                c = '♔';
                                break;
                            case QUEEN:
                                c = '♕';
                                break;
                            case ROOK:
                                c = '♖';
                                break;
                            case BISHOP:
                                c = '♗';
                                break;
                            case KNIGHT:
                                c = '♘';
                                break;
                            case PAWN:
                                c = '♙';
                                break;
                        }
                        if (space.getpiece().getColor().equals("black"))
                            c += 6;
                        System.out.print( c + "\t");
                    }
                }
            }
            System.out.print("|");
            System.out.print("\t" + rowNum);
            System.out.println();
        }

        printHorizontalDivider(rows);
        printLetterRow(rows);
    }

    /**
     * Prints a equal in width to to the size of the board.
     * @param m width of board (number of columns)
     */
    private void printHorizontalDivider(int m) {
        System.out.print("\t");
        for (int r = 0; r <= m; r++) {
            if (r != m)
                System.out.print("+--\t");
            else System.out.print("+");
        }
        System.out.println();
    }

    /**
     * Prints a row of letter labels to help the user identify the column in standard chess notation.
     * Should be printed above and below the board.
     * @param m width of board (number of columns)
     */
    private void printLetterRow(int m){
        System.out.print("\t");
        for (int i = 0; i < m; i++){
            System.out.print(("  " + (char) ('A' + i)) + "\t");
        }
        System.out.println();
    }

    /**
     * Adds a line to the console
     * @param line string to print (should be a feedback message to user)
     */
    public void addLine(String line){
        consoleQueue.add(line);
    }


    /**
     * prints lines below the text board
     * Consists of feedback for user
     */
    private void printConsole(){
        int start = consoleQueue.size() > numberOfConsoleLines? consoleQueue.size() - numberOfConsoleLines : 0;
        int blankLines = consoleQueue.size() > numberOfConsoleLines? 0 : numberOfConsoleLines - consoleQueue.size();
        for (int i = 0; i < blankLines; i++){
            System.out.println();
        }
        for (int i = start; i < consoleQueue.size(); i++){
            if (i == oldsize + 1){
                System.out.println();
            }
            System.out.println(consoleQueue.get(i));
        }
        oldsize = consoleQueue.size();
    }
}
