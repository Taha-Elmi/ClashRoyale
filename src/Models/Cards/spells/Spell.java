package Models.Cards.spells;

import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Graphic.FXManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

abstract public class Spell extends Card implements Runnable {
    private final double radius;
    public Spell(int cost,int level,double radius) {
        super(cost,level,1);
        this.radius = radius;
    }

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

    public double getRadius() {
        return radius;
    }
    public void readyForThrow(ImageView imageView, Point2D dst, Timeline timeline) {
        setTimeline(timeline);
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(3),
                new KeyValue(imageView.xProperty(),dst.getX()),
                new KeyValue(imageView.yProperty(),dst.getY())
        ));
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                die();
            }
        });
    }
}
