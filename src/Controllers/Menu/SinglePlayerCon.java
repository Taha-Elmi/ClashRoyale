package Controllers.Menu;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SinglePlayerCon implements Controller{
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button ok;
    @FXML
    public void initialize() {
        choiceBox.getItems().add("easy");
        choiceBox.getItems().add("medium");
        choiceBox.getItems().add("hard");
        choiceBox.setValue("easy");
        Platform.runLater(() -> ok.requestFocus());
    }

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == ok) {
            String mode = choiceBox.getValue();
            ((Stage) ok.getScene().getWindow()).close();
            startSinglePlayerGame(mode);
        } else {
            throw new Exception("unknown event");
        }
    }
    private void startSinglePlayerGame(String mode) {
        Config.mediaPlayer.stop();
        FXManager.goTo("game.fxml",Config.primaryStage);
        System.out.println("singlePlayerStarted");
    }
}
