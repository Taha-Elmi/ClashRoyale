package Models.Cards.spells;

public class Arrows extends Spell {
    private int areaDamage;
    public Arrows(int cost) {
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
