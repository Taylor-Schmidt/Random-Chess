public class TextActuator {

    //Test main; probably remove before prototype release
    public static void main(String[] args) {
        Space[][] spaces = new Space[8][8];
        for (int i = 0; i < 8; i++) {
            spaces[0][i] = new Space(new Bishop("white", 3));
        }

        TextActuator actuator = new TextActuator();
        actuator.printBoard(spaces);
    }

    void printBoard(Space[][] spaces) {
        int m = spaces.length;
        int n = spaces[0].length;

        for (Space[] row : spaces) {
            printHorizontalDivider(m);

            for (Space space : row) {
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
            System.out.println();
        }

        printHorizontalDivider(m);
    }

    private void printHorizontalDivider(int m) {
        for (int r = 0; r <= m; r++) {
            if (r != m)
                System.out.print("+--\t");
            else System.out.print("+");
        }
        System.out.println();
    }

}
