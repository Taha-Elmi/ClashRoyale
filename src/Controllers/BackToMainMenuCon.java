package Controllers;

import Database.FileUtils;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class BackToMainMenuCon implements Controller {

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == yesButton) {
            Config.mediaPlayer.stop();
            ((Stage) yesButton.getScene().getWindow()).close();
            FXManager.goTo("MainMenu.fxml", Config.primaryStage);
        } else if (ae.getSource() == noButton) {
            ((Stage) yesButton.getScene().getWindow()).close();
        } else {
            Config.unknownInputException();
        }
    }
}
