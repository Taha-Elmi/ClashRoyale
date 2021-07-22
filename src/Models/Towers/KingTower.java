package Models.Towers;

import Models.Cards.Card;
import Models.GameManager.Game;
import Models.Graphic.FXManager;

public class KingTower extends Tower {
    private boolean isAwake;

    public KingTower(int level) {
        super(level);
        setRange(0);
        setHitSpeed(1);
        isAwake = false;
    }


    @Override
    public void setLevel(int level) {
        super.setLevel(level);
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
            setHp(2904);
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

    public void wakeUp() {
        if (isAwake)
            return;

        if (Game.getInstance().getPlayer1().getKingTower() == this)
            getOwnerImageView().setImage(FXManager.getImage("/Game/blueKingWakeUp.gif"));
        else
            getOwnerImageView().setImage(FXManager.getImage("/Game/redKingWakeUp.gif"));
        setRange(7);
        isAwake = true;
    }


    @Override
    public void run() {

    }
}
