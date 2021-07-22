package Controllers;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.buildings.Building;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Troop;
import Models.GameManager.Game;
import Models.GameManager.GameMode;
import Models.GameManager.HumanManager;
import Models.GameManager.Player;
import Models.Graphic.FXManager;
import Models.Towers.Tower;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameCon implements Controller {
    private static GameCon instance;
    private Card chosenCard;
    @FXML
    private GridPane deck;
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private Label opponentName;

    @FXML
    private ImageView leftBridge;

    @FXML
    private ImageView rightBridge;

    @FXML
    private ImageView blueKingTower;

    @FXML
    private ImageView redKingTower;

    @FXML
    private ImageView blueLeftPrincessTower;

    @FXML
    private ImageView redLeftPrincessTower;

    @FXML
    private ImageView redRightPrincessTower;

    @FXML
    private ImageView blueRightPrincessTower;

    @FXML
    private ImageView redKingImageView;

    @FXML
    private ImageView blueKingImageView;

    @FXML
    private ImageView leftRedPrincessImageView;

    @FXML
    private ImageView rightRedPrincessImageView;

    @FXML
    private ImageView leftBluePrincessImageView;

    @FXML
    private ImageView rightBluePrincessImageView;

    @FXML
    private Label opponentLevel;

    @FXML
    private Label opponentHp;

    @FXML
    private Label opponentCrowns;

    @FXML
    private Label name;

    @FXML
    private Label level;

    @FXML
    private Label hp;

    @FXML
    private Label crowns;

    @FXML
    private HBox mainBorder;

    @FXML
    private ImageView next;

    @FXML
    private Pane boardPane;

    @FXML
    private Label timerLabel;

    @FXML
    private ProgressBar elixirBar;

    @FXML
    private ImageView leftKingImageView;

    @FXML
    private ImageView rightKingImageView;

    @FXML
    private Button backButton;

    private static Timer timer;

    private static Timer mainLoop;

    private LocalTime localTime;

    @FXML
    public void backToMainMenuButton() {
        Config.primaryStage.setFullScreen(false);
        FXManager.openWindow("backToMainMenu.fxml");
    }
    @FXML
    public void initialize() {
        instance = this;
        FXManager.setBackground(FXManager.getImage("/Game/jungle.jpg"), mainBorder);
        leftKingImageView.setImage(FXManager.getImage("/Game/leftKing.png"));
        rightKingImageView.setImage(FXManager.getImage("/Game/rightKing.png"));
        setCardsImages();
        updateCardsActiveness();

        name.setText(Config.client.getName());
        level.setText("lvl: " + Config.client.getLevel());
        Game.getInstance().updateHps();
//        hp.setText("HP: MAX");
        setPlayerCardsLevel(Game.getInstance().getPlayer1(), Config.client.getLevel());

        opponentName.setText(Game.getInstance().getManager().getName());
        opponentHp.setText("HP: MAX");
        if (Game.getInstance().getGameMode() == GameMode.SINGLE) {
            opponentLevel.setText("lvl: " + Config.client.getLevel());
//            opponentHp.setText("HP: MAX");
            setPlayerCardsLevel(Game.getInstance().getPlayer2(), Config.client.getLevel());
        } else if (Game.getInstance().getGameMode() == GameMode.MULTI) {
            opponentLevel.setText("lvl: " + ((HumanManager)Game.getInstance().getManager()).getLevel());
            setPlayerCardsLevel(Game.getInstance().getPlayer2(), ((HumanManager)Game.getInstance().getManager()).getLevel());
        }

        Platform.runLater(() -> {
            FXManager.setStageReadyForGame(Config.primaryStage);
        });
        Game.getInstance().getPlayer1().getKingTower().setImageView(blueKingTower);
        Game.getInstance().getPlayer1().getKingTower().setOwnerImageView(blueKingImageView);
        Game.getInstance().getPlayer1().getPrincessTowers().get(0).setImageView(blueLeftPrincessTower);
        Game.getInstance().getPlayer1().getPrincessTowers().get(0).setOwnerImageView(leftBluePrincessImageView);
        Game.getInstance().getPlayer1().getPrincessTowers().get(1).setImageView(blueRightPrincessTower);
        Game.getInstance().getPlayer1().getPrincessTowers().get(1).setOwnerImageView(rightBluePrincessImageView);
        Game.getInstance().getPlayer2().getKingTower().setImageView(redKingTower);
        Game.getInstance().getPlayer2().getKingTower().setOwnerImageView(redKingImageView);
        Game.getInstance().getPlayer2().getPrincessTowers().get(0).setImageView(redLeftPrincessTower);
        Game.getInstance().getPlayer2().getPrincessTowers().get(0).setOwnerImageView(leftRedPrincessImageView);
        Game.getInstance().getPlayer2().getPrincessTowers().get(1).setImageView(redRightPrincessTower);
        Game.getInstance().getPlayer2().getPrincessTowers().get(1).setOwnerImageView(rightRedPrincessImageView);
        startTimer();
        startMainLoop();
        Config.playMusic("assets/musics/BattleTheme.mp3");
    }

    private void startMainLoop() {
        mainLoop = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Game.getInstance().update();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(100.0);
        mainLoop.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    private void startTimer() {
        timer = new Timer();
        localTime = LocalTime.of(0, 3, 0);
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timerAdvance();
                        elixirAdvance();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0);
        timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    private void timerAdvance() {
        localTime = localTime.minusSeconds(1);
        timerLabel.setText(localTime.format(DateTimeFormatter.ofPattern("mm:ss")));
        if (localTime.isBefore(LocalTime.of(0, 0, 1))) {
            Game.getInstance().finish();
            System.out.println("FINISHED!");
        }
    }

    private void elixirAdvance() {
        if (Game.getInstance().getPlayer1().getElixirs() < 10)
            Game.getInstance().getPlayer1().setElixirs(Game.getInstance().getPlayer1().getElixirs() + 1);
        if (Game.getInstance().getPlayer2().getElixirs() < 10)
            Game.getInstance().getPlayer2().setElixirs(Game.getInstance().getPlayer2().getElixirs() + 1);
        elixirBar.setProgress((double) Game.getInstance().getPlayer1().getElixirs() / 10);
        updateCardsActiveness();
    }

    private void setPlayerCardsLevel(Player player, int level) {
        for (Card card: Game.getInstance().getPlayer1().getCards()) {
            card.setLevel(level);
        }
        for (Card card: player.getCards()) {
            card.setLevel(level);
        }
    }

    private void setCardsImages() {
        card1.setImage(CardImage.find(Config.client.getDeckCards().get(0)).getImage());
        card2.setImage(CardImage.find(Config.client.getDeckCards().get(1)).getImage());
        card3.setImage(CardImage.find(Config.client.getDeckCards().get(2)).getImage());
        card4.setImage(CardImage.find(Config.client.getDeckCards().get(3)).getImage());
        next.setImage(CardImage.find(Config.client.getDeckCards().get(4)).getImage());
    }

    private void updateCardsActiveness() {
        if (Config.client.getDeckCards().get(0).getCost() > Game.getInstance().getPlayer1().getElixirs()) {
            card1.setDisable(true);
            card1.setOpacity(0.5);
        } else {
            card1.setDisable(false);
            card1.setOpacity(1);
        }

        if (Config.client.getDeckCards().get(1).getCost() > Game.getInstance().getPlayer1().getElixirs()) {
            card2.setDisable(true);
            card2.setOpacity(0.5);
        } else {
            card2.setDisable(false);
            card2.setOpacity(1);
        }

        if (Config.client.getDeckCards().get(2).getCost() > Game.getInstance().getPlayer1().getElixirs()) {
            card3.setDisable(true);
            card3.setOpacity(0.5);
        } else {
            card3.setDisable(false);
            card3.setOpacity(1);
        }

        if (Config.client.getDeckCards().get(3).getCost() > Game.getInstance().getPlayer1().getElixirs()) {
            card4.setDisable(true);
            card4.setOpacity(0.5);
        } else {
            card4.setDisable(false);
            card4.setOpacity(1);
        }
    }

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {

    }

    @FXML
    private void dragHandler(DragEvent de) {

    }

    @FXML
    private void dragDetectionHandler(MouseEvent me) {
        if (!(me.getSource() instanceof ImageView)) {
            return;
        }
        Dragboard db = ((ImageView) me.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        chosenCard = CardImage.find(((ImageView) me.getSource()).getImage()).getCard();
        cb.putImage(((ImageView) me.getSource()).getImage());
        db.setContent(cb);
        me.consume();
    }

    @FXML
    private void dragOverHandler(DragEvent de) {
        if (de.getDragboard().hasImage() && isValidToDrop(new Point2D(de.getX(),de.getY()),1,chosenCard)) {
            de.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void dragDroppedHandler(DragEvent de) {
        try {
            Game.getInstance().getPlayer1().setElixirs(Game.getInstance().getPlayer1().getElixirs() - chosenCard.getCost());
            elixirBar.setProgress((double) Game.getInstance().getPlayer1().getElixirs() / 10);
            updateCardsActiveness();
            if (chosenCard instanceof Troop) {
                Point2D src = new Point2D(de.getX(), de.getY());
                ImageView nearerTower = getNearerTowerImageView(src, 1);
                Game.getInstance().bornCard(chosenCard, src
                        , new Point2D(nearerTower.getX(), nearerTower.getY()), boardPane, 1);
            } else if (chosenCard instanceof Spell) {
                Point2D src = new Point2D(blueKingTower.getX(),blueKingTower.getY());
                Game.getInstance().bornCard(chosenCard,src,new Point2D(de.getX()-13,de.getY()-13),boardPane,1);
            } else if (chosenCard instanceof Building) {
                Point2D src = new Point2D(de.getX() - 13, de.getY() - 13);
                Game.getInstance().bornCard(chosenCard,src,new Point2D(de.getX()-13,de.getY()-13),boardPane,1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        Game.getInstance().playCardPlayer1(chosenCard);
        setCardsImages();
        chosenCard = null;
    }

    public ImageView getNearerTowerImageView(Point2D src, int player) {
        if (player == 1) {
            double redKingTowerDistance = src.distance(new Point2D(redKingTower.getX(), redKingTower.getY()));
            double redLeftPrincessTowerDistance = src.distance(new Point2D(redLeftPrincessTower.getX(), redLeftPrincessTower.getY()));
            double redRightPrincessTowerDistance = src.distance(new Point2D(redRightPrincessTower.getX(), redRightPrincessTower.getY()));

            if (!boardPane.getChildren().contains(redKingTower)) {
                redKingTowerDistance = 999999999;
            }
            if (!boardPane.getChildren().contains(redLeftPrincessTower)) {
                redLeftPrincessTowerDistance = 999999999;
            }
            if (!boardPane.getChildren().contains(redRightPrincessTower)) {
                redRightPrincessTowerDistance = 999999999;
            }

            double min = redKingTowerDistance;

            if (min >= redLeftPrincessTowerDistance)
                min = redLeftPrincessTowerDistance;
            if (min >= redRightPrincessTowerDistance)
                min = redRightPrincessTowerDistance;

            if (min == redKingTowerDistance) {
                return redKingTower;
            } else if (min == redLeftPrincessTowerDistance) {
                return redLeftPrincessTower;
            } else if (min == redRightPrincessTowerDistance) {
                return redRightPrincessTower;
            } else {
                return null;
            }
        } else if (player == 2) {
            double blueKingTowerDistance = src.distance(new Point2D(blueKingTower.getX(), blueKingTower.getY()));
            double blueLeftPrincessTowerDistance = src.distance(new Point2D(blueLeftPrincessTower.getX(), blueLeftPrincessTower.getY()));
            double blueRightPrincessTowerDistance = src.distance(new Point2D(blueRightPrincessTower.getX(), blueRightPrincessTower.getY()));

            if (!boardPane.getChildren().contains(blueKingTower)) {
                blueKingTowerDistance = 999999999;
            }
            if (!boardPane.getChildren().contains(blueLeftPrincessTower)) {
                blueLeftPrincessTowerDistance = 999999999;
            }
            if (!boardPane.getChildren().contains(blueRightPrincessTower)) {
                blueRightPrincessTowerDistance = 999999999;
            }
            double min = blueKingTowerDistance;
            if (min >= blueLeftPrincessTowerDistance)
                min = blueLeftPrincessTowerDistance;
            if (min >= blueRightPrincessTowerDistance)
                min = blueRightPrincessTowerDistance;

            if (min == blueKingTowerDistance) {
                return blueKingTower;
            } else if (min == blueLeftPrincessTowerDistance) {
                return blueLeftPrincessTower;
            } else if (min == blueRightPrincessTowerDistance) {
                return blueRightPrincessTower;
            } else {
                return null;
            }
        } else {
            Config.unknownInputException();
        }
        return null;
    }

    public ImageView getRandomTower() {
        Random random = new Random();
        while (true) {
            int randInt = random.nextInt(3);
            if (randInt == 0 && boardPane.getChildren().contains(blueKingTower)) {
                return blueKingTower;
            } else if (randInt == 1 && boardPane.getChildren().contains(blueLeftPrincessTower)) {
                return blueLeftPrincessTower;
            } else if (randInt == 2 && boardPane.getChildren().contains(blueRightPrincessTower)) {
                return blueRightPrincessTower;
            }
        }
    }

    public ImageView getNearerBridge(Point2D src) {
        double leftBridgeDistance = src.distance(new Point2D(leftBridge.getLayoutX(),leftBridge.getLayoutY()));
        double rightBridgeDistance = src.distance(new Point2D(rightBridge.getLayoutX(),rightBridge.getLayoutY()));

        double min = Math.min(leftBridgeDistance, rightBridgeDistance);

        if (min == leftBridgeDistance)
            return leftBridge;
        else if (min == rightBridgeDistance)
            return rightBridge;
        else
            return null;
    }

    public boolean isValidToDrop(Point2D point2D,int playerNum,Card card) {
        boolean leftPrincessTowerIsDead;
        boolean rightPrincessTowerIsDead;
        if (playerNum == 1) {
            leftPrincessTowerIsDead = Game.getInstance().getPlayer2().getPrincessTowers().get(0).isDead();
            rightPrincessTowerIsDead = Game.getInstance().getPlayer2().getPrincessTowers().get(1).isDead();
        } else {
            leftPrincessTowerIsDead = Game.getInstance().getPlayer1().getPrincessTowers().get(0).isDead();
            rightPrincessTowerIsDead = Game.getInstance().getPlayer1().getPrincessTowers().get(1).isDead();
        }
        if (card instanceof Spell) {
            return true;
        }
        double x = point2D.getX();
        double y = point2D.getY();
        if (playerNum == 1) {
            if (y > 345)
                return true;
            else {
                boolean[] returnValues = new boolean[2];
                returnValues[0] = false;
                returnValues[1] = false;
                if (leftPrincessTowerIsDead) {
                    if (y > 227 && x < 203) {
                        returnValues[0] = true;
                    }
                }
                if (rightPrincessTowerIsDead) {
                    if (y > 227 && x > 253) {
                        returnValues[1] = true;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    if (returnValues[i] == true) {
                        return true;
                    }
                }
                return false;
            }
        } else if (playerNum == 2) {
            if (y < 345)
                return true;
            else
                return false;
        } else {
            Config.unknownInputException();
        }
        return false;
    }

    public void setCrowns(int n, int playerNumber) {
        switch (playerNumber) {
            case 1 -> crowns.setText(" " + n);
            case 2 -> opponentCrowns.setText(" " + n);
        }
    }

    public void setHp(int n, int playerNumber) {
        switch (playerNumber) {
            case 1 -> hp.setText("HP: " + n);
            case 2 -> opponentHp.setText("HP: " + n);
        }
    }

    public ImageView getRedKingTower() {
        return redKingTower;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static Timer getMainLoop() {
        return mainLoop;
    }

    public Pane getBoardPane() {
        return boardPane;
    }

    public ImageView find(Image image) {
        for (Node node : boardPane.getChildren()) {
            //node instanceof ImageView && ((ImageView) node).getImage().equals(cardImage.getImage()
            if (node instanceof ImageView && ((ImageView) node).getImage() != null && ((ImageView) node).getImage().equals(image))
                return (ImageView) node;
        }
        return null;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public Label getOpponentName() {
        return opponentName;
    }

    public Label getOpponentCrowns() {
        return opponentCrowns;
    }

    public Label getOpponentHp() {
        return opponentHp;
    }

    public Label getOpponentLevel() {
        return opponentLevel;
    }

    public Label getName() {
        return name;
    }

    public Label getCrowns() {
        return crowns;
    }

    public Label getHp() {
        return hp;
    }

    public Label getLevel() {
        return level;
    }

    public static GameCon getInstance() {
        return instance;
    }

}
