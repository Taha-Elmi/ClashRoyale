package Models.GameManager;

import Models.Cards.Card;
import java.util.ArrayList;
import java.util.List;
import Models.Towers.*;

/**
 * represents a player of the game
 */
public class Player {
    private List<PrincessTower> princessTowers = new ArrayList<>();
    private KingTower kingTower;
    private List<Card> cards;
    private int crown = 0;
    private int elixirs = 4;
    private int hp;

    /**
     * constructor
     * @param cards cards
     */
    public Player(List<Card> cards) {
        this.cards = cards;
        initialize();
    }

    /**
     * Initialize
     */
    public void initialize() {
        princessTowers.add(new PrincessTower(1));
        princessTowers.add(new PrincessTower(1));
        kingTower = new KingTower(1);
    }

    /**
     * plays a card
     */
    public void playCard(Card card){
        sendCardToTheLast(card);

    }

    private void sendCardToTheLast(Card card) {
        cards.remove(card);
        cards.add(card);
    }

    /**
     * setter of the elixir field
     * @param elixirs
     */
    public void setElixirs(int elixirs) {
        this.elixirs = elixirs;
    }

    /**
     * getter of the elixir field
     * @return elixir
     */
    public int getElixirs() {
        return elixirs;
    }

    /**
     * getter of the cards field
     * @return cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * getter of the King Tower
     * @return the King Tower
     */
    public KingTower getKingTower() {
        return kingTower;
    }

    /**
     * getter of the Princess Tower list
     * @return the list
     */
    public List<PrincessTower> getPrincessTowers() {
        return princessTowers;
    }

    /**
     * getter of the crowns field
     * @return
     */
    public int getCrown() {
        return crown;
    }

    /**
     * setter of the crowns field
     * @param crown crowns
     */
    public void setCrown(int crown) {
        this.crown = crown;
    }

    /**
     * setter of the hp field
     * @param hp hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * getter of the hp field
     * @return hp
     */
    public int getHp() {
        return hp;
    }
}
