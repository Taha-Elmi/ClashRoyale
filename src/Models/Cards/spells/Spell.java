package Models.Cards.spells;

import Models.Cards.Card;

abstract public class Spell extends Card implements Runnable {
    private final double radius;
    public Spell(int cost,int level,int radius) {
        super(cost,level);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
