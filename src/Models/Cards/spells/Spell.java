package Models.Cards.spells;

import Models.Cards.Card;

abstract public class Spell extends Card implements Runnable {
    private final double radius;
    public Spell(int cost,int level,double radius) {
        super(cost,level,1);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
