package Controllers;

import Database.BattleHistory;
import Database.SQLManager;
import Main.Config;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

    private BattleHistory.Result result;

    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/Game/jungle.jpg"), mainBorder);
        leftKingImageView.setImage(FXManager.getImage("/Game/leftKing.png"));
        rightKingImageView.setImage(FXManager.getImage("/Game/rightKing.png"));
        if (Game.getInstance().getPlayer1().getCrown() > Game.getInstance().getPlayer2().getCrown()
                || (Game.getInstance().getPlayer1().getCrown() == Game.getInstance().getPlayer2().getCrown()
                && Game.getInstance().getPlayer1().getHp() > Game.getInstance().getPlayer2().getHp())) {
            mainLabel.setStyle("-fx-text-fill: blue");
            mainLabel.setText(Config.client.getName() + " Won!");
            happyKing.setImage(FXManager.getImage("/Game/happyBlueKing.png"));
            result = BattleHistory.Result.WIN;
        } else {
            mainLabel.setStyle("-fx-text-fill: red");
            String botName = Game.getInstance().getManager().getClass().getSimpleName();
            mainLabel.setText(botName.substring(0, botName.length() - 7) + " Won!");
            happyKing.setImage(FXManager.getImage("/Game/happyRedKing.png"));
            result = BattleHistory.Result.LOOSE;
        }
    }

    @FXML
    void backOnAction(ActionEvent event) {
        SQLManager.addHistory(Config.client.getName(), "StupidRobot",
                Game.getInstance().getPlayer1().getCrown(), Game.getInstance().getPlayer2().getCrown(), result);
        Config.mediaPlayer.stop();
        Config.playMusic("assets/musics/MainTheme.mp3");
        FXManager.goTo("MainMenu.fxml", Config.primaryStage);
    }
}
