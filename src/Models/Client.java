package Models;

import Database.BattleHistory;
import Models.Cards.Card;
import java.util.ArrayList;

/**
 * Client of the application
 */
public class Client {
    private String name;
    private int xp;
    private int level;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<BattleHistory> battleHistories = new ArrayList<>();

    /**
     * constructor
     * @param name name
     * @param xp xp
     * @param level level
     */
    public Client(String name, int xp, int level) {
        this.name = name;
        this.xp = xp;
        this.level = level;
    }

    /**
     * getter of the deck cards
     * @return the deck cards
     */
    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    /**
     * sets deck cards
     * @param deckCards deck cards
     */
    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    /**
     * getter of the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter of the level
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * getter of the xp
     * @return the xp
     */
    public int getXp() {
        return xp;
    }

    /**
     * setter of the level
     * @param level level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * setter of xp
     * @param xp xp
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * getter of battle histories
     * @return battle histories list
     */
    public ArrayList<BattleHistory> getBattleHistories() {
        return battleHistories;
    }
}
