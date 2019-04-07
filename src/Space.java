import java.util.Objects;
import java.util.Random;

/**
 * Represents on tile on a Board instance.
 */
public class Space {

    private Piece p; //Piece that is on this Space. If it is empty, then there is no piece on this Space.
    private Effect effect; //Effect on the space.

    /**
     * Default constructor.
     * Sets p to null.
     * In the logic of the engine, returns a Space with no Piece on it.
     */
    public Space() {

        p = null;
        Random randomNum = new Random();
        int randInt = randomNum.nextInt(20); //20 makes it so that each effect has a 5% chance of generating.

        //5% chance of generating a bomb space.
        if(randInt==1){
            effect = new BombEffect();
            System.out.println("Created bomb tile.");
        }
        else{
            effect=null;
        }
    }

    /**
     * Creates a Space with a predefined Piece on it. Useful for setting up
     *
     * @param P
     */
    public Space(Piece P) {
        p = P;
        effect=null;
    }

    /**
     * Getter for the piece on this Space
     *
     * @return the Piece placed on this space. (Returns null if there is no piece.)
     */
    public Piece getPiece() {
        return p;
    }

    /**
     * Places the given Piece on this Space
     *
     * @param P Piece to place on this Space.
     */
    public void setPiece(Piece P) {
        p = P;
    }

    public Effect getEffect(){
        return effect;
    }
    public void setEffect(Effect e){
        effect = e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Space)) return false;
        Space space = (Space) o;
        return Objects.equals(p, space.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }

    public void doEffect(Space s){
        s.getEffect().doEffect(s);
    }
}
