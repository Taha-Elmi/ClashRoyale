package Models.GameManager;

public interface Manager {
    void waitForAction();
    void action();
    String getName();
    Player getPlayer();
}
