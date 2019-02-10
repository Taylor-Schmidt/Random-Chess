
public class bishop implements piece{

	private String color;
	private int value;
	
	public bishop(String c, int v)
	{
		color=c;
		value=v;
	}
	
	public void move(space a[][], int currentX, int currentY, int newX, int newY)
	//Still need to program to only move piece if legal move. Also will need to make it so you can only move a pieceif it is that color's turn.
	{
		a[currentX][currentY]=null;
		a[newX][newY]=new space(this);
	}
	
	public int getvalue()
	{
		return value;
	}
}
