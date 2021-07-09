package Models.Cards.spells;

public class FireBall extends Spell {
    private int areaDamage;
    public FireBall(int cost) {
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

    @Override
    public void run() {

    }
}
