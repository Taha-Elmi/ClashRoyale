package Models.Cards.troops;

import Models.Graphic.FXManager;
import javafx.scene.image.Image;

public class Barbarian extends Troop {

    public Barbarian(int level) {
        super(5, level,5);
        setSpeed(Speed.MEDIUM);
    }

    @Override
    public Image born() {
        return FXManager.getImage("/Gifs/Barbarian/forward.gif");
    }

    @Override
    public void die() {

    }

    @Override
    public void setLevel(int level) {

    }


}
