import java.util.Random;

public class ChessBoard {
    public static int sizeOfRow = 16;
    public static int sizeOfColumn = 16;
    public static Boolean Board[][] = new Boolean[sizeOfRow][sizeOfColumn];
    public static Random randomNum = new Random();
    public static int row = 0;
    public static int column = 0;

    public static Boolean[][] CreateBoard() {
        /*     0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
         * 0 :|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 1 :|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 2 :|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 3 :|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 4 :|-||-||-||-||*||*||*||*||*||*||*||*||-||-||-||-|
         * 5 :|-||-||-||-||*||*||*||*||*||*||*||*||-||-||-||-|
         * 6 :|-||-||-||-||*||*||*||*||*||*||*||*||-||-||-||-|
         * 7 :|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 8 :|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 9 :|-||-||-||-||*||*||*||*||*||*||*||*||-||-||-||-|
         * 10:|-||-||-||-||*||*||*||*||*||*||*||*||-||-||-||-|
         * 11:|-||-||-||-||*||*||*||*||*||*||*||*||-||-||-||-|
         * 12:|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 13:|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 14:|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * 15:|-||-||-||-||-||-||-||-||-||-||-||-||-||-||-||-|
         * */
        //default squares
        for(row=4;row<7;row++) {
            for(column=4;column<=11;column++) {
                Board[row][column]=true;
            }
        }
        for(row=9;row<12;row++) {
            for(column=4;column<=11;column++) {
                Board[row][column]=true;
            }
        }
        //Randomize middle part of board
        int i = 0;
        if(8>randomNum.nextInt(10)) {
            createTile(4, 7, 1, 2);
            createTile(11, 7, 1, 2);
            i++;
        }
        if(8>randomNum.nextInt(10)) {
            createTile(5, 7, 1, 2);
            createTile(10, 7, 1, 2);
            i++;
        }
        if(8>randomNum.nextInt(10)) {
            createTile(6, 7, 1, 2);
            createTile(9, 7, 1, 2);
            i++;
        }
        if(8>randomNum.nextInt(10)) {
            createTile(7, 7, 1, 2);
            createTile(8, 7, 1, 2);
            i++;
        }
        if(i==0){
            createTile(6, 7, 1, 2);
            createTile(9, 7, 1, 2);
            createTile(7, 7, 1, 2);
            createTile(8, 7, 1, 2);
        }
        //randomizing corner parts
        int rand=randomNum.nextInt(3);
        if(0==rand) {
            createTile(1, 1, 4, 6);
            createTile(11, 1, 4, 6);
            createTile(1, 9, 4, 6);
            createTile(11, 9, 4, 6);
        }
        else if (1==rand) {
            createTile(2, 2, 3, 5);
            createTile(11, 2, 3, 5);
            createTile(2, 9, 3, 5);
            createTile(11, 9, 3, 5);
        }
        else {
            createTile(5, 3, 2, 4);
            createTile(9, 3, 2, 4);
            createTile(5, 9, 2, 4);
            createTile(9, 9, 2, 4);
        }
        //randomizing side parts
        int rand2=randomNum.nextInt(4);
        if(rand2==0 ) {
            createTile(1, 6, 3, 4);
            createTile(12, 6, 3, 4);
        }
        if(rand2==1 || rand2==2) {
            createTile(2, 6, 2, 4);
            createTile(12, 6, 2, 4);
        }
        if(rand2==2|| rand2==1 || rand==2) {
            createTile(3, 6, 1, 4);
            createTile(12, 6, 1, 4);
        }
        if(0==randomNum.nextInt(2)){
            createTile(6, 12, 4, 2);
            createTile(6, 2, 4, 2);
        }
        if(0==randomNum.nextInt(2)){
            createTile(6, 12, 4, 1);
            createTile(6, 3, 4, 1);
        }
        if(0==randomNum.nextInt(2)){
            createTile(4, 3, 7, 1);
            createTile(4, 12, 7, 1);
        }
        return Board;
    }
    //Create a square of title starting from top left to bottom right
    public static void createTile(int startColumn,int startRow,int width, int height) {
        for(int m_startRow = startRow; m_startRow<height+startRow;m_startRow++)
            for(int m_startColumn = startColumn;m_startColumn<width+startColumn;m_startColumn++)
                Board[m_startRow][m_startColumn]=true;
    }
}

