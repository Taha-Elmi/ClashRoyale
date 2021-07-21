package Models.Cards.troops;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.scene.image.Image;

public class Archer extends Troop {

    private static final Speed speed = Speed.FAST;
    public Archer(int level) {
        super(3, level,3);
        setSpeed(speed);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(125);
            setDamage(33);
        } else if (level == 2) {
            setHp(127);
            setDamage(44);
        } else if (level == 3) {
            setHp(151);
            setDamage(48);
        } else if (level == 4) {
            setHp(166);
            setDamage(53);
        } else if (level == 5) {
            setHp(182);
            setDamage(58);
        } else {
            Config.unknownInputException();
        }
    }

    @Override
    public void setSpeedToDefault() {
        setSpeed(speed);
    }
}
