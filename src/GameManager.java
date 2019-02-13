import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) {
        Space[][] board = new Space[8][8]; //TODO: change to Board class

        TextActuator actuator = new TextActuator();
        Scanner kb = new Scanner(System.in);

        actuator.printBoard(board);

        System.out.println("White goes first.");
        System.out.print("Which piece would you like to move?");
        String input = kb.nextLine();


    }
}
