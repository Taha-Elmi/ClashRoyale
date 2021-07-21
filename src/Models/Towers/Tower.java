package Models.Towers;

import Models.GameManager.Game;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

abstract public class Tower implements Hitter, Damageable,Runnable {
    private int level;
    private int hp;
    private int damage;
    private double range;
    private double hitSpeed;
    private ImageView imageView;
    private ImageView owner;
    private Timeline timeline;

    public Tower(int level) {
        setLevel(level);
        timeline = new Timeline();
    }
    public void setLevel(int level) {
        this.level = level;
    }
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
        Game.getInstance().dieTower(this);
    }

    public boolean isDead() {
        return hp <= 0;
    }
    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        Game.getInstance().updateHps();
        if (isDead()) {
            hp = 0;
            Game.getInstance().updateHps();
            die();
        }
    }

    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(damage);
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setOwnerImageView(ImageView owner) {
        this.owner = owner;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageView getOwnerImageView() {
        return owner;
    }

    public int getDamage() {
        return damage;
    }

    public double getRange() {
        return range;
    }

    public int getLevel() {
        return level;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public int getHp() {
        return hp;
    }
}
