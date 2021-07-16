package Controllers;

import Main.Config;
import Models.Cards.CardImage;
import Models.Graphic.FXManager;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameCon {

    @FXML
    private GridPane deck;

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
    private HBox mainBorder;

    @FXML
    private ImageView next;

    @FXML
    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/Game/jungle.jpg"), mainBorder);
        for (int i = 0; i < 4; i++) {
            ((ImageView) deck.getChildren().get(i)).setImage(CardImage.find(Config.client.getDeckCards().get(i)).getImage());
        }
        next.setImage(CardImage.find(Config.client.getDeckCards().get(4)).getImage());
        name.setText(Config.client.getName());
        level.setText("" + Config.client.getLevel());
        hp.setText("MAX");
    }
}
