import java.util.Scanner;
public class Test {

	public static void main(String[] args) {
		
		//initialize regular 8x8 board
		space[][] board = new space[8][8];
		Scanner key =  new Scanner(System.in);
		
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				board[i][j]=null;
			}
		}
		turn CurrentTurn=new turn();
		displayBoard(CurrentTurn.getcolor(),board);
		
		
		
		//The rest of the program is temporary and for testing
		bishop wbishop = new bishop("white", 1);
		board[3][3]= new space(wbishop);
		displayBoard(CurrentTurn.getcolor(),board);
		System.out.println("Enter the X coordinate for where you want to move the bishop(Represented by the 1). Legal moves not yet programmed.");
		int currentX=3, currentY=3, newX, newY;
		newX=key.nextInt();
		System.out.println("Now enter the Y coordinate.");
		newY=key.nextInt();
		board[currentX][currentY].getpiece().move(board, currentX, currentY, newX, newY);
		CurrentTurn.changeTurn();
		displayBoard(CurrentTurn.getcolor(),board);

	}




public static void displayBoard(String turn, space[][] a)
{
	System.out.println(turn + "'s Turn");
	for(int i=7;i>-1;i--)
	{
		for(int j=0;j<8;j++)
		{
			if(a[i][j]==null)
				System.out.print(0 + " ");
			else
				System.out.print(a[i][j].getpiece().getvalue() + " ");
		}
		System.out.println("");
		
	}
	System.out.println("################");
}


}