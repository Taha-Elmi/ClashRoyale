package Models.Cards.spells;

import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.GameManager.Game;
import Models.Interfaces.Damageable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class Rage extends Spell {
    private double duration;
    private int counter;

    public Rage(int level) {
        super(3,level,5);
        setLevel(level);
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
    public void readyForThrow(ImageView imageView, Point2D dst, Timeline timeline,int playerNum) {
        imageView.setFitWidth(5);
        imageView.setFitHeight(10);
        super.readyForThrow(imageView,dst,timeline,playerNum);
        Spell spell = this;
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                die();
                Timeline timeline1 = new Timeline();
                Circle circle = new Circle(getRadius() * 25);
                circle.setCenterX(dst.getX());
                circle.setCenterY(dst.getY());
                String cssLayout = "-fx-opacity: 0.3";
                circle.setStyle(cssLayout);
                circle.setFill(Color.PURPLE);
                GameCon.getInstance().getBoardPane().getChildren().add(circle);
                Timer timer = new Timer(); //time to end: getDuration
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        task();
                        counter++;
                        if (counter >= duration)
                            timer.cancel();
                    }
                };
                long frameTimeInMilliseconds = (long)(1000.0);
                timer.schedule(timerTask, 0, frameTimeInMilliseconds);

                Game.getInstance().checkSpell(spell,dst,playerNum);
            }
        });
    }

    private void task() {

    }

    @Override
    public void act(Damageable damageable) {

    }


    @Override
    protected boolean isDead() {
        return false;
    }

}
