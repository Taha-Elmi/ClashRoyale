package Models;

import Database.BattleHistory;
import Models.Cards.Card;
import java.util.ArrayList;

public class Client {
    private String name;
    private int xp;
    private int level;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<BattleHistory> battleHistories = new ArrayList<>();

    public Client(String name, int xp, int level) {
        this.name = name;
        this.xp = xp;
        this.level = level;
    }
    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public ArrayList<BattleHistory> getBattleHistories() {
        return battleHistories;
    }

    public void setBattleHistories(ArrayList<BattleHistory> battleHistories) {
        this.battleHistories = battleHistories;
    }
}
