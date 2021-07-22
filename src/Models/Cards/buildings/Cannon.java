package Models.Cards.buildings;

import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.Target;
import Models.Cards.spells.Spell;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Interfaces.Flyer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Cannon extends Building {
    private int damage;
    private int hitCounter = 0;
    private int lifeCounter = 0;

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

    public void readyForBorn(ImageView imageView, int playerNumber, Point2D src) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                hitCounter++;
                if (hitCounter >= getHitSpeed() * 10) {
                    Platform.runLater(() ->
                            task(playerNumber,imageView)
                    );
                    hitCounter = 0;
                }
                lifeCounter++;
                if (lifeCounter >= getLifetime() * 10) {
                    lifeCounter = 0;
                    Platform.runLater( () -> {
                                GameCon.getInstance().getBoardPane().getChildren().remove(imageView);
                                die();
                            }
                    );
                    timer.cancel();
                }
            }
        };
        long frameTimeInMilliseconds = (long)(100.0);
        timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }
    private void task(int playerNum,ImageView imageView) {
        Point2D src = new Point2D(imageView.getX(), imageView.getY());
        List<CardImage> cardImages;
        if (playerNum == 1) {
            cardImages = Game.getInstance().getPlayer2_list();
        } else {
            cardImages = Game.getInstance().getPlayer1_list();
        }
        Point2D dst;
        CardImage target = null;
        double distance = 0;
        for (CardImage enemy : cardImages) {
            if (!(enemy.getCard() instanceof Damageable) || (enemy instanceof Flyer))
                continue;
            dst = new Point2D(GameCon.getInstance().find(enemy.getImage()).getX(),
                    GameCon.getInstance().find(enemy.getImage()).getY());
            if (target == null) {
                target = enemy;
                distance = src.distance(dst);
            } else if (src.distance(dst) < distance) {
                target = enemy;
                distance = src.distance(dst);
            }
        }
        if (target == null || target.getCard() instanceof Spell || distance > getRange() * 70)
            return;

        Circle circle = new Circle(8);
        circle.setCenterY(imageView.getY());
        circle.setCenterX(imageView.getX());
        ImageView targetImageView = GameCon.getInstance().find(target.getImage());

        double duration = src.distance(targetImageView.getX(), targetImageView.getY()) / 70;
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duration),
                new KeyValue(circle.centerXProperty(), targetImageView.getX()),
                new KeyValue(circle.centerYProperty(), targetImageView.getY())
        ));
        CardImage finalTarget = target;
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameCon.getInstance().getBoardPane().getChildren().remove(circle);
                Damageable damageable = (Damageable) finalTarget.getCard();
                hit(damageable);
            }
        });
        GameCon.getInstance().getBoardPane().getChildren().add(circle);
        timeline.play();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
