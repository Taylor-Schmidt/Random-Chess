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
        String s = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

        s += printSpaceArray(board.getBoard(), board.getRows(), board.getCols(), false);

        s += printConsole();

        System.out.println(s);
    }


    String printSpaceArray(Space[][] spaces, int rows, int cols, boolean asciiEnabled){
        StringBuilder sb = new StringBuilder();

        sb.append(letterRow(rows));
        for (int i = 0; i < rows; i++) {
            Space[] row = spaces[i];
            sb.append(getHorizontalDivider(rows));

            int rowNum = rows - i;

            sb.append(rowNum).append("\t");
//            System.out.print(rowNum + "\t");
            for (int j = 0; j < cols; j++) {
                Space space = row[j];
                if (space == null) {
                    sb.append("|##\t");
//                    System.out.print("|##\t");
                } else {
                    sb.append("|");
//                    System.out.print("| ");
                    Piece piece = space.getPiece();
                    if (piece == null) {
                        sb.append("\t");
//                        System.out.print("\t");
                    } else {
                        String s = asciiEnabled?
                                asciiChessCharacters(space.getPiece()) : letterChessCharacters(space.getPiece());
                        sb.append(s).append("\t");
//                        System.out.print( s + "\t");
                    }
                }
            }
            sb.append("|\t").append(rowNum).append("\n");
//            System.out.print("|");
//            System.out.print("\t" + rowNum);
//            System.out.println();
        }

        sb.append(getHorizontalDivider(rows));
        sb.append(letterRow(rows));
//        printHorizontalDivider(rows);
//        printLetterRow(rows);
//        System.out.print(sb.toString());
        return sb.toString();
    }

    private String letterChessCharacters(Piece piece){
        char name = ' ';
        switch (piece.getType()) {
            case KING:
                name = 'K';
                break;
            case QUEEN:
                name = 'Q';
                break;
            case ROOK:
                name = 'R';
                break;
            case BISHOP:
                name = 'B';
                break;
            case KNIGHT:
                name = 'K';
                break;
            case PAWN:
                name = 'P';
                break;
        }
        char color = ' ';
        if (piece.getColor().equals("black")){
            color = 'b';
        } else {
            color = 'w';
        }

        return  name + "" + color;
    }

    private String asciiChessCharacters(Piece piece){
        char c = ' ';
        switch (piece.getType()) {
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
        if (piece.getColor().equals("black"))
            c += 6;

        return  " " + c;
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

    private String getHorizontalDivider(int m){
        StringBuilder stringBuilder = new StringBuilder("\t");
        for (int r = 0; r <= m; r++) {
            if (r != m)
                stringBuilder.append("+--\t");
            else stringBuilder.append("+");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
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

    private String letterRow(int m){
        StringBuilder stringBuilder = new StringBuilder("\t");
        for (int i = 0; i < m; i++){
            stringBuilder.append(' ');
            stringBuilder.append((char) ('A' + i));
            stringBuilder.append("\t");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
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
    private String printConsole(){
        StringBuilder sb = new StringBuilder();
        int start = consoleQueue.size() > numberOfConsoleLines? consoleQueue.size() - numberOfConsoleLines : 0;
        int blankLines = consoleQueue.size() > numberOfConsoleLines? 0 : numberOfConsoleLines - consoleQueue.size();
        for (int i = 0; i < blankLines; i++){
            sb.append('\n');
        }
        for (int i = start; i < consoleQueue.size(); i++){
            if (i == oldsize + 1){
                sb.append('\n');
            }
            sb.append(consoleQueue.get(i));
            sb.append('\n');
//            System.out.println(consoleQueue.get(i));
        }
        oldsize = consoleQueue.size();

        return sb.toString();
    }
}
