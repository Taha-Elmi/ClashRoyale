package Models;

import Models.Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private int xp;
    private int level;
    private List<Card> cards = new ArrayList<>();
    private ArrayList<Card> deckCards = new ArrayList<>();
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
}
