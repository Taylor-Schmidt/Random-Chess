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
                    System.out.print("|###");
                } else {
                    System.out.print("| " + space.getpiece().getColor().charAt(0) +
                            space.getpiece().getClass().toString().replaceAll("class ", "").charAt(0));
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
                System.out.print("+---");
            else System.out.print("+");
        }
        System.out.println();
    }

}
