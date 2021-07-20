package Models.Cards.troops;
import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
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
        // needs to be completed...
        return null;
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
    public void getDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    public void hit(Card card) {
        if (card instanceof Damageable) {
            Damageable damageable = (Damageable) card;
            damageable.getDamage(damage);
        }
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
}
