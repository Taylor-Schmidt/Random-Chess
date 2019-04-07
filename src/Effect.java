public abstract class Effect {

    public enum EffectType{
        Bomb
    }

    public abstract void doEffect(Space s);
    public abstract EffectType getType();
}
