package Models.GameManager;

import Controllers.GameCon;
import Database.BattleHistory;
import Database.SQLManager;
import Models.Cards.CardImage;


import java.util.ArrayList;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.buildings.Building;
import Models.Cards.spells.Rage;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Archer;
import Models.Cards.troops.Giant;
import Models.Cards.troops.Troop;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Towers.KingTower;
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
    private BattleHistory.Result result;
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

        if (player1.getCrown() > Game.getInstance().getPlayer2().getCrown()
                || (player1.getCrown() == player2.getCrown() && player1.getHp() > player2.getHp())) {
            result = BattleHistory.Result.WIN;
            Config.client.setXp(Config.client.getXp() + 200);
        } else {
            result = BattleHistory.Result.LOOSE;
            Config.client.setXp(Config.client.getXp() + 70);
        }

        if (Config.client.getXp() < 300)
            Config.client.setLevel(1);
        else if (Config.client.getXp() < 500)
            Config.client.setLevel(2);
        else if (Config.client.getXp() < 900)
            Config.client.setLevel(3);
        else if (Config.client.getXp() < 1700)
            Config.client.setLevel(4);
        else
            Config.client.setLevel(5);


        SQLManager.updateClient(Config.client.getXp(), Config.client.getLevel());
        SQLManager.addHistory(Config.client.getName(), manager.getName(),
                Game.getInstance().getPlayer1().getCrown(), Game.getInstance().getPlayer2().getCrown(), result);
        FXManager.goTo("scoreboard.fxml", Config.primaryStage);
    }

    public void update() {
        manager.action();
        checkTowers(player1,player2_list,1);
        checkTowers(player2,player1_list,2);
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

    private void checkTowers(Player player,ArrayList<CardImage> enemyList,int playerNum) {
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
            if (tower.isDead()) {
                continue;
            }

            //here we handle the hitSpeed
            if (tower.getCounter() < tower.getHitSpeed()) {
                tower.counterIncrease();
                continue;
            } else
                tower.setCounter(0);

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
            if (target == null || target.getCard() instanceof Spell || distance > tower.getRange() * 25)
                continue;
            ImageView arrowImageView;

            if (playerNum == 1) {
                arrowImageView = new ImageView(FXManager.getImage("/Game/forward_arrow.jpg"));
            } else {
                arrowImageView = new ImageView(FXManager.getImage("/Game/backward_arrow.jpg"));
            }
            arrowImageView.setX(tower.getImageView().getX() + 35);
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
            Tower finalTower = tower;
            CardImage finalTarget = target;
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GameCon.getInstance().getBoardPane().getChildren().remove(arrowImageView);
                    Damageable damageable = (Damageable) finalTarget.getCard();
                    finalTower.hit(damageable);
                }
            });
            timeline.play();
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

    public BattleHistory.Result getResult() {
        return result;
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
                    cardImage = new CardImage((Card) card.clone(), imageView.getImage(), playerNumber);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                Timeline timeline = new Timeline();
                switch (playerNumber) {
                    case 1 -> player1_list.add(cardImage);
                    case 2 -> player2_list.add(cardImage);
                    default -> throw new IllegalArgumentException();
                }
            }
        } else if (card instanceof Spell) {
            ImageView imageView = new ImageView(card.born(playerNumber));
            CardImage cardImage = null;
            try {
                cardImage = new CardImage((Card) card.clone(), imageView.getImage());
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
        } else if (card instanceof Building) {
            ImageView imageView = new ImageView(card.born(playerNumber));
            CardImage cardImage = null;
            try {
                cardImage = new CardImage((Card) card.clone(), imageView.getImage());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            imageView.setX(dst.getX());
            imageView.setY(dst.getY());
            boardPane.getChildren().add(imageView);
            switch (playerNumber) {
                case 1 -> player1_list.add(cardImage);
                case 2 -> player2_list.add(cardImage);
                default -> throw new IllegalArgumentException();
            }
            Building building = (Building) cardImage.getCard();
            building.readyForBorn(imageView,playerNumber,dst);
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

    public void dieCard(CardImage cardImage) throws Exception {
        try {
            GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(cardImage.getImage()));
            player1_list.remove(cardImage);
            player2_list.remove(cardImage);
        }catch (NullPointerException e){}
    }

    public void dieTower(Tower tower) {
        GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(tower.getImageView().getImage()));
        GameCon.getInstance().getBoardPane().getChildren().removeIf(node -> node instanceof ImageView && ((ImageView) node).getImage().equals(tower.getOwnerImageView().getImage()));
        checkCrowns();
        if (tower instanceof KingTower)
            finish();
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
        player2.setCrown(counter);

        counter = 0;
        if (player2.getKingTower().isDead())
            counter++;
        for (PrincessTower princessTower : player2.getPrincessTowers()) {
            if (princessTower.isDead())
                counter++;
        }
        GameCon.getInstance().setCrowns(counter, 1);
        player1.setCrown(counter);
    }

    public void updateHps() {
        int sum = 0;
        sum += player1.getKingTower().getHp();
        sum += player1.getPrincessTowers().get(0).getHp();
        sum += player1.getPrincessTowers().get(1).getHp();
        GameCon.getInstance().setHp(sum, 1);
        player1.setHp(sum);

        sum = 0;
        sum += player2.getKingTower().getHp();
        sum += player2.getPrincessTowers().get(0).getHp();
        sum += player2.getPrincessTowers().get(1).getHp();
        GameCon.getInstance().setHp(sum, 2);
        player2.setHp(sum);
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
