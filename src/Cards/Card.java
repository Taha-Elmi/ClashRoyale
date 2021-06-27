package Cards;

abstract public class Card {
    private int cost;

    public Card(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public abstract void born();
    public abstract void die();
}
