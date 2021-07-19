package Models.Cards.troops;

import javafx.scene.image.Image;

public class Valkyrie extends Troop {

    public Valkyrie(int level) {
        super(4, level,1);
        setSpeed(Speed.MEDIUM);
    }

    @Override
    public void die() {

    }

    @Override
    public void setLevel(int level) {

    }

}
