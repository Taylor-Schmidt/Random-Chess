public class BombEffect extends Effect {

    private EffectType effectType = EffectType.Bomb;

    public void doEffect(Space s){

        if(s.getPiece().getType() != Piece.ChessPieceType.KING) {
            s.setPiece(null);
            AudioManager.getInstance().playBoom();
        }
        System.out.println("Landed on bomb");
    }

    public EffectType getType(){
        return effectType;
    }
}
