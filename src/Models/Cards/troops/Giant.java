package Models.Cards.troops;

import Models.Cards.Target;

/**
 * Giant
 */
public class Giant extends Troop {
    private final static Speed speed = Speed.SLOW;

    /**
     * constructor
     * @param level level
     */
    public Giant(int level) {
        super(5, level,1);
        setSpeed(Speed.SLOW);
        setHitSpeed(1.5);
        setTargetCategory(Target.BUILDINGS);
        setRange(25);
        setAreaSplash(false);
    }

    /**
     * setter of level and other fields
     * @param level level
     */
    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(2000);
            setDamage(126);
        } else if(level == 2) {
            setHp(2200);
            setDamage(138);
        } else if (level == 3) {
            setHp(2420);
            setDamage(152);
        } else if (level == 4) {
            setHp(2660);
            setDamage(167);
        } else if (level == 5) {
            setHp(2920);
            setDamage(183);
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
