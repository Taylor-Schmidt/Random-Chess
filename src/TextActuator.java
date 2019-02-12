public class TextActuator {

    //Test main; probably remove before prototype release
    public static void main(String[] args) {
        Space[][] spaces = new Space[8][8];



        TextActuator actuator = new TextActuator();

        actuator.printBoard(spaces);
    }

    private void printBoard(Space[][] spaces){
        int m = spaces.length;
        int n = spaces[0].length;

        for (int i = 0; i < spaces.length; i++){
            for (int r = 0; r <= m; r++){
                print("+   ");
            }
            println();
            for (int j = 0; j < spaces[0].length; j++){
                if (spaces[i][j] == null) {
                    print(" ###");
                } else {
                    print(" " + spaces[i][j].getpiece().getvalue());
//                    switch(spaces[i][j].getpiece().getvalue()){
//
//                    }
                }
            }
            println();
        }

        for (int r = 0; r <= m; r++){
            print("+   ");
        }
        println();
    }

    private void print(String s){
        System.out.print(s);
    }

    private void println(){
        System.out.println();
    }

    private void println(String s){
        System.out.println(s);
    }
}
