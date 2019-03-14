import java.util.Objects;

/**
 * Represents on tile on a Board instance.
 */
public class Space {

    private Piece p; //Piece that is on this Space. If it is empty, then there is no piece on this Space.

    /**
     * Default constructor.
     * Sets p to null.
     * In the logic of the engine, returns a Space with no Piece on it.
     */
    public Space() {
        p = null;
    }

    /**
     * Creates a Space with a predefined Piece on it. Useful for setting up
     *
     * @param P
     */
    public Space(Piece P) {
        p = P;
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
}
