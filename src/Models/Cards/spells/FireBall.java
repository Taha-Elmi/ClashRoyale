package Models.Cards.spells;

import Main.Config;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;

/**
 * Fire Ball
 */
public class FireBall extends Spell implements Hitter {
    private int areaDamage;

    /**
     * constructor
     * @param level level
     */
    public FireBall(int level) {
        super(4, level,2.5);
    }

    /**
     * setter of area damage
     * @param areaDamage area damage
     */
    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }

    /**
     * setter of the level
     * @param level level
     */
    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setAreaDamage(325);
        } else if (level == 2) {
            setAreaDamage(357);
        } else if (level == 3) {
            setAreaDamage(393);
        } else if (level == 4) {
            setAreaDamage(432);
        } else if (level == 5) {
            setAreaDamage(474);
        } else {
            Config.unknownInputException();
        }
    }

    /**
     * hits
     * @param damageable the card
     */
    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(areaDamage);
    }

    /**
     * the act method
     * @param damageable target
     */
    @Override
    public void act(Damageable damageable) {
        hit(damageable);
    }
}
