package Models.Towers;

import Models.Cards.Card;

public class KingTower extends Tower {

    public KingTower() {
        super();
        initialize();
    }

    private void initialize() {
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

        } else if (level == 3) {

        } else if (level == 4) {

        } else if (level == 5) {

        } else;
    }

    @Override
    public void hit(Card card) {

    }
}
