public class BombEffect extends Effect {

    private EffectType effectType = EffectType.Bomb;

    public void doEffect(Space s){
        s.setPiece(null);
    }

    public EffectType getType(){
        return effectType;
    }
}
