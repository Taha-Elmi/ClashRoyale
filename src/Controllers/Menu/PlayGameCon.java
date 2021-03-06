package Controllers.Menu;

import Controllers.Controller;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Play Game Menu controller
 */
public class PlayGameCon implements Controller {
    private Stage stage;

    @FXML
    private BorderPane mainBorder;

    @FXML
    private Button singlePlayer;

    @FXML
    private Button oneVsOne;

    @FXML
    private Button twoVsTwo;

    @FXML
    private Button back;

    /**
     * button press handler
     * @param ae the ActionEvents.
     * @throws Exception exception
     */
    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == singlePlayer) {
            FXManager.openWindow("singlePlayer.fxml");
        } else if (ae.getSource() == oneVsOne) {
            FXManager.goTo("1v1.fxml", Config.primaryStage);
        } else if (ae.getSource() == twoVsTwo) {

        } else if (ae.getSource() == back) {
            FXManager.goTo("MainMenu.fxml",getStage());
        } else {
            throw new Exception("unknown event");
        }
    }

    /**
     * Initialize
     */
    public void initialize() {
        Image image = FXManager.getImage("/BackGrounds/PlayGame.jpg");
        FXManager.setBackground(image,mainBorder);
    }

    /**
     * getter of the stage field
     * @return the stage
     */
    private Stage getStage() {
        if (stage == null)
            stage = (Stage) back.getScene().getWindow();
        return stage;
    }
}
