package Models.Cards.troops;

import javafx.scene.image.Image;

public class MiniPekka extends Troop {

    public MiniPekka(int level) {
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
