package Controllers;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.troops.Troop;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class GameCon implements Controller {
    private static Pane staticBoardPane;
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
        staticBoardPane = boardPane;
        startTimer();
        startMainLoop();
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
            Game.getInstance().getPlayer1().setElixirs(Game.getInstance().getPlayer1().getElixirs() + 1);
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
        if (de.getDragboard().hasImage()) {
            de.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void dragDroppedHandler(DragEvent de) {
        Game.getInstance().getPlayer1().setElixirs(Game.getInstance().getPlayer1().getElixirs() - chosenCard.getCost());
        elixirBar.setProgress((double) Game.getInstance().getPlayer1().getElixirs() / 10);
        updateCardsActiveness();

        try {
            Game.getInstance().bornCard(chosenCard, new Point2D(de.getX(), de.getY())
                    , new Point2D(redRightPrincessTower.getLayoutX(), redRightPrincessTower.getLayoutY()), boardPane, 1);
            Game.getInstance().playCardPlayer1(chosenCard);
            setCardsImages();
            chosenCard = null;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Timer getTimer() {
        return timer;
    }

    public static Timer getMainLoop() {
        return mainLoop;
    }

    public static Pane getStaticBoardPane() {
        return staticBoardPane;
    }
}
