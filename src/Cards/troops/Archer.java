package Cards.troops;
import Cards.Card;
import Cards.Target;

public class Archer extends Troop {
    public Archer(int cost) {
        super(cost);
        setTarget(Target.AIRandGROUND);
    }

    @Override
    public void born() {

    }

    @Override
    public void die() {

    }

}
