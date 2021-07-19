package Models.GameManager;

import Controllers.GameCon;
import Models.Cards.CardImage;

import java.util.ArrayList;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.troops.Archer;
import Models.Cards.troops.Giant;
import Models.Cards.troops.Troop;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

import Models.Cards.CardImage;

import java.util.ArrayList;

public class Game {
    private static Game instance;
    private Player player1;
    private ArrayList<CardImage> player1_list;
    private Player player2;
    private ArrayList<CardImage> player2_list;
    private Manager manager;
    private GameMode gameMode;
    public Game(Player player1, Player player2, GameMode gameMode,Manager manager) {
        this.player1 = player1;
        this.player2 = player2;
        player1_list = new ArrayList<>();
        player2_list = new ArrayList<>();
        this.gameMode = gameMode;
        this.manager = manager;
        instance = this;
    }

    public void initialize() {

    }

    public void finish() {
        GameCon.getTimer().cancel();
        GameCon.getMainLoop().cancel();
        // display scoreboard...
    }

    public void update() {
        manager.action();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public static Game getInstance() {
        return instance;
    }

    public void bornCard(Card card, Point2D src, Point2D dst, Pane boardPane,int playerNumber) {
        final int SPACE = 20;
        final int WIDTH = 30;
        final int HEIGHT = 50;
        ParallelTransition pt = new ParallelTransition();
        for (int i = 0; i < card.getNumber(); i++) {
            ImageView imageView = new ImageView(card.born(playerNumber));
            if (card instanceof Archer) {
                imageView.setFitWidth(WIDTH - 7);
                imageView.setFitHeight(HEIGHT - 7);
            } else if (card instanceof Giant) {
                imageView.setFitWidth(WIDTH + 10);
                imageView.setFitHeight(HEIGHT + 10);
            } else {
                imageView.setFitWidth(WIDTH);
                imageView.setFitHeight(HEIGHT);
            }
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
                    CardImage cardImage = new CardImage((Card) card.clone(), imageView.getImage());
                    switch (playerNumber) {
                        case 1 -> player1_list.add(cardImage);
                        case 2 -> player2_list.add(cardImage);
                        default -> throw new IllegalArgumentException();
                    }
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

    public void dieCard(CardImage cardImage) {
        GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(cardImage.getImage()));
        player1_list.remove(cardImage);
        player2_list.remove(cardImage);
    }

    public void playCardPlayer1(Card card) {
        player1.playCard(card);
    }
    public void playCardPlayer2(Card card) {
        player2.playCard(card);
    }

    public boolean isGameOver() {
        return false;
    }

    public Manager getManager() {
        return manager;
    }

    public ArrayList<CardImage> getPlayer1_list() {
        return player1_list;
    }

    public ArrayList<CardImage> getPlayer2_list() {
        return player2_list;
    }
}
