public abstract class Effect {

    public enum EffectType{
        Bomb, SwitchPiece
    }

    public abstract void doEffect(Space s, Board b, Position p, BoardButton[][] buttons);
    public abstract EffectType getType();
}
