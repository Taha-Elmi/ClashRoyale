package Models.GameManager;

import Controllers.GameCon;
import Models.Cards.Card;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

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
        run();
    }

    @Override
    public void run() {
        playCard();
    }

    private Card getRandomCard() {
        return player.getCards().get(random.nextInt(player.getCards().size()));
    }
    private Point2D getRandomPoint2D() {
        double randomWidth = random.nextDouble() * validRandomWidth;
        double randomHeight = random.nextDouble() * validRandomHeight;
        return new Point2D(randomWidth,randomHeight);
    }
    private void playCard() {
        Card card;
        if ((card = getRandomCard()).getCost() > Game.getInstance().getPlayer2().getElixirs())
            return;
        Game.getInstance().getPlayer2().setElixirs(Game.getInstance().getPlayer2().getElixirs() - card.getCost());
        Point2D src = getRandomPoint2D();
        ImageView nearerTower = GameCon.getInstance().getNearerTowerImageView(src,2);
        Point2D dst = new Point2D(nearerTower.getLayoutX(), nearerTower.getLayoutY());
        Game.getInstance().bornCard(card, src, dst, GameCon.getInstance().getBoardPane(), 2);
        Game.getInstance().playCardPlayer2(card);
    }
}
