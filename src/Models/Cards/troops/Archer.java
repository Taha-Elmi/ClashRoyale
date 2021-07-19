package Models.Cards.troops;
import Models.Graphic.FXManager;
import javafx.scene.image.Image;

public class Archer extends Troop {

    public Archer(int level) {
        super(3, level,3);
        setSpeed(Speed.FAST);
    }


    @Override
    public void die() {

    }

    @Override
    public void setLevel(int level) {

    }

}
