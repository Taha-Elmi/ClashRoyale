package Models.Cards.troops;

import Main.Config;
import Models.Cards.Target;
import javafx.scene.image.Image;

public class Wizard extends Troop {

    private final static Speed speed = Speed.MEDIUM;

    public Wizard(int level) {
        super(5, level,1);
        setSpeed(Speed.MEDIUM);
        setHitSpeed(1.7);
        setTargetCategory(Target.AIRandGROUND);
        setRange(5 * 25);
        setAreaSplash(true);
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

    @Override
    public void setSpeedToDefault() {
        setSpeed(speed);
    }
}
