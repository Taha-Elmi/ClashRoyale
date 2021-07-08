package Models.Cards.spells;

import Models.Cards.Card;

abstract public class Spell extends Card {
    private double radius;
    private int level;
    public Spell(int cost) {
        super(cost);
    }
}
