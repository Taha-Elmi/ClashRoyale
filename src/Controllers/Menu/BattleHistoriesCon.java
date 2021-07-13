package Controllers.Menu;

import Database.BattleHistory;
import Database.SQLManager;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;

public class BattleHistoriesCon implements Controller {
    @FXML
    private Pane mainBorder;
    @FXML
    private VBox mainBox;

    public void initialize() {
        Image image = FXManager.getImage("/BackGrounds/BattleHistory.jpg");
        FXManager.setBackground(image,mainBorder);
        addAllBattleHistories();
    }
    @Override
    public void actionHandler(ActionEvent ae) throws Exception {

    }

    private void addNewBattleHistory(BattleHistory battleHistory) {
        VBox vBox = new VBox();
        vBox.prefWidth(320);
        vBox.prefHeight(84);
        vBox.setPadding(new Insets(0,10,0,10));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Label opponentName = new Label("Opponent: " + battleHistory.getOpponentName());
        Label matchDate = new Label("Date: " + simpleDateFormat.format(battleHistory.getDate()));
        Label matchRes = new Label("Result: " + battleHistory.getResult() + "!" + "\t" +
                battleHistory.getWonCrown() + ":" + battleHistory.getLostCrown());
        vBox.getChildren().add(matchDate);
        vBox.getChildren().add(opponentName);
        vBox.getChildren().add(matchRes);
        String cssLayout = "-fx-border-color: #F1C800;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n";
        vBox.setStyle(cssLayout);
        mainBox.getChildren().add(vBox);
    }
    private void addAllBattleHistories() {
        for (BattleHistory battleHistory: SQLManager.getHistory(Config.client)) {
            addNewBattleHistory(battleHistory);
        }
    }
}
