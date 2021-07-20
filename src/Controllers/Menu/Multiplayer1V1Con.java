package Controllers.Menu;


import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Multiplayer1V1Con {
    @FXML
    private VBox mainPane;

    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/BackGrounds/PlayGame.jpg"), mainPane);
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        FXManager.goTo("playGame.fxml", Config.primaryStage);
    }

    @FXML
    void createGameButtonOnAction(ActionEvent event) {
        FXManager.goTo("createGame.fxml", Config.primaryStage);
    }

    @FXML
    void joinGameButtonOnAction(ActionEvent event) {

    }

}
