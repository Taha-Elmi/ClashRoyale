package Models.Cards.spells;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.GameManager.Game;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FireBall extends Spell implements Hitter {
    private int areaDamage;
    public FireBall(int level) {
        super(4, level,2.5);
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
            setAreaDamage(325);
        } else if (level == 2) {
            setAreaDamage(357);
        } else if (level == 3) {
            setAreaDamage(393);
        } else if (level == 4) {
            setAreaDamage(432);
        } else if (level == 5) {
            setAreaDamage(474);
        } else {
            Config.unknownInputException();
        }
    }

    protected boolean isDead() {
        return false;
    }


    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(areaDamage);
    }

    @Override
    public void act(Damageable damageable) {
        hit(damageable);
    }
}
