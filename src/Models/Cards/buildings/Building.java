package Models.Cards.buildings;

import Models.Cards.Card;
import Models.Cards.Target;
import Interfaces.Damageable;
import Interfaces.Hitter;

abstract public class Building extends Card implements Hitter, Damageable,Runnable {
    private int level;
    private int hp;
    private int damage;
    private double hitSpeed;
    private Target target;
    private double range;
    private int lifetime;
    public Building(int cost) {
        super(cost);
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
    public void hit(Card card) {
        if (card instanceof Damageable) {
            Damageable damageable = (Damageable) card;
            damageable.getDamage(damage);
        }
    }

    public abstract void setLevel(int level);

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    protected boolean isDead() {
        return hp <= 0;
    }
}
