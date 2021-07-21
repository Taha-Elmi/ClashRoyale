package Models.Cards.troops;
import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.Target;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

abstract public class Troop extends Card implements Hitter, Damageable {
    private int hp;
    private int damage;
    private double hitSpeed;
    private Speed speed;
    private Target targetCategory;
    private int range;
    private boolean areaSplash;
    private int count;
    private Damageable target;
    private boolean isDamaging;

    public Troop(int cost,int level,int number) {
        super(cost,level,number);
    }

    public Point2D findTarget() {
        CardImage cardImage = Game.getInstance().cardToCardImage(this);
        ArrayList<CardImage> enemyList = (Game.getInstance().getPlayer1_list().contains(cardImage) ?
                Game.getInstance().getPlayer1_list() : Game.getInstance().getPlayer2_list());
        Point2D src = new Point2D(GameCon.getInstance().find(cardImage.getImage()).getX(),
                GameCon.getInstance().find(cardImage.getImage()).getY());
        CardImage possibleTarget = null;
        double distance =  0;

        // finding the nearest enemy troop
        for (CardImage enemy : enemyList) {
            if (!(enemy.getCard() instanceof Troop))
                continue;

            Point2D dst = new Point2D(GameCon.getInstance().find(enemy.getImage()).getX(),
                    GameCon.getInstance().find(enemy.getImage()).getY());

            if (possibleTarget == null || src.distance(dst) < distance) {
                possibleTarget = enemy;
                distance = src.distance(dst);
            }
        }

        // checking if the nearest troop is valid to be target
        if (possibleTarget != null && distance <= 100) {
            target = (Troop) possibleTarget.getCard();

            CardImage cardImageToReturn = Game.getInstance().cardToCardImage((Card) target);
            if ((isUnderBridge() && ((Troop) target).isUnderBridge()) || (!isUnderBridge() && !((Troop) target).isUnderBridge()))
                return new Point2D(GameCon.getInstance().find(cardImageToReturn.getImage()).getX(),
                    GameCon.getInstance().find(cardImageToReturn.getImage()).getY());
            else
                return new Point2D(GameCon.getInstance().getNearerBridge(src).getLayoutX(),
                        GameCon.getInstance().getNearerBridge(src).getLayoutY());
        }

        // checking if there is a bridge ahead or not
        int playerNumber = (enemyList == Game.getInstance().getPlayer2_list() ? 1 : 2);

        if ((playerNumber == 1 && isUnderBridge()) || (playerNumber == 2 && !isUnderBridge()))
            return new Point2D(GameCon.getInstance().getNearerBridge(src).getLayoutX(),
                    GameCon.getInstance().getNearerBridge(src).getLayoutY());

        return new Point2D(GameCon.getInstance().getNearerTowerImageView(src, playerNumber).getLayoutX(),
                GameCon.getInstance().getNearerTowerImageView(src, playerNumber).getLayoutY());
    }

    public boolean isUnderBridge() {
        CardImage cardImage = Game.getInstance().cardToCardImage(this);
        ImageView imageView = GameCon.getInstance().find(cardImage.getImage());
        System.out.println("X: " + imageView.getLayoutX() + "\tY: " + imageView.getLayoutY());
        if (imageView.getY() > GameCon.getInstance().getNearerBridge(new Point2D(imageView.getX(), imageView.getY())).getLayoutY())
            return true;
        return false;
    }

    public void step() {
        ImageView imageView = GameCon.getInstance().find(Game.getInstance().cardToCardImage(this).getImage());
        Point2D src = new Point2D(imageView.getX(), imageView.getY());
        Point2D dst = findTarget();
        Point2D path = dst.subtract(src).multiply((1 / dst.distance(src)));
        System.out.println(path.distance(0, 0)); // must print 1
        getTimeline().stop();
        getTimeline().getKeyFrames().clear();
        getTimeline().getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(imageView.xProperty(), src.add(path).getX()),
                        new KeyValue(imageView.yProperty(), src.add(path).getY())
                )
        );
        getTimeline().play();
    }

    public void setTarget(Target targetCategory) {
        this.targetCategory = targetCategory;
    }

    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(damage);
    }

    public void readyForMove(ImageView imageView, Point2D dst,Timeline timeline) {
        setTimeline(timeline);
        timeline.getKeyFrames().clear();
        Point2D src = new Point2D(imageView.getX(), imageView.getY());
        boolean isUnderBridge = true;
        if (isUnderBridge && !(this instanceof BabyDragon)) {
            ImageView nearerBridge = GameCon.getInstance().getNearerBridge(src);
            timeline.getKeyFrames().add(new KeyFrame(
               Duration.seconds(speedToSecond(Speed.toDouble(speed),src.distance(new Point2D(nearerBridge.getLayoutX(),nearerBridge.getLayoutY())))),
               new KeyValue(imageView.xProperty(),nearerBridge.getLayoutX()),
               new KeyValue(imageView.yProperty(),nearerBridge.getLayoutY())
            ));
        }
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(speedToSecond(Speed.toDouble(speed),src.distance(dst))),
                new KeyValue(imageView.xProperty(),dst.getX()),
                new KeyValue(imageView.yProperty(),dst.getY())
        ));
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    private double speedToSecond(double speed, double length) {
        //System.out.println(length / speed);
        return length / speed;
    }
    
    private double speedToLength(Speed speed) {
        return switch (speed) {
            case SLOW -> 0.5 * 25;
            case MEDIUM -> 1 * 25;
            case FAST -> 2 * 25;
        };
    }

    @Override
    protected boolean isDead() {
        return hp <= 0;
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

    public int getDamage() {
        return damage;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
