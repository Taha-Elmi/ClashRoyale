package Models.GameManager;

import Controllers.GameCon;
import Models.Cards.Card;
import javafx.geometry.Point2D;
import java.util.Random;

public class StupidRobotManager implements Manager,Runnable {
    private Player player;
    private final double validRandomWidth = 320;
    private final double validRandomHeight = 520;
    Random random = new Random();
    public StupidRobotManager(Player player) {
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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            Card card = getRandomCard();
            Game.getInstance().bornCard(card, getRandomPoint2D(), new Point2D(300, 50), GameCon.getStaticBoardPane(), 2);
            Game.getInstance().playCardPlayer2(card);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Card getRandomCard() {
        return player.getCards().get(random.nextInt(player.getCards().size()));
    }
    private Point2D getRandomPoint2D() {
        double randomWidth = random.nextDouble() * validRandomWidth;
        double randomHeight = random.nextDouble() * validRandomHeight;
        return new Point2D(randomWidth,randomHeight);
    }
}
