package Models.GameManager;

public class HumanManager implements Manager ,Runnable{
    private String name;
    private int level;
    private Player player;

    public HumanManager(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    @Override
    public void waitForAction() {

    }

    @Override
    public void action() {

    }

    @Override
    public void run() {

    }

    @Override
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
