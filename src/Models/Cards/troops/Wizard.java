package Models.Cards.troops;

import javafx.scene.image.Image;

public class Wizard extends Troop {

    public Wizard(int level) {
        super(5, level,1);
        setSpeed(Speed.MEDIUM);
    }

    @Override
    public void die() {

    }

    @Override
    public void setLevel(int level) {

    }
}
