package Models.Cards.buildings;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.Target;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import javafx.scene.image.Image;

public class Cannon extends Building {
    private int damage;

    public Cannon(int level) {
        super(6, level, 0.8, Target.GROUND, 5.5, 30);
    }

    @Override
    public Image born(int playerNum) {
        if (playerNum == 1) {
            return FXManager.getImage("/Gifs/Cannon/blue.gif");
        } else if (playerNum == 2) {
            return FXManager.getImage("/Gifs/Cannon/red.gif");
        } else {
            Config.unknownInputException();
        }
        return null;
    }


    @Override
    public void run() {

    }


    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(380);
            setDamage(60);
        } else if (level == 2) {
            setHp(418);
            setDamage(66);
        } else if (level == 3) {
            setHp(459);
            setDamage(72);
        } else if (level == 4) {
            setHp(505);
            setDamage(79);
        } else if (level == 5) {
            setHp(554);
            setDamage(87);
        } else {
            try {
                throw new Exception("Unknown input!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
