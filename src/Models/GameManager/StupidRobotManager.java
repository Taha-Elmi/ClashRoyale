package Models.GameManager;

import Controllers.GameCon;
import Models.Cards.Card;
import Models.Cards.buildings.Building;
import Models.Cards.spells.Rage;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Troop;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.Random;

public class StupidRobotManager implements Manager,Runnable {
    private Player player;
    private String name;
    private final double validRandomWidth = 300;
    private final double validRandomHeight = 280;
    Random random = new Random();

    public StupidRobotManager(String name, Player player) {
        this.name = name;
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
        playCard(getRandomCard());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    private Card getRandomCard() {
        return player.getCards().get(random.nextInt(4));
    }
    private Point2D getRandomPoint2D() {
        double randomWidth = random.nextDouble() * validRandomWidth;
        double randomHeight = random.nextDouble() * validRandomHeight;
        return new Point2D(randomWidth,randomHeight);
    }
    private void playCard(Card card) {
        if (card.getCost() > Game.getInstance().getPlayer2().getElixirs() || card instanceof Rage)
            return;
        Game.getInstance().getPlayer2().setElixirs(Game.getInstance().getPlayer2().getElixirs() - card.getCost());
        Point2D src = null;
        ImageView targetTower = null;
        if (card instanceof Troop) {
            src = getRandomPoint2D();
            targetTower = GameCon.getInstance().getNearerTowerImageView(src,2);
        } else if (card instanceof Spell) {
            src = new Point2D(GameCon.getInstance().getRedKingTower().getX(),GameCon.getInstance().getRedKingTower().getY());
            targetTower = GameCon.getInstance().getRandomTower();
        } else if (card instanceof Building) {
            return;
//            targetTower = GameCon.getInstance().getNearerTowerImageView(src,2);
        }
        Point2D dst = new Point2D(targetTower.getX(), targetTower.getY());
        Game.getInstance().bornCard(card, src, dst, GameCon.getInstance().getBoardPane(), 2);
        Game.getInstance().playCardPlayer2(card);
    }
}
