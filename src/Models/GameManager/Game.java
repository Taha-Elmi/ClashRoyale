package Models.GameManager;
public class Game implements Runnable{
    private Board board;
    private Player player1;
    private Player player2;
    private Manager manager1;
    private Manager manager2;
    private TimeManager timeManager;
    private GameMode gameMode;

    public Game(Board board, Player player1, Player player2, GameMode gameMode) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.gameMode = gameMode;
    }

    public void initialize() {}

    public void finish() {}

    public void update() {}
    @Override
    public void run() {
    }
}
