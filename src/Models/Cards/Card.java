package Models.Cards;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Card implements Cloneable {
    protected Timeline timeline = new Timeline();
    private final int cost;
    private int level;
    private final int number;
    public Card(int cost,int level,int number) {
        this.cost = cost;
        this.number = number;
        setLevel(level);
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public int getNumber() {
        return number;
    }

    public abstract Image born();
    public abstract void die();
    protected abstract boolean isDead();
    public abstract void setLevel(int level);

    public Timeline getTimeline() {
        return timeline;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
