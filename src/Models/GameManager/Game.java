package Models.GameManager;

public class Game {
    private static Game instance;
    private Player player1;
    private Player player2;
    private Manager manager;
    private TimeManager timeManager;
    private GameMode gameMode;

    public Game(Player player1, Player player2, GameMode gameMode,Manager manager) {
        this.player1 = player1;
        this.player2 = player2;
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
