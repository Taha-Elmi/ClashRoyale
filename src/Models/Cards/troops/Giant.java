package Models.Cards.troops;

import javafx.scene.image.Image;

public class Giant extends Troop {

    public Giant(int level) {
        super(5, level,1);
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
