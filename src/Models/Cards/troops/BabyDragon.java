package Models.Cards.troops;

import javafx.scene.image.Image;

public class BabyDragon extends Troop {
    public BabyDragon(int level) {
        super(4, level,1);
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
