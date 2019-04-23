public abstract class Effect {

    public enum EffectType{
        Bomb, SwitchPiece
    }

    public abstract void doEffect(Space s, Board b, int row, int col, BoardButton[][] buttons);
    public abstract EffectType getType();
}
