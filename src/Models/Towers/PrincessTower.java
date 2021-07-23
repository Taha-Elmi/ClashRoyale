package Models.Towers;

/**
 * Princess Tower
 */
public class PrincessTower extends Tower {

    /**
     * constructor
     * @param level level
     */
    public PrincessTower(int level) {
        super(level);
        setRange(7.5);
        setHitSpeed(0.8);
    }

    /**
     * setter of level and other fields
     * @param level level
     */
    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        if (level == 1) {
            setDamage(50);
            setHp(1400);
        } else if (level == 2) {
            setHp(1512);
            setDamage(54);
        } else if (level == 3) {
            setHp(1624);
            setDamage(58);
        } else if (level == 4) {
            setHp(1750);
            setDamage(62);
        } else if (level == 5) {
            setHp(1890);
            setDamage(69);
        } else;
    }
}
