package Models.Cards.troops;

import Main.Config;
import Models.Cards.Target;

/**
 * Barbarian
 */
public class Barbarian extends Troop {
    private final static Speed speed = Speed.MEDIUM;

    /**
     * constructor
     * @param level level
     */
    public Barbarian(int level) {
        super(5, level,5);
        setSpeed(Speed.MEDIUM);
        setRange(25);
        setHitSpeed(1.5);
        setAreaSplash(false);
        setTargetCategory(Target.GROUND);
    }

    /**
     * setter of level and other fields
     * @param level level
     */
    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(300);
            setDamage(75);
        } else if (level == 2) {
            setHp(330);
            setDamage(82);
        } else if (level == 3) {
            setHp(363);
            setDamage(90);
        } else if (level == 4) {
            setHp(438);
            setDamage(99);
        } else if (level == 5) {
            setHp(480);
            setDamage(109);
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
