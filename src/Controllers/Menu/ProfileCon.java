package Controllers.Menu;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class ProfileCon implements Controller {
    @FXML
    private Pane mainBorder;
    @FXML
    private Label username;
    @FXML
    private Label xp;
    @FXML
    private ImageView levelImage;
    @FXML
    private Button back;

    @FXML
    public void initialize() {
        levelImage.setImage(FXManager.getImage("/Levels/" + Config.client.getLevel() + ".png"));
        username.setText(Config.client.getName());
        xp.setText("" + Config.client.getXp());
        FXManager.setBackground(FXManager.getImage("/BackGrounds/Profile.png"),mainBorder);
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
