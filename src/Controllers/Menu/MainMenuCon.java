package Controllers.Menu;

import Controllers.Controller;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Main Menu controller
 */
public class MainMenuCon implements Controller {
    private Stage stage;

    @FXML
    private BorderPane mainBorder;

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

    /**
     * button press action handler
     * @param ae the ActionEvents.
     * @throws Exception exception
     */
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

    /**
     * Initialize.
     */
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/Views/Assets/Pictures/BackGrounds/MainMenu.jpg"));
        FXManager.setBackground(image,mainBorder);
    }

    /**
     * getter of the stage
     * @return the stage
     */
    private Stage getStage() {
        if (stage == null)
            stage = (Stage) exitButton.getScene().getWindow();
        return stage;
    }
}
