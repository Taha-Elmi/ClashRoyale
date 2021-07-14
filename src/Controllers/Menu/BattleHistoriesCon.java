package Controllers.Menu;

import Database.BattleHistory;
import Database.SQLManager;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class BattleHistoriesCon implements Controller {
    private Button backButton;
    @FXML
    private ScrollPane mainPane;
    @FXML
    private VBox mainBox;

    public void initialize() {
        mainBox.getChildren().clear();
        createBackButton();
        Image image = FXManager.getImage("/BackGrounds/BattleHistory.jpg");
        FXManager.setBackground(image,mainBox);
        boolean isEmpty = addAllBattleHistories();
        if (isEmpty) {
            emptyList();
        }
    }

    private void createBackButton() {
        backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            try {
                actionHandler(actionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        VBox.setMargin(backButton,new Insets(0,0,30,0));
        mainBox.getChildren().add(backButton);
    }

    private void emptyList() {
        VBox vBox = new VBox();
        vBox.prefWidth(320);
        vBox.prefHeight(84);
        vBox.setPadding(new Insets(0,10,0,10));
        Label label = new Label("No Battle history to show");
        String cssLayout = "-fx-font-size: 27;";
        label.setStyle(cssLayout);
        vBox.getChildren().add(label);
        mainBox.getChildren().add(vBox);
    }

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == backButton) {
            FXManager.goTo("MainMenu.fxml",Config.primaryStage);
        } else {
            Config.unknownInputException();
        }
    }

    private void addNewBattleHistory(BattleHistory battleHistory) {
        VBox vBox = new VBox();
        vBox.prefWidth(320);
        vBox.prefHeight(84);
        vBox.setPadding(new Insets(0,10,0,10));
//        vBox.setAlignment(Pos.TOP_CENTER);
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
    private boolean addAllBattleHistories() {
        ArrayList<BattleHistory> battleHistories = SQLManager.getHistory(Config.client);
        if (battleHistories.size() <= 0) {
            return true;
        }
        Collections.reverse(battleHistories);
        for (BattleHistory battleHistory: battleHistories) {
            addNewBattleHistory(battleHistory);
        }
        return false;
    }
}
