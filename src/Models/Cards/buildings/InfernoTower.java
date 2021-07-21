package Models.Cards.buildings;

import Models.Cards.Card;
import Models.Cards.Target;
import Models.Interfaces.Damageable;
import javafx.scene.image.Image;

public class InfernoTower extends Building {
    private int minimumDamage;
    private int maximumDamage;

    public InfernoTower(int level) {
        super(5, level, 0.4, Target.AIRandGROUND, 6, 40);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setMinimumDamage(20);
            setMaximumDamage(400);
            setHp(800);
        } else if (level == 2) {
            setMinimumDamage(22);
            setMaximumDamage(440);
            setHp(880);
        } else if (level == 3) {
            setMinimumDamage(24);
            setMaximumDamage(484);
            setHp(968);
        } else if (level == 4) {
            setMinimumDamage(26);
            setMaximumDamage(532);
            setHp(1064);
        } else if (level == 5) {
            setMinimumDamage(29);
            setMaximumDamage(584);
            setHp(1168);
        } else {
            try {
                throw new Exception("Unknown input!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaximumDamage() {
        return maximumDamage;
    }

    public int getMinimumDamage() {
        return minimumDamage;
    }

    @Override
    public Image born(int playerNum) {
        return null;
    }

    @Override
    public void die() {

    }


    @Override
    public void run() {

    }


    @Override
    public void hit(Damageable damageable) {

    }

    public void setMaximumDamage(int maximumDamage) {
        this.maximumDamage = maximumDamage;
    }

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }
}
