package Controllers.Menu;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * 1V1 Menu controller
 */
public class Multiplayer1V1Con {
    @FXML
    private VBox mainPane;

    /**
     * Initialize
     */
    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/BackGrounds/PlayGame.jpg"), mainPane);
    }

    /**
     * back button handler
     * @param event the event
     */
    @FXML
    void backButtonOnAction(ActionEvent event) {
        FXManager.goTo("playGame.fxml", Config.primaryStage);
    }

    /**
     * create game button handler
     * @param event the event
     */
    @FXML
    void createGameButtonOnAction(ActionEvent event) {
        FXManager.goTo("createGame.fxml", Config.primaryStage);
    }

    /**
     * join game handler
     * @param event the event
     */
    @FXML
    void joinGameButtonOnAction(ActionEvent event) {
        FXManager.goTo("joinGame.fxml", Config.primaryStage);
    }

}
