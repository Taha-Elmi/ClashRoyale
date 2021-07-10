package Models.Cards.troops;
import Models.Cards.Card;
import Models.Cards.Target;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;

abstract public class Troop extends Card implements Hitter, Damageable {
    private int level;
    private int hp;
    private int damage;
    private double hitSpeed;
    private Speed speed;
    private Target target;
    private int range;
    private boolean areaSplash;
    private int count;
    public Troop(int cost) {
        super(cost);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    @Override
    public void getDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    public void hit(Card card) {
        if (card instanceof Damageable) {
            Damageable damageable = (Damageable) card;
            damageable.getDamage(damage);
        }
    }

    @Override
    protected boolean isDead() {
        return hp <= 0;
    }
}
