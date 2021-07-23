package Models.Towers;

import Models.GameManager.Game;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

/**
 * Parent class of Towers
 */
public abstract class Tower implements Hitter, Damageable {
    private int level;
    private int hp;
    private int damage;
    private double range;
    private double hitSpeed;
    private double counter;
    private ImageView imageView;
    private ImageView owner;
    private Timeline timeline;

    /**
     * constructor
     * @param level level
     */
    public Tower(int level) {
        setLevel(level);
        timeline = new Timeline();
        counter = 0;
    }

    /**
     * setter of level
     * @param level level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * setter of hp
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * setter of damage
     * @param damage damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * setter of the range
     * @param range range
     */
    public void setRange(double range) {
        this.range = range;
    }

    /**
     * setter of the hit speed
     * @param hitSpeed hit speed
     */
    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * kills the tower
     */
    public void die() {
        if (this instanceof KingTower)
            ((KingTower) this).wakeUp();
        else {
            if (Game.getInstance().getPlayer1().getPrincessTowers().contains(this))
                Game.getInstance().getPlayer1().getKingTower().wakeUp();
            else
                Game.getInstance().getPlayer2().getKingTower().wakeUp();
        }
        Game.getInstance().dieTower(this);
    }

    /**
     * checks if dead
     * @return true if dead, false otherwise
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * damages the tower
     * @param damage the damage
     */
    @Override
    public void gotDamage(int damage) {
        if (this instanceof KingTower)
            ((KingTower) this).wakeUp();

        hp -= damage;
        Game.getInstance().updateHps();
        if (isDead()) {
            hp = 0;
            Game.getInstance().updateHps();
            die();
        }
    }

    /**
     * hits
     * @param damageable the card
     */
    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(damage);
    }

    /**
     * setter of the image view
     * @param imageView the image view
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * setter of the owner image view
     * @param owner the image view
     */
    public void setOwnerImageView(ImageView owner) {
        this.owner = owner;
    }

    /**
     * getter of the image view
     * @return the image view
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * getter of the owner image view
     * @return the image view
     */
    public ImageView getOwnerImageView() {
        return owner;
    }

    /**
     * getter of damage
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * getter of the range
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * getter of the level
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * getter of the time line
     * @return the time line
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * getter of the hp
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * getter of the hit speed
     * @return the hit speed
     */
    public double getHitSpeed() {
        return hitSpeed;
    }

    /**
     * getter of the counter
     * @return counter
     */
    public double getCounter() {
        return counter;
    }

    /**
     * setter of the counter
     * @param counter counter
     */
    public void setCounter(double counter) {
        this.counter = counter;
    }

    /**
     * increases the counter
     */
    public void counterIncrease() {
        counter += 0.1;
    }
}
