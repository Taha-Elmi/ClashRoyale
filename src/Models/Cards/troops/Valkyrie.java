package Models.Cards.troops;

import Main.Config;
import Models.Cards.Target;

/**
 * Valkyrie
 */
public class Valkyrie extends Troop {
    private final static Speed speed = Speed.MEDIUM;

    /**
     * constructor
     * @param level level
     */
    public Valkyrie(int level) {
        super(4, level,1);
        setSpeed(Speed.MEDIUM);
        setHitSpeed(1.5);
        setTargetCategory(Target.GROUND);
        setRange(25);
        setAreaSplash(true);

    }

    /**
     * sets level and other fields
     * @param level level
     */
    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(880);
            setDamage(120);
        } else if (level == 2) {
            setHp(968);
            setDamage(132);
        } else if (level == 3) {
            setHp(1064);
            setDamage(145);
        } else if (level == 4) {
            setHp(1170);
            setDamage(159);
        } else if (level == 5) {
            setHp(1284);
            setDamage(175);
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
