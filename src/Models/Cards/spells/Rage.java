package Models.Cards.spells;

import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.troops.*;
import Models.GameManager.Game;
import Models.Interfaces.Damageable;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Rage
 */
public class Rage extends Spell {
    private double duration;
    private int counter = 0;

    /**
     * constructor
     * @param level level
     */
    public Rage(int level) {
        super(3,level,5);
        setLevel(level);
    }

    /**
     * setter of the duration
     * @param duration duration
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * setter of the level field
     * @param level level
     */
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

    /**
     * makes it ready for throw
     * @param imageView image view
     * @param dst dst
     * @param timeline time line
     * @param playerNum player number
     */
    @Override
    public void readyForThrow(ImageView imageView, Point2D dst, Timeline timeline,int playerNum) {
        imageView.setFitWidth(10);
        imageView.setFitHeight(20);
        super.readyForThrow(imageView,dst,timeline,playerNum);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                die();
                Circle circle = new Circle(getRadius() * 25);
                circle.setCenterX(dst.getX());
                circle.setCenterY(dst.getY());
                String cssLayout = "-fx-opacity: 0.4";
                circle.setStyle(cssLayout);
                circle.setFill(Color.PURPLE);
                GameCon.getInstance().getBoardPane().getChildren().add(circle);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        task(playerNum,dst);
                        counter++;
                        if (counter >= duration) {
                            Platform.runLater( () ->
                                    GameCon.getInstance().getBoardPane().getChildren().remove(circle)
                            );
                            timer.cancel();
                        }
                    }
                };
                long frameTimeInMilliseconds = (long)(1000.0);
                timer.schedule(timerTask, 0, frameTimeInMilliseconds);
            }
        });
    }

    private void task(int playerNum,Point2D src) {
        List<CardImage> cardImages;
        if (playerNum == 1) {
            cardImages = Game.getInstance().getPlayer1_list();
        } else {
            cardImages = Game.getInstance().getPlayer2_list();
        }
        Point2D dst;
        double distance = 0;
        for (CardImage cardImage : cardImages) {
            if (!(cardImage.getCard() instanceof Troop)) {
                continue;
            }
            dst = new Point2D(GameCon.getInstance().find(cardImage.getImage()).getX(),
                    GameCon.getInstance().find(cardImage.getImage()).getY());
            distance = src.distance(dst);

            if (distance > getRadius() * 25) {
                setToNormal(cardImage);
                continue;
            } else {
                act((Troop) cardImage.getCard());
            }
        }
    }

    /**
     * returns card to normal stage
     * @param cardImage list of cards
     */
    private void setToNormal(CardImage cardImage) {
        Card card = cardImage.getCard();
        ((Troop) card).setSpeedToDefault();
    }

    /**
     * act method
     * @param damageable target
     */
    @Override
    public void act(Damageable damageable) {
        //Nothing
    }

    /**
     * overloaded act method
     * @param troop troop
     */
    public void act(Troop troop) {
        if (troop.getSpeed() == Speed.SLOW) {
            troop.setSpeed(Speed.RAGE_SLOW);
        } else if (troop.getSpeed() == Speed.MEDIUM) {
            troop.setSpeed(Speed.RAGE_MEDIUM);
        } else if (troop.getSpeed() == Speed.FAST) {
            troop.setSpeed(Speed.RAGE_FAST);
        } else {
            return;
        }
    }
}
