package Cards.troops;
import Cards.Card;
import Cards.Target;
import Interfaces.Hitter;

abstract public class Troop extends Card implements Hitter {
    private int level;
    private int hp;
    private int damage;
    private double hitSpeed;
    private Speed speed;
    private Target target;
    private int range;
    private boolean areaSplash;
    private int count;
    public Troop(int cost) {
        super(cost);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

}
