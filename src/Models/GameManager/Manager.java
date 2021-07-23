package Models.GameManager;

/**
 * Manager Interface to be used in the game class
 */
public interface Manager {
    /**
     * what should be done in the game
     */
    void action();

    /**
     * getter of the name field
     * @return name
     */
    String getName();

    /**
     * getter of the player field
     * @return player
     */
    Player getPlayer();
}
