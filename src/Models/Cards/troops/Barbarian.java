package Models.Cards.troops;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.scene.image.Image;

public class Barbarian extends Troop {

    private final static Speed speed = Speed.MEDIUM;
    public Barbarian(int level) {
        super(5, level,5);
        setSpeed(Speed.MEDIUM);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(300);
            setDamage(75);
        } else if (level == 2) {
            setHp(330);
            setDamage(82);
        } else if (level == 3) {
            setHp(363);
            setDamage(90);
        } else if (level == 4) {
            setHp(438);
            setDamage(99);
        } else if (level == 5) {
            setHp(480);
            setDamage(109);
        } else {
            Config.unknownInputException();
        }
    }


    @Override
    public void setSpeedToDefault() {
        setSpeed(speed);
    }
}
