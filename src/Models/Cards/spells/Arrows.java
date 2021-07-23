package Models.Cards.spells;

import Main.Config;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;

/**
 * Arrows
 */
public class Arrows extends Spell implements Hitter {
    private int areaDamage;

    /**
     * constructor
     * @param level level
     */
    public Arrows(int level) {
        super(3, level, 4);
    }

    /**
     * setter of the area damage
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
            setAreaDamage(144);
        } else if (level == 2) {
            setAreaDamage(156);
        } else if (level == 3) {
            setAreaDamage(174);
        } else if (level == 4) {
            setAreaDamage(189);
        } else if (level == 5) {
            setAreaDamage(210);
        } else {
            Config.unknownInputException();
        }
    }

    /**
     * the act method
     * @param damageable damageable
     */
    @Override
    public void act(Damageable damageable) {
        hit(damageable);
    }

    /**
     * hits
     * @param damageable the card
     */
    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(areaDamage);
    }
}
