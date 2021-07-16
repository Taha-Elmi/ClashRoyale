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
            Stage stage = (Stage) ok.getScene().getWindow();
            stage.close();
            startSinglePlayerGame(mode);
        } else {
            throw new Exception("unknown event");
        }
    }
    private void startSinglePlayerGame(String mode) {
        System.out.println("singlePlayerStarted");
        Stage stage = FXManager.openWindow("game.fxml");

        stage.setTitle("Clash Royale");
        stage.getIcons().add(FXManager.getImage("/Icons/mainicon.jpg"));

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }
}
