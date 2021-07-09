package Controllers.Menu;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

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
        Config.primaryStage.close();
    }
}
