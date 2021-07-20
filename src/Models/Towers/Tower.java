package Models.Towers;

import Models.Cards.Card;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Tower implements Hitter, Damageable,Runnable {
    private int level;
    private int hp;
    private int damage;
    private double range;
    private double hitSpeed;
    private ImageView imageView;
    private Timeline timeline;

    public Tower(int level) {
        setLevel(level);
        timeline = new Timeline();
    }
    public abstract void setLevel(int level);
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public void die() {

    }

    public boolean isDead() {
        return hp <= 0;
    }
    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    public void hit(Card card) {
        if (card instanceof Damageable) {
            Damageable damageable = (Damageable) card;
            damageable.gotDamage(damage);
        }
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getDamage() {
        return damage;
    }

    public double getRange() {
        return range;
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
