package Models.GameManager;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.troops.Troop;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

public class Game {
    private static Game instance;
    private Player player1;
    private Player player2;
    private Manager manager;
    private GameMode gameMode;
    public Game(Player player1, Player player2, GameMode gameMode,Manager manager) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameMode = gameMode;
        this.manager = manager;
        instance = this;
    }

    public void initialize() {
    }

    public void finish() {

    }

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

    public void bornCard(Card card, Point2D src, Point2D dst, Pane boardPane) {
        final int SPACE = 20;
        final int WIDTH = 30;
        final int HEIGHT = 50;
        ParallelTransition pt = new ParallelTransition();
        for (int i = 0; i < card.getNumber(); i++) {
            ImageView imageView = new ImageView(card.born());
            imageView.setFitWidth(WIDTH);
            imageView.setFitHeight(HEIGHT);
            double x = src.getX();
            double y = src.getY();
            if (i == 1) {
                x += SPACE;
                y += SPACE;
            } else if (i == 2){
                x -= SPACE;
                y -= SPACE;
            } else if (i == 3) {
                x -= SPACE;
                y += SPACE;
            } else if (i == 4) {
                x += SPACE;
                y -= SPACE;
            } else if (i == 0){
                //nothing
            } else {
                Config.unknownInputException();
            }
            imageView.setX(x);
            imageView.setY(y);
            boardPane.getChildren().add(imageView);
            if (card instanceof Troop) {
                try {
                    CardImage cardImage = new CardImage((Card) card.clone(),card.born());
                    Troop troop = (Troop) cardImage.getCard();
                    Timeline timeline = new Timeline();
                    troop.readyForMove(imageView,new Point2D(dst.getX(),dst.getY()),timeline);
                    pt.getChildren().add(timeline);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        pt.play();
    }

    public void playCardPlayer1(Card card) {
        player1.playCard(card);
    }
    public void playCardPlayer2(Card card) {
        player2.playCard(card);
    }

    public Manager getManager() {
        return manager;
    }
}
