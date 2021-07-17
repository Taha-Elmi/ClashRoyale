package Controllers.Menu;

import Controllers.Controller;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;


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
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private ImageView card5;
    @FXML
    private ImageView card6;
    @FXML
    private ImageView card7;
    @FXML
    private ImageView card8;

    public void initialize() {
        levelImage.setImage(FXManager.getImage("/Levels/" + Config.client.getLevel() + ".png"));
        username.setText(Config.client.getName());
        xp.setText("" + Config.client.getXp());
        setDeckCards();
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
    private void setDeckCards() {
        ArrayList<ImageView> deck = new ArrayList<>(4);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        deck.add(card7);
        deck.add(card8);
        for (int i = 0; i < 8; i++) {
            String resource = "/Cards/" + Config.client.getDeckCards().get(i).getClass().getSimpleName() + ".png";
            Image image = FXManager.getImage(resource);
            deck.get(i).setImage(image);
        }
    }
}
