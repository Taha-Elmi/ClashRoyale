package Controllers.Menu;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileCon implements Controller {
    @FXML
    private Label username;
    @FXML
    private Label xp;
    @FXML
    private Label level;
    @FXML
    private Button back;

    @FXML
    public void initialize() {
        username.setText("Username: " + Config.client.getName());
        xp.setText("XP: " + Config.client.getXp());
        level.setText("Level: " + Config.client.getLevel());
    }

    @Override
    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == back) {
            FXManager.goTo("MainMenu.fxml",Config.primaryStage);
        } else {
            throw new Exception("unknown event");
        }
    }
}
