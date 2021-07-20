package Models.GameManager;

import Models.Cards.Card;
import java.util.ArrayList;
import java.util.List;
import Models.Towers.*;

public class Player {
    private List<PrincessTower> princessTowers = new ArrayList<>();
    private KingTower kingTower;
    private List<Card> cards;
    private int crown = 0;
    private int elixirs = 4;

    public Player( List<Card> cards) {
        this.cards = cards;
        initialize();
    }

    public void initialize() {
        princessTowers.add(new PrincessTower());
        princessTowers.add(new PrincessTower());
        kingTower = new KingTower();
    }
    public void playCard(Card card){
        sendCardToTheLast(card);

    }
    private void sendCardToTheLast(Card card) {
        cards.remove(card);
        cards.add(card);
    }

    public void setElixirs(int elixirs) {
        this.elixirs = elixirs;
    }

    public int getElixirs() {
        return elixirs;
    }
    public List<Card> getCards() {
        return cards;
    }

    public KingTower getKingTower() {
        return kingTower;
    }

    public List<PrincessTower> getPrincessTowers() {
        return princessTowers;
    }
}
