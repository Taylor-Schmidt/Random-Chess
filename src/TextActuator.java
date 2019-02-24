import java.util.LinkedList;

public class TextActuator {

    private LinkedList<String> consoleQueue = new LinkedList<>();
    private int numberOfConsoleLines;

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
     * @param spaces 2D array of type Space
     */
    void printBoard(Space[][] spaces) {
        int m = spaces.length;
        int n = spaces[0].length;

        printLetterRow(m);
        for (int i = 0; i < m; i++) {
            Space[] row = spaces[i];
            printHorizontalDivider(m);

            int rowNum = m - i;

            System.out.print(rowNum + "\t");
            for (int j = 0; j < n; j++) {
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

        printHorizontalDivider(m);
        printLetterRow(m);

        printConsole();
    }

    /**
     * Prints a row equal in width to to the size of the board.
     * @param m
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
     * Prints a row of labels (for above and below board
     * @param m width of board
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
        for (int i = 0; i < numberOfConsoleLines; i++){
            System.out.println(consoleQueue.get(i));
        }
    }

}
