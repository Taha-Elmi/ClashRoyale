package Controllers;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Back to main menu controller
 */
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
            FXManager.setStageNormal(Config.primaryStage);
            Config.playMusic("assets/musics/MainTheme.mp3");
            FXManager.goTo("MainMenu.fxml", Config.primaryStage);
        } else if (ae.getSource() == noButton) {
            FXManager.setStageReadyForGame(Config.primaryStage);
            ((Stage) yesButton.getScene().getWindow()).close();
        } else {
            Config.unknownInputException();
        }
    }
}
