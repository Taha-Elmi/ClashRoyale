package Models.GameManager;

public class Game {
    private static Game instance;
    private Board board;
    private Player player1;
    private Player player2;
    private Manager manager1;
    private Manager manager2;
    private TimeManager timeManager;
    private GameMode gameMode;

    public Game(Player player1, Player player2, GameMode gameMode) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameMode = gameMode;
        instance = this;
    }

    public void initialize() {}

    public void finish() {}

    public void update() {}

    public static Game getInstance() {
        return instance;
    }
}
