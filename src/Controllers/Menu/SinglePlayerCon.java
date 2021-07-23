package Controllers.Menu;

import Controllers.Controller;
import Main.Config;
import Models.GameManager.*;
import Models.Graphic.FXManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * Single Player Menu controller
 */
public class SinglePlayerCon implements Controller {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button ok;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        choiceBox.getItems().add("easy");
        choiceBox.getItems().add("medium");
        choiceBox.getItems().add("hard");
        choiceBox.setValue("easy");
        Platform.runLater(() -> ok.requestFocus());
    }

    /**
     * ok button handler
     * @param ae the ActionEvents.
     * @throws Exception exception
     */
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
        Player player1 = new Player(Config.client.getDeckCards());
        Player player2 = new Player(Config.getPlayer2Cards());
        Manager manager = switch (mode) {
            case "easy" -> new StupidRobotManager("StupidRobot", player2);
            case "medium" -> new SmartRobotManager("SmartRobot", player2);
            case "hard" -> new GeniusRobotManager("GeniusRobot", player2);
            default -> throw new IllegalStateException("Unexpected value: " + mode);
        };
        Game game = new Game(player1,
                player2,GameMode.SINGLE,manager);
        Config.mediaPlayer.stop();
        FXManager.goTo("game.fxml",Config.primaryStage);
        System.out.println("singlePlayerStarted");
    }
}
