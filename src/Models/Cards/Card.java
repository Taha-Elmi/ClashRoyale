package Models.Cards;

import Models.GameManager.Game;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Card implements Cloneable {
    protected Timeline timeline;
    private final int cost;
    private int level;
    private final int number;
    public Card(int cost,int level,int number) {
        this.cost = cost;
        this.number = number;
        this.level = level;
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

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public abstract Image born(int playerNum);
    public void die() {
        Game.getInstance().dieCard(Game.getInstance().cardToCardImage(this));
    }
    protected abstract boolean isDead();
    public abstract void setLevel(int level);
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
