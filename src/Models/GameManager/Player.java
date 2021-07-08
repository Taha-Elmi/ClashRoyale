package Models.GameManager;

import Models.Cards.Card;
import Models.Client;
import java.util.ArrayList;
import java.util.List;
import Models.Towers.*;
public class Player {
    private List<PrincessTower> princessTowers = new ArrayList<>();
    private KingTower kingTower;
    private List<Card> cards = new ArrayList<>();
    private int crown = 0;
    private int elixirs = 4;
    private Client client;

    public void playCard(Card card){}

}
