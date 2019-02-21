
public class Space {

    //private int x;
    //private int y;


    private Piece p;

    public Space() {p=null;}

    public Space(Piece P) {
        p = P;
    }

    public Piece getpiece() {
        return p;
    }

    public void setpiece(Piece P) {
        p=P;
    }

}
