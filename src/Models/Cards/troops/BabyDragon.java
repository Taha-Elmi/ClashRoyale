package Models.Cards.troops;

import Main.Config;
import javafx.scene.image.Image;

public class BabyDragon extends Troop {
    public BabyDragon(int level) {
        super(4, level,1);
        setSpeed(Speed.FAST);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(800);
            setDamage(100);
        } else if (level == 2) {
            setHp(880);
            setDamage(110);
        } else if (level == 3) {
            setHp(968);
            setDamage(121);
        } else if (level == 4) {
            setHp(1064);
            setDamage(133);
        } else if (level == 5) {
            setHp(1168);
            setDamage(146);
        } else {
            Config.unknownInputException();
        }
    }

}
