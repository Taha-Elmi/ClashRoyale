package Models.Cards.troops;
import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.Target;
import Models.Cards.spells.Spell;
import Models.GameManager.Game;
import Models.GameManager.Player;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Interfaces.Flyer;
import Models.Interfaces.Hitter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Parent class of all troops
 */
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
    private double counter;

    /**
     * constructor
     * @param cost cost
     * @param level level
     * @param number number
     */
    public Troop(int cost,int level,int number) {
        super(cost,level,number);
        isDamaging = false;
    }

    /**
     * finds the target
     * @return the location to go
     */
    public Point2D findTarget() {
        CardImage cardImage = Game.getInstance().cardToCardImage(this);
        ArrayList<CardImage> enemyList = (Game.getInstance().getPlayer1_list().contains(cardImage) ?
                Game.getInstance().getPlayer2_list() : Game.getInstance().getPlayer1_list());
        Point2D src = new Point2D(GameCon.getInstance().find(cardImage.getImage()).getX(),
                GameCon.getInstance().find(cardImage.getImage()).getY());
        CardImage possibleTarget = null;
        double distance =  0;

        // finding the nearest enemy troop
        for (CardImage enemy : enemyList) {
            if (targetCategory == Target.BUILDINGS)
                break;
            if (enemy.getCard() instanceof Spell)
                continue;
            if (targetCategory == Target.GROUND && enemy.getCard() instanceof Flyer)
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
            if ((isUnderBridge() && ((Troop) target).isUnderBridge()) || (!isUnderBridge() && !((Troop) target).isUnderBridge()) || this instanceof BabyDragon)
                return new Point2D(GameCon.getInstance().find(cardImageToReturn.getImage()).getX(),
                    GameCon.getInstance().find(cardImageToReturn.getImage()).getY());
            else
                return new Point2D(GameCon.getInstance().getNearerBridge(src).getLayoutX(),
                        GameCon.getInstance().getNearerBridge(src).getLayoutY());
        }

        // finding the target tower
        int playerNumber = (enemyList == Game.getInstance().getPlayer1_list() ? 2 : 1);
        ImageView nearerTowerImageView = GameCon.getInstance().getNearerTowerImageView(src, playerNumber);
        Player enemy = (playerNumber == 1 ? Game.getInstance().getPlayer2() : Game.getInstance().getPlayer1());
        if (nearerTowerImageView.equals(enemy.getKingTower().getImageView()))
            target = enemy.getKingTower();
        else if (nearerTowerImageView.equals(enemy.getPrincessTowers().get(0).getImageView()))
            target = enemy.getPrincessTowers().get(0);
        else
            target = enemy.getPrincessTowers().get(1);

        // checking if there is a bridge ahead or not
        if (((playerNumber == 1 && isUnderBridge()) || (playerNumber == 2 && !isUnderBridge())) && !(this instanceof BabyDragon))
            return new Point2D(GameCon.getInstance().getNearerBridge(src).getLayoutX(),
                    GameCon.getInstance().getNearerBridge(src).getLayoutY());

        return new Point2D(nearerTowerImageView.getX(), nearerTowerImageView.getY());
    }

    /**
     * checks if the troop is under bridge or not
     * @return true if it's under the bridges and false otherwise
     */
    public boolean isUnderBridge() {
        CardImage cardImage = Game.getInstance().cardToCardImage(this);
        ImageView imageView = GameCon.getInstance().find(cardImage.getImage());
        if (imageView.getY() > GameCon.getInstance().getNearerBridge(new Point2D(imageView.getX(), imageView.getY())).getLayoutY())
            return true;
        return false;
    }

    /**
     * moves the troop for one step, or fight
     */
    public void step() {
        checkIfIsDamaging();
        if (isDamaging) {
            if (counter < hitSpeed) {
                counter += 0.1;
                return;
            }
            hit(target);
            damageEmote();
            counter = 0;
            return;
        }
        ImageView imageView = GameCon.getInstance().find(Game.getInstance().cardToCardImage(this).getImage());
        Point2D src = new Point2D(imageView.getX(), imageView.getY());
        Point2D dst = findTarget();
        Point2D path = dst.subtract(src).multiply((speedToLength(speed) / dst.distance(src)));
        getTimeline().stop();
        getTimeline().getKeyFrames().clear();
        setTimeline(new Timeline());
        getTimeline().getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(imageView.xProperty(), src.add(path).getX()),
                        new KeyValue(imageView.yProperty(), src.add(path).getY())
                )
        );
        getTimeline().play();
    }

    private void checkIfIsDamaging() {
        CardImage cardImage = Game.getInstance().cardToCardImage(this);
        ImageView imageView = GameCon.getInstance().find(cardImage.getImage());
        int playerNumber = (Game.getInstance().getPlayer1_list().contains(this) ? 1 : 2);
        if (target == null || target.isDead()) {
            target = null;
            isDamaging = false;
            cardImage.setNormalGif();
            imageView.setImage(cardImage.getImage());
            return;
        }
        Point2D src = new Point2D(imageView.getX(), imageView.getY());
        Point2D dst;
        if (target instanceof Troop) {
            CardImage targetCardImage = Game.getInstance().cardToCardImage((Card) target);
            dst = new Point2D(GameCon.getInstance().find(targetCardImage.getImage()).getX(),
                    GameCon.getInstance().find(targetCardImage.getImage()).getY());
        } else {
            dst = new Point2D(GameCon.getInstance().getNearerTowerImageView(src, playerNumber).getX(),
                    GameCon.getInstance().getNearerTowerImageView(src, playerNumber).getY());
        }
        if (dst.distance(src) > range) {
            isDamaging = false;
            cardImage.setNormalGif();
        } else {
            isDamaging = true;
            cardImage.setDamageGif();
        }
        imageView.setImage(cardImage.getImage());
    }

    /**
     * setter of the target category
     * @param targetCategory target category
     */
    public void setTargetCategory(Target targetCategory) {
        this.targetCategory = targetCategory;
    }

    /**
     * gets damage
     * @param damage the damage
     */
    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    /**
     * hits
     * @param damageable the card
     */
    @Override
    public void hit(Damageable damageable) {
        damageable.gotDamage(damage);
    }

    /**
     * setter of the speed
     * @param speed
     */
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }
    
    private double speedToLength(Speed speed) {
        return switch (speed) {
            case SLOW -> 0.5 * 25;
            case MEDIUM -> 1 * 25;
            case FAST -> 2 * 25;
            case RAGE_SLOW -> 0.5 * 1.4 * 25;
            case RAGE_MEDIUM -> 1 * 25 * 1.4;
            case RAGE_FAST -> 2 * 25 * 1.4;
        };
    }

    /**
     * checks if dead
     * @return true if dead, false otherwise
     */
    @Override
    public boolean isDead() {
        return hp <= 0;
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
     * creates an emote when damaging.
     *
     * some troops implements it and some not.
     * so we have it here, so that if a troop doesn't implement it, nothing happens.
     */
    public void damageEmote(){}

    /**
     * getter of the damage field
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * setter of the hp
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * setter of the damage
     * @param damage damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * sets speed to default
     */
    public abstract void setSpeedToDefault();

    /**
     * getter of speed
     * @return speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * setter of the hit speed
     * @param hitSpeed hit speed
     */
    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * setter of the range
     * @param range range
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * setter of area splash
     * @param areaSplash area splash
     */
    public void setAreaSplash(boolean areaSplash) {
        this.areaSplash = areaSplash;
    }

    /**
     * getter of the target
     * @return target
     */
    public Damageable getTarget() {
        return target;
    }
}
