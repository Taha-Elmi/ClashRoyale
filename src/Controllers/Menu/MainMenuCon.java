package Controllers.Menu;

import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuCon implements Controller {
    private Stage stage;
    @FXML
    private Button playGameButton;
    @FXML
    private Button battleDeckButton;
    @FXML
    private Button battleHistoryButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button exitButton;

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == playGameButton) {
            FXManager.goTo("playGame.fxml",getStage());
        } else if (ae.getSource() == battleDeckButton) {
            FXManager.goTo("battleDeck.fxml",getStage());
        } else if (ae.getSource() == battleHistoryButton) {
            FXManager.goTo("battleHistory.fxml",getStage());
        } else if (ae.getSource() == profileButton) {
            FXManager.goTo("profile.fxml",getStage());
        } else if (ae.getSource() == exitButton) {
            System.exit(0);
        } else {
            throw new Exception("unknown event");
        }
    }

    private Stage getStage() {
        if (stage == null)
            stage = (Stage) exitButton.getScene().getWindow();
        return stage;
    }
}
