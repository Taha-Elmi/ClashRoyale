package Models.Cards.troops;
import javafx.scene.image.Image;

public class Archer extends Troop {

    public Archer(int level) {
        super(3, level,3);
    }

    @Override
    public Image born() {
        return null;
    }

    @Override
    public void die() {

    }

    @Override
    public void setLevel(int level) {

    }

}
