package Models.Cards.buildings;

import Controllers.GameCon;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.Target;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Troop;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import Models.Towers.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

abstract public class Building extends Card implements Hitter, Damageable,Runnable {
    private int lifeCounter = 0;
    private int hp;
    private int hitCounter = 0;
    private double hitSpeed;
    private Target target;
    private double range;
    private int lifetime;

    public double getHitSpeed() {
        return hitSpeed;
    }

    public Target getTarget() {
        return target;
    }

    public double getRange() {
        return range;
    }

    public int getLifetime() {
        return lifetime;
    }

    public Building(int cost, int level, double hitSpeed,
                    Target target, double range, int lifetime) {
        super(cost,level,1);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifetime = lifetime;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }
    public void readyForBorn(ImageView imageView, int playerNumber, Point2D src) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                hitCounter++;
                if (hitCounter >= hitSpeed * 10) {
                    Platform.runLater(() ->
                            task(playerNumber,imageView)
                    );
                    hitCounter = 0;
                }
                lifeCounter++;
                if (lifeCounter >= lifetime * 10) {
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
            cardImages = Game.getInstance().getPlayer1_list();
        } else {
            cardImages = Game.getInstance().getPlayer2_list();
        }
        Point2D dst;
        CardImage target = null;
        double distance = 0;
        for (CardImage enemy : cardImages) {
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

        Circle circle = new Circle(15);
        circle.setCenterY(imageView.getY());
        circle.setCenterX(imageView.getX());
        GameCon.getInstance().getBoardPane().getChildren().add(circle);
        ImageView targetImageView = GameCon.getInstance().find(target.getImage());
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(1),
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
        timeline.play();
    }

    @Override
    public void hit(Damageable damageable) {
        if (this instanceof Cannon) {
            Cannon cannon = (Cannon) this;
            damageable.gotDamage(cannon.getDamage());
        } else {}
    }

}
