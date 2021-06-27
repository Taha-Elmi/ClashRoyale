package Cards.buildings;

import Cards.Card;
import Cards.Target;
import Interfaces.Hitter;

abstract public class Building extends Card implements Hitter {
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
}
