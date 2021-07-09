package Models.Towers;

import Models.Cards.Card;

public class KingTower extends Tower {

    public void initialize() {
        setLevel(1); // that will automatically set damage and hp based on level;
        setRange(7);
        setHitSpeed(1);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setDamage(50);
            setHp(2400);
        } else if (level == 2) {
            setDamage(53);
            setHp(2568);
        } else if (level == 3) {
            setDamage(57);
            setHp(2736);
        } else if (level == 4) {
            setDamage(60);
            setHp(60);
        } else if (level == 5) {
            setDamage(64);
            setHp(3096);
        } else {
            try {
                throw new Exception("Unknown input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void hit(Card card) {

    }

    @Override
    public void run() {

    }
}
