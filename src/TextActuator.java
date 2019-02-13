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
            for (int r = 0; r <= m; r++) {
                print("+   ");
            }
            println();
            for (Space space : row) {
                if (space == null) {
                    print(" ###");
                } else {
                    print("  " + space.getpiece().getColor().charAt(0) +
                            space.getpiece().getClass().toString().replaceAll("class ", "").charAt(0));

                }
            }
            println();
        }

        for (int r = 0; r <= m; r++) {
            print("+   ");
        }
        println();
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void println() {
        System.out.println();
    }

    private void println(String s) {
        System.out.println(s);
    }
}
