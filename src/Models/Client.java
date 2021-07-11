package Models;

import Models.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private int xp;
    private int level;
    private ArrayList<Card> deckCards = new ArrayList<>();

    public Client(String name, int xp, int level) {
        this.name = name;
        this.xp = xp;
        this.level = level;
    }

    public void signup(String username, String password) {
    }
    public void login(String username) {
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
}
