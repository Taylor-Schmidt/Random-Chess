public class BombEffect extends Effect {

    private EffectType effectType = EffectType.Bomb;

    public void doEffect(Space s, Board b, Position p, BoardButton[][] buttons) {

        if (s.getPiece().getType() != Piece.ChessPieceType.KING) {
            s.setPiece(null);
            AudioManager.getInstance().playBoom();

            buttons[p.row][p.col].explode();
        }

        for (Position vector : Position.cardinalDirections) {
            Position newPos = p.add(vector);
            Space adjacentSpace = b.getSpace(newPos);
            if (adjacentSpace != null) {
                Piece adjacentPiece = adjacentSpace.getPiece();

                if (adjacentPiece != null && adjacentPiece.getType() != Piece.ChessPieceType.KING) {
                    adjacentSpace.setPiece(null);

                    buttons[newPos.row][newPos.col].explode();
                }
            }
        }

    }

    public EffectType getType() {
        return effectType;
    }
}
