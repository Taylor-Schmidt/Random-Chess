
public class turn {

	private String color;
	
	public turn()
	{
		color="White";
	}
	
	public void changeTurn()
	{
		if(color=="White")
			color="Black";
		else
			color="White";
	}
	
	public String getcolor()
	{
		return color;
	}
}
