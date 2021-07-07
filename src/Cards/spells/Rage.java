package Cards.spells;

public class Rage extends Spell {
    private double duration;
    public Rage(int cost) {
        super(cost);
    }

    @Override
    public void born() {

    }

    @Override
    public void die() {

    }

    @Override
    protected boolean isDead() {
        return false;
    }

}
