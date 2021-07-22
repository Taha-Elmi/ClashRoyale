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
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InfernoTower extends Building {
    private int minimumDamage;
    private int maximumDamage;
    private int damage;
    private int hitCounter = 0;
    private int lifeCounter = 0;
    private Line line;

    public InfernoTower(int level) {
        super(5, level, 0.4, Target.AIRandGROUND, 6, 40);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setMinimumDamage(20);
            setMaximumDamage(400);
            setHp(800);
        } else if (level == 2) {
            setMinimumDamage(22);
            setMaximumDamage(440);
            setHp(880);
        } else if (level == 3) {
            setMinimumDamage(24);
            setMaximumDamage(484);
            setHp(968);
        } else if (level == 4) {
            setMinimumDamage(26);
            setMaximumDamage(532);
            setHp(1064);
        } else if (level == 5) {
            setMinimumDamage(29);
            setMaximumDamage(584);
            setHp(1168);
        } else {
            try {
                throw new Exception("Unknown input!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaximumDamage() {
        return maximumDamage;
    }

    public int getMinimumDamage() {
        return minimumDamage;
    }

    @Override
    public Image born(int playerNum) {
        return switch (playerNum) {
            case 1 -> FXManager.getImage("/Gifs/Inferno/blue.gif");
            case 2 -> FXManager.getImage("/Gifs/Inferno/red.gif");
            default -> throw new IllegalStateException("Unexpected value: " + playerNum);
        };
    }

    public void readyForBorn(ImageView imageView, int playerNumber, Point2D src) {
        damage = minimumDamage;
        line = new Line();
        line.setStartX(imageView.getX());
        line.setStartY(imageView.getY());
        line.setFill(Color.RED);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                hitCounter++;
                if (hitCounter >= getHitSpeed() * 10) {
                    if (damage < maximumDamage)
                        damage += 20;
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
            if (!(enemy instanceof Damageable))
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
        if (target == null || target.getCard() instanceof Spell || distance > getRange() * 70) {
            GameCon.getInstance().getBoardPane().getChildren().remove(line);
            return;
        }
        ImageView targetImageView = GameCon.getInstance().find(target.getImage());

        line.setEndX(targetImageView.getX());
        line.setEndY(targetImageView.getY());
        if (!(GameCon.getInstance().getBoardPane().getChildren().contains(line)))
            GameCon.getInstance().getBoardPane().getChildren().add(line);
        ((Damageable) target).gotDamage(damage);
    }


    @Override
    public void run() {

    }


    public void setMaximumDamage(int maximumDamage) {
        this.maximumDamage = maximumDamage;
    }

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }
}
