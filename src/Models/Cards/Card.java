package Models.Cards;

abstract public class Card {
    private final int cost;
    private int level;
    public Card(int cost,int level) {
        this.cost = cost;
        setLevel(level);
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public abstract void born();
    public abstract void die();
    protected abstract boolean isDead();
    public abstract void setLevel(int level);
}
