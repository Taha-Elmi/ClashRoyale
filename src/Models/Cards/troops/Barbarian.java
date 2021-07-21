package Models.Cards.troops;

import Models.Graphic.FXManager;
import javafx.scene.image.Image;

public class Barbarian extends Troop {

    public Barbarian(int level) {
        super(5, level,5);
        setSpeed(Speed.MEDIUM);
    }

    @Override
    public void setLevel(int level) {

    }


}
