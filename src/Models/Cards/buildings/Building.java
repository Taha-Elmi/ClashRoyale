package Models.Cards.buildings;

import Models.Cards.Card;
import Models.Cards.Target;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;

abstract public class Building extends Card implements Hitter, Damageable,Runnable {
    private int hp;
    private double hitSpeed;
    private Target target;
    private double range;
    private int lifetime;

    public double getHitSpeed() {
        return hitSpeed;
    }

    public Target getTarget() {
        return target;
    }

    public double getRange() {
        return range;
    }

    public int getLifetime() {
        return lifetime;
    }

    public Building(int cost, int level, double hitSpeed,
                    Target target, double range, int lifetime) {
        super(cost,level);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifetime = lifetime;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void getDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    protected boolean isDead() {
        return hp <= 0;
    }
}
