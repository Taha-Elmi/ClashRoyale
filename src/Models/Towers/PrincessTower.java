package Models.Towers;

import Models.Cards.Card;
public class PrincessTower extends Tower {

    public PrincessTower(int level) {
        super(level);
        setRange(7.5);
        setHitSpeed(0.8);
    }

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


    @Override
    public void run() {

    }
}
