package Models.Cards.troops;

import Main.Config;
import Models.Cards.Target;

/**
 * Archer
 */
public class Archer extends Troop {
    private static final Speed speed = Speed.MEDIUM;

    /**
     * constructor
     * @param level level
     */
    public Archer(int level) {
        super(3, level,3);
        setSpeed(Speed.MEDIUM);
        setTargetCategory(Target.AIRandGROUND);
        setHitSpeed(1.2);
        setRange(5 * 25);
        setAreaSplash(false);
    }

    /**
     * setter of level and other fields
     * @param level level
     */
    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(125);
            setDamage(33);
        } else if (level == 2) {
            setHp(127);
            setDamage(44);
        } else if (level == 3) {
            setHp(151);
            setDamage(48);
        } else if (level == 4) {
            setHp(166);
            setDamage(53);
        } else if (level == 5) {
            setHp(182);
            setDamage(58);
        } else {
            Config.unknownInputException();
        }
    }

    /**
     * sets speed to default
     */
    @Override
    public void setSpeedToDefault() {
        setSpeed(speed);
    }
}
