package Controllers;

import Database.BattleHistory;
import Main.Config;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;

/**
 * The Scoreboard controller.
 */
public class ScoreboardCon {
    @FXML
    private HBox mainBorder;

    @FXML
    private ImageView leftKingImageView;

    @FXML
    private ImageView rightKingImageView;

    @FXML
    private ImageView happyKing;

    @FXML
    private Label mainLabel;

    @FXML
    private Label opponentCrowns;

    @FXML
    private Label opponentName;

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
    private Label crowns;

    @FXML
    private Label timerLabel;

    /**
     * Initialize.
     */
    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/Game/jungle.jpg"), mainBorder);
        leftKingImageView.setImage(FXManager.getImage("/Game/leftKing.png"));
        rightKingImageView.setImage(FXManager.getImage("/Game/rightKing.png"));
        timerLabel.setText(GameCon.getInstance().getTimerLabel().getText());
        opponentCrowns.setText(GameCon.getInstance().getOpponentCrowns().getText());
        opponentHp.setText(GameCon.getInstance().getOpponentHp().getText());
        opponentLevel.setText(GameCon.getInstance().getOpponentLevel().getText());
        opponentName.setText(GameCon.getInstance().getOpponentName().getText());
        crowns.setText(GameCon.getInstance().getCrowns().getText());
        hp.setText(GameCon.getInstance().getHp().getText());
        level.setText(GameCon.getInstance().getLevel().getText());
        name.setText(GameCon.getInstance().getName().getText());
        if (Game.getInstance().getResult() == BattleHistory.Result.WIN) {
            mainLabel.setStyle("-fx-text-fill: blue;\n" + "-fx-font-size: 50px;");
            mainLabel.setText(Config.client.getName() + "\nWon!");
            happyKing.setImage(FXManager.getImage("/Game/happyBlueKing.png"));
        } else {
            mainLabel.setStyle("-fx-text-fill: red;\n" + "-fx-font-size: 50px;");
            mainLabel.setText(Game.getInstance().getManager().getName() + "\nWon!");
            happyKing.setImage(FXManager.getImage("/Game/happyRedKing.png"));
        }
    }

    /**
     * action handler of the back button
     * @param event the event
     */
    @FXML
    void backOnAction(ActionEvent event) {
        Config.mediaPlayer.stop();
        Config.playMusic("assets/musics/MainTheme.mp3");
        FXManager.setStageNormal(Config.primaryStage);
        FXManager.goTo("MainMenu.fxml", Config.primaryStage);
    }

    /**
     * event handler
     * @param event the event
     */
    @FXML
    void dragDroppedHandler(DragEvent event) {

    }

    /**
     * event handler
     * @param event the event
     */
    @FXML
    void dragOverHandler(DragEvent event) {

    }
}
