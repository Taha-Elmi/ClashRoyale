package Models.Cards.spells;

import Main.Config;
import Models.Cards.Card;
import Models.Interfaces.Damageable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Arrows extends Spell {
    private int areaDamage;
    public Arrows(int level) {
        super(3, level, 4);
    }

    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }

    public int getAreaDamage() {
        return areaDamage;
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setAreaDamage(144);
        } else if (level == 2) {
            setAreaDamage(156);
        } else if (level == 3) {
            setAreaDamage(174);
        } else if (level == 4) {
            setAreaDamage(189);
        } else if (level == 5) {
            setAreaDamage(210);
        } else {
            Config.unknownInputException();
        }
    }


    @Override
    protected boolean isDead() {
        return false;
    }

    @Override
    public void act(Damageable damageable) {

    }
}
