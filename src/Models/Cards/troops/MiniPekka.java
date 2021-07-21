package Models.Cards.troops;

import Main.Config;
import javafx.scene.image.Image;

public class MiniPekka extends Troop {

    private final static Speed speed = Speed.FAST;
    public MiniPekka(int level) {
        super(4, level,1);
        setSpeed(Speed.FAST);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(600);
            setDamage(325);
        } else if (level == 2) {
            setHp(660);
            setDamage(357);
        } else if (level == 3) {
            setHp(726);
            setDamage(393);
        } else if (level == 4) {
            setHp(798);
            setDamage(432);
        } else if (level == 5) {
            setHp(876);
            setDamage(474);
        } else {
            Config.unknownInputException();
        }
    }

    @Override
    public void setSpeedToDefault() {
        setSpeed(speed);
    }
}
