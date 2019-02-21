public class Position {
    int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static String parsePosition(int row, int col) {
        char letter = (char) ('A' + col);

        return letter + "" + (8 - row);
    }

    public static Position parsePosition(String s) {
        s = s.trim();
        if (s.length() > 2) {
            return null;
        } else {

            int col = s.charAt(0) - 'A';
            int row = 8 - s.charAt(1) - '0';

            return new Position(row, col);
        }
    }
}
