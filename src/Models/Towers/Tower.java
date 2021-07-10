package Models.Towers;

import Models.Cards.Card;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;

abstract public class Tower implements Hitter, Damageable,Runnable {
    private int level;
    private int hp;
    private int damage;
    private double range;
    private double hitSpeed;

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

    }

    public boolean isDead() {
        return hp <= 0;
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
}
