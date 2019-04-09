public abstract class Effect {

    public enum EffectType{
        Bomb, SwitchPiece
    }

    public abstract void doEffect(Space s);
    public abstract EffectType getType();
}
