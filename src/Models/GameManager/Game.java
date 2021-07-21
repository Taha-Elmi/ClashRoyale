package Models.GameManager;

import Controllers.GameCon;
import Models.Cards.CardImage;

import java.util.ArrayList;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Archer;
import Models.Cards.troops.Giant;
import Models.Cards.troops.Troop;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Towers.PrincessTower;
import Models.Towers.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.util.Duration;

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
        checkTowers(player1,player2_list);
        checkTowers(player2,player1_list);
        for (CardImage cardImage : player1_list) {
            if (cardImage.getCard() instanceof Troop)
                ((Troop) cardImage.getCard()).step();
        }
        for (CardImage cardImage : player2_list) {
            if (cardImage.getCard() instanceof Troop)
                ((Troop) cardImage.getCard()).step();
        }
    }

    private void checkAllCards(ArrayList<CardImage> playerList, ArrayList<CardImage> enemyList) {
        for (CardImage cardImage : playerList) {
            Point2D src = new Point2D(GameCon.getInstance().find(cardImage.getImage()).getX(),
                    GameCon.getInstance().find(cardImage.getImage()).getY());
            CardImage target = null;
            double distance = 0;
            for (CardImage enemy : enemyList) {
                Point2D dst = new Point2D(GameCon.getInstance().find(enemy.getImage()).getX(),
                        GameCon.getInstance().find(enemy.getImage()).getY());
                if (target == null) {
                    target = enemy;
                    distance = src.distance(dst);
                } else if (src.distance(dst) < distance) {
                    target = enemy;
                    distance = src.distance(dst);
                }
            }

            if (target == null || distance > 100)
                continue;

            cardImage.getCard().getTimeline().stop();
            Timeline timeline = new Timeline();
            ((Troop) cardImage.getCard()).readyForMove(GameCon.getInstance().find(cardImage.getImage()),
                    new Point2D(GameCon.getInstance().find(target.getImage()).getX(), GameCon.getInstance().find(target.getImage()).getY()),
                    timeline);
            timeline.play();
        }
    }

    private void checkTowers(Player player,ArrayList<CardImage> enemyList) {
        final int TOWERS_NUM = 3;
        Tower tower;
        Point2D src;
        Point2D dst;
        for (int i = 0; i < TOWERS_NUM; i++) {
            if (i == 0) {
                tower = player.getKingTower();
            } else if (i == 1) {
                tower = player.getPrincessTowers().get(0);
            } else if (i == 2) {
                tower = player.getPrincessTowers().get(1);
            } else {
                tower = null;
            }
            src = new Point2D(tower.getImageView().getX(),tower.getImageView().getY());
            CardImage target = null;
            double distance = 0;
            for (CardImage enemy : enemyList) {
                dst = new Point2D(GameCon.getInstance().find(enemy.getImage()).getX(),
                        GameCon.getInstance().find(enemy.getImage()).getY());
                if (target == null) {
                    target = enemy;
                    distance = src.distance(dst);
                } else if (src.distance(dst) < distance) {
                    target = enemy;
                    distance = src.distance(dst);
                }
            }
            if (target == null || distance > tower.getRange())
                continue;
            ImageView arrowImageView = new ImageView(FXManager.getImage("/Game/arrow.jpg"));
            arrowImageView.setX(tower.getImageView().getX());
            arrowImageView.setY(tower.getImageView().getY());
            arrowImageView.setFitWidth(5);
            arrowImageView.setFitHeight(20);
            GameCon.getInstance().getBoardPane().getChildren().add(arrowImageView);
            ImageView targetImageView = GameCon.getInstance().find(target.getImage());
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.seconds(1),
                    new KeyValue(arrowImageView.xProperty(),targetImageView.getX()),
                    new KeyValue(arrowImageView.yProperty(),targetImageView.getY())
            ));
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GameCon.getInstance().getBoardPane().getChildren().remove(arrowImageView);
                }
            });
            timeline.play();
            Damageable damageable = (Damageable) target.getCard();
            tower.hit(damageable);
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public static Game getInstance() {
        return instance;
    }

    public void bornCard(Card card, Point2D src, Point2D dst, Pane boardPane,int playerNumber) {
        if (card instanceof Troop) {
            final int SPACE = 20;
            final int WIDTH = 30;
            final int HEIGHT = 50;
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
                } else if (i == 2) {
                    x -= SPACE;
                    y -= SPACE;
                } else if (i == 3) {
                    x -= SPACE;
                    y += SPACE;
                } else if (i == 4) {
                    x += SPACE;
                    y -= SPACE;
                } else if (i == 0) {
                    //nothing
                } else {
                    Config.unknownInputException();
                }
                imageView.setX(x);
                imageView.setY(y);
                boardPane.getChildren().add(imageView);
                CardImage cardImage = null;
                try {
                    cardImage = new CardImage((Card) card.clone(), imageView.getImage());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                Timeline timeline = new Timeline();
                switch (playerNumber) {
                    case 1 -> player1_list.add(cardImage);
                    case 2 -> player2_list.add(cardImage);
                    default -> throw new IllegalArgumentException();
                }
                Troop troop = (Troop) cardImage.getCard();
//                troop.readyForMove(imageView, new Point2D(dst.getX(), dst.getY()), timeline);
//                timeline.play();
            }
        } else if (card instanceof Spell) {
            ImageView imageView = new ImageView(card.born(1));
            CardImage cardImage = null;
            try {
                cardImage = new CardImage((Card) card.clone(),imageView.getImage());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            Timeline timeline = new Timeline();
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            imageView.setX(src.getX());
            imageView.setY(src.getY());
            boardPane.getChildren().add(imageView);
            switch (playerNumber) {
                case 1 -> player1_list.add(cardImage);
                case 2 -> player2_list.add(cardImage);
                default -> throw new IllegalArgumentException();
            }
            Spell spell = (Spell) cardImage.getCard();
            spell.readyForThrow(imageView,dst,timeline,playerNumber);
            timeline.play();
        }
    }

    public void checkSpell(Spell spell,Point2D src,int playerNum) {
        ArrayList<CardImage> enemyList = null;
        ArrayList<Tower> enemyTowers = new ArrayList<>();
        if (playerNum == 1) {
            enemyList = player2_list;
            enemyTowers.add(player2.getKingTower());
            enemyTowers.add(player2.getPrincessTowers().get(0));
            enemyTowers.add(player2.getPrincessTowers().get(1));
        }
        else if (playerNum == 2) {
            enemyList = player1_list;
            enemyTowers.add(player1.getKingTower());
            enemyTowers.add(player1.getPrincessTowers().get(0));
            enemyTowers.add(player1.getPrincessTowers().get(1));
        }

        Point2D dst;
        CardImage[] targets = new CardImage[enemyList.size()];
        int index = 0;
        double distance = 0;
        for (CardImage enemy : enemyList) {
            dst = new Point2D(GameCon.getInstance().find(enemy.getImage()).getX(),
                    GameCon.getInstance().find(enemy.getImage()).getY());
            distance = src.distance(dst);

            if (distance > spell.getRadius() * 25) {
                continue;
            }
            targets[index] = enemy;
            index++;
        }
        index--;
        for (int i = 0; i < index; i++) {
            Damageable damageable = (Damageable) targets[i].getCard();
            spell.act(damageable);
        }
        index = 0;
        Tower[] towersTarget = new Tower[3];
        for (Tower tower: enemyTowers) {
            dst = new Point2D(tower.getImageView().getX(),tower.getImageView().getY());
            distance = src.distance(dst);
            if (distance > spell.getRadius() * 25) {
                continue;
            }
            towersTarget[index] = tower;
            index++;
        }
        index--;
        for (int i = 0; i <= index; i++) {
            spell.act(towersTarget[i]);
        }
    }
    public void dieCard(CardImage cardImage) {
        GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(cardImage.getImage()));
        player1_list.remove(cardImage);
        player2_list.remove(cardImage);
    }

    public void dieTower(Tower tower) {
        GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(tower.getImageView().getImage()));
        GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(tower.getOwnerImageView().getImage()));
        checkCrowns();
    }

    private void checkCrowns() {
        int counter = 0;
        if (player1.getKingTower().isDead())
            counter++;
        for (PrincessTower princessTower : player1.getPrincessTowers()) {
            if (princessTower.isDead())
                counter++;
        }
        GameCon.getInstance().setCrowns(counter, 2);

        counter = 0;
        if (player2.getKingTower().isDead())
            counter++;
        for (PrincessTower princessTower : player2.getPrincessTowers()) {
            if (princessTower.isDead())
                counter++;
        }
        GameCon.getInstance().setCrowns(counter, 1);
    }

    public CardImage cardToCardImage(Card card) {
        for (CardImage cardImage: player1_list) {
            if (cardImage.getCard().equals(card)) {
                return cardImage;
            }
        }
        for (CardImage cardImage: player2_list) {
            if (cardImage.getCard().equals(card)) {
                return cardImage;
            }
        }
        return null;
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
