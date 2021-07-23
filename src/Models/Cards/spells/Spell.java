package Models.Cards.spells;

import Main.Config;
import Models.Cards.Card;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
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

/**
 * Parent class of all spells
 */
public abstract class Spell extends Card {
    private final double radius;

    /**
     * constructor
     * @param cost cost
     * @param level level
     * @param radius radius
     */
    public Spell(int cost,int level,double radius) {
        super(cost,level,1);
        this.radius = radius;
    }

    /**
     * makes it born
     * @param playerNum player number
     * @return the image
     */
    @Override
    public Image born(int playerNum) {
        String nameOfGif = "";
        if (playerNum == 1) {
            nameOfGif = "/forward.gif";
        } else if (playerNum == 2) {
            nameOfGif = "/backward.gif";
        } else {
            Config.unknownInputException();
        }
        return FXManager.getImage("/Gifs/" + getClass().getSimpleName() + nameOfGif);
    }

    /**
     * getter of the radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * makes it ready for throw
     * @param imageView image view
     * @param dst dst
     * @param timeline time line
     * @param playerNum player number
     */
    public void readyForThrow(ImageView imageView, Point2D dst, Timeline timeline,int playerNum) {
        setTimeline(timeline);
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(3),
                new KeyValue(imageView.xProperty(),dst.getX()),
                new KeyValue(imageView.yProperty(),dst.getY())
        ));
        Spell spell = this;
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Game.getInstance().checkSpell(spell,dst,playerNum);
                die();
            }
        });
    }

    /**
     * the act method
     * @param damageable target
     */
    public abstract void act(Damageable damageable);
}
