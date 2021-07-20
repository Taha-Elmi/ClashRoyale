package Models.Cards.spells;

import Main.Config;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rage extends Spell {
    private double duration;
    public Rage(int level) {
        super(3,level,5);
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setDuration(6);
        } else if (level == 2) {
            setDuration(6.5);
        } else if (level == 3) {
            setDuration(7);
        } else if (level == 4) {
            setDuration(7.5);
        } else if (level == 5) {
            setDuration(8);
        } else {
            Config.unknownInputException();
        }
    }

    @Override
    public void readyForThrow(ImageView imageView, Point2D dst, Timeline timeline) {
        //Nothing.
    }

    public void readyForThrow() {

    }

    @Override
    protected boolean isDead() {
        return false;
    }

    @Override
    public void run() {

    }
}
