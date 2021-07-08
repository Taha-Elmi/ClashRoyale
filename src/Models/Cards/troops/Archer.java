package Models.Cards.troops;
import Models.Cards.Target;

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
