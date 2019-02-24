
public class Turn {

    private String color;

    public Turn() {
        color = "White";
    }

    public void changeTurn() {
        if (color.equals("White"))
            color = "Black";
        else
            color = "White";
    }

    public String getcolor() {
        return color;
    }



}
