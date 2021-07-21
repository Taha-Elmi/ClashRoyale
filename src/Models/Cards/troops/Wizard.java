package Models.Cards.troops;

import Main.Config;
import javafx.scene.image.Image;

public class Wizard extends Troop {

    public Wizard(int level) {
        super(5, level,1);
        setSpeed(Speed.MEDIUM);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(340);
            setDamage(130);
        } else if (level == 2) {
            setHp(374);
            setDamage(143);
        } else if (level == 3) {
            setHp(411);
            setDamage(157);
        } else if (level == 4) {
            setHp(452);
            setDamage(172);
        } else if(level == 5) {
            setHp(496);
            setDamage(189);
        } else {
            Config.unknownInputException();
        }
    }
}
