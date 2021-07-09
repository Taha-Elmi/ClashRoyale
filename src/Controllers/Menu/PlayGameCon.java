package Controllers.Menu;

import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayGameCon implements Controller {
    private Stage stage;
    @FXML
    private Button singlePlayer;
    @FXML
    private Button oneVsOne;
    @FXML
    private Button twoVsTwo;
    @FXML
    private Button back;

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == singlePlayer) {
            FXManager.openWindow("singlePlayer.fxml");
        } else if (ae.getSource() == oneVsOne) {
            FXManager.goTo("battleDeck.fxml",getStage());
        } else if (ae.getSource() == twoVsTwo) {
            FXManager.goTo("battleHistory.fxml",getStage());
        } else if (ae.getSource() == back) {
            FXManager.goTo("MainMenu.fxml",getStage());
        } else {
            throw new Exception("unknown event");
        }
    }

    private Stage getStage() {
        if (stage == null)
            stage = (Stage) back.getScene().getWindow();
        return stage;
    }
}
