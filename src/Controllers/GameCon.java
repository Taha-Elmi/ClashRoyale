package Controllers;

import Main.Config;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameCon {

    @FXML
    private GridPane deck;

    @FXML
    public void initialize() {
        for (int i = 0; i < 8; i++) {
            ((ImageView) deck.getChildren().get(i)).setImage(Config.cardImages.get(i).getImage());
        }
    }
}
