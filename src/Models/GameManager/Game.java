package Models.GameManager;

import Models.Cards.Card;

import java.util.ArrayList;

public class Game {
    private static Game instance;
    private Player player1;
    private ArrayList<Card> player1_list;
    private Player player2;
    private ArrayList<Card> player2_list;
    private Manager manager;
    private GameMode gameMode;

    public Game(Player player1, Player player2, GameMode gameMode,Manager manager) {
        this.player1 = player1;
        this.player2 = player2;
        player1_list = new ArrayList<>();
        player2_list = new ArrayList<>();
        this.gameMode = gameMode;
        this.manager = manager;
        instance = this;
    }

    public void initialize() {}

    public void finish() {}

    public void update() {}

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public static Game getInstance() {
        return instance;
    }
}
