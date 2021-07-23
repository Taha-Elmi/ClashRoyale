package Models.Cards.buildings;

import Models.Cards.Card;
import Models.Cards.Target;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * Parent class of buildings
 */
abstract public class Building extends Card implements Hitter, Damageable {
    private int hp;
    private double hitSpeed;
    private Target target;
    private double range;
    private int lifetime;

    /**
     * constructor
     * @param cost cost
     * @param level level
     * @param hitSpeed hit speed
     * @param target target
     * @param range range
     * @param lifetime lifetime
     */
    public Building(int cost, int level, double hitSpeed,
                    Target target, double range, int lifetime) {
        super(cost,level,1);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifetime = lifetime;
    }

    /**
     * getter of the hit speed
     * @return hit speed
     */
    public double getHitSpeed() {
        return hitSpeed;
    }

    /**
     * getter of the target
     * @return target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * getter of the range
     * @return range
     */
    public double getRange() {
        return range;
    }

    /**
     * getter of the life time
     * @return life time
     */
    public int getLifetime() {
        return lifetime;
    }

    /**
     * setter of the hp field
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * gets damage
     * @param damage the damage
     */
    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    /**
     * checks if dead
     * @return true if dead, false otherwise
     */
    @Override
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * makes ready to born
     * @param imageView image view
     * @param playerNumber player number
     * @param src src
     */
    public abstract void readyForBorn(ImageView imageView, int playerNumber, Point2D src);

    /**
     * hits the target
     * @param damageable the card
     */
    @Override
    public void hit(Damageable damageable) {
        if (this instanceof Cannon) {
            Cannon cannon = (Cannon) this;
            damageable.gotDamage(cannon.getDamage());
        } else {}
    }

}
