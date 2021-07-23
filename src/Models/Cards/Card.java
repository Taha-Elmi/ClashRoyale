package Models.Cards;

import Models.GameManager.Game;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

/**
 * Parent class of all cards
 */
abstract public class Card implements Cloneable {
    protected Timeline timeline;
    private final int cost;
    private int level;
    private final int number;

    /**
     * constructor
     * @param cost cost
     * @param level level
     * @param number number
     */
    public Card(int cost,int level,int number) {
        this.cost = cost;
        this.number = number;
        this.level = level;
        setLevel(level);
        timeline = new Timeline();
    }

    /**
     * getter of the cost field
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * getter of the level field
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * getter of the number field
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * getter of the time line field
     * @return time line
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * setter of the time line field
     * @param timeline time line
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     * born method of cards
     * @param playerNum player number
     * @return the image of the card
     */
    public abstract Image born(int playerNum);

    /**
     * die method of cards
     */
    public void die() {
        try {
            Game.getInstance().dieCard(Game.getInstance().cardToCardImage(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setter of the level and other fields
     * @param level level
     */
    public abstract void setLevel(int level);

    /**
     * overridden method of cloning
     * @return a copied object
     * @throws CloneNotSupportedException exception
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
