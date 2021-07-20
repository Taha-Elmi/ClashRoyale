package Controllers;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Troop;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import Models.Towers.Tower;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private Label opponentLevel;

    @FXML
    private Label opponentHp;

    @FXML
    private Label name;

    @FXML
    private Label level;

    @FXML
    private Label hp;

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

    private static Timer timer;

    private static Timer mainLoop;

    private LocalTime localTime;

    @FXML
    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/Game/jungle.jpg"), mainBorder);
        leftKingImageView.setImage(FXManager.getImage("/Game/leftKing.png"));
        rightKingImageView.setImage(FXManager.getImage("/Game/rightKing.png"));
        setCardsImages();
        updateCardsActiveness();
        name.setText(Config.client.getName());
        level.setText("" + Config.client.getLevel());
        hp.setText("MAX");
        Platform.runLater(() -> {
            FXManager.setStageReadyForGame(Config.primaryStage);
        });
        startTimer();
        startMainLoop();
        instance = this;
        Config.playMusic("assets/musics/BattleTheme.mp3");
    }

    private void startMainLoop() {
        mainLoop = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Game.getInstance().update();
                        if (Game.getInstance().isGameOver()) {
                            Game.getInstance().finish();
                        }
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
            timer.cancel();
            Game.getInstance().finish();
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
                        , new Point2D(nearerTower.getLayoutX(), nearerTower.getLayoutY()), boardPane, 1);
            } else if (chosenCard instanceof Spell) {
                Point2D src = new Point2D(blueKingTower.getLayoutX(),blueKingTower.getLayoutY());
                Game.getInstance().bornCard(chosenCard,src,new Point2D(de.getX()-13,de.getY()-13),boardPane,1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        Game.getInstance().playCardPlayer1(chosenCard);
        setCardsImages();
        chosenCard = null;
    }

    public ImageView getNearerTowerImageView(Point2D src,int player) {
        if (player == 1) {
            double redKingTowerDistance = src.distance(new Point2D(redKingTower.getLayoutX(), redKingTower.getLayoutY()));
            double redLeftPrincessTowerDistance = src.distance(new Point2D(redLeftPrincessTower.getLayoutX(), redLeftPrincessTower.getLayoutY()));
            double redRightPrincessTowerDistance = src.distance(new Point2D(redRightPrincessTower.getLayoutX(), redRightPrincessTower.getLayoutY()));

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
            double blueKingTowerDistance = src.distance(new Point2D(blueKingTower.getLayoutX(), blueKingTower.getLayoutY()));
            double blueLeftPrincessTowerDistance = src.distance(new Point2D(blueLeftPrincessTower.getLayoutX(), blueLeftPrincessTower.getLayoutY()));
            double blueRightPrincessTowerDistance = src.distance(new Point2D(blueRightPrincessTower.getLayoutX(), blueRightPrincessTower.getLayoutY()));

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

    public ImageView getNearerBridge(Point2D src) {
        double leftBridgeDistance = src.distance(new Point2D(leftBridge.getLayoutX(),leftBridge.getLayoutY()));
        double rightBridgeDistance = src.distance(new Point2D(rightBridge.getLayoutX(),rightBridge.getLayoutY()));

        double min = leftBridgeDistance;

        if (min >= rightBridgeDistance)
            min = rightBridgeDistance;

        if (min == leftBridgeDistance)
            return leftBridge;
        else if (min == rightBridgeDistance)
            return rightBridge;
        else
            return null;
    }
    public boolean isValidToDrop(Point2D point2D,int playerNum,Card card) {
        if (card instanceof Spell) {
            return true;
        }
        double x = point2D.getX();
        double y = point2D.getY();
        if (playerNum == 1) {
            if (y > 345)
                return true;
            else
                return false;
        } else if (playerNum == 2) {
            if (y > 345)
                return true;
            else
                return false;
        } else {
            Config.unknownInputException();
        }
        return false;
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

    public static GameCon getInstance() {
        return instance;
    }
}
