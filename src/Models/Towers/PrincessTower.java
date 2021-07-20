package Models.Towers;

import Models.Cards.Card;
public class PrincessTower extends Tower {

    public PrincessTower() {

    }

    public void initialize() {
        setLevel(1);
        setHitSpeed(0.8);
        setRange(7.5);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setDamage(50);
            setHp(1400);
        } else if (level == 2) {

        } else if (level == 3) {

        } else if (level == 4) {

        } else if (level == 5) {

        } else;
    }


    @Override
    public void run() {

    }
}
