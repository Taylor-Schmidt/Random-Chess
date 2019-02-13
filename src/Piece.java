
public interface Piece {

    public abstract void move(Space a[][], int currentX, int currentY, int newX, int newY);

    public int getvalue();

    public String getColor();

}
