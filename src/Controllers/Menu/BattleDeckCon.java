package Controllers.Menu;

import Main.Config;
import Models.Cards.Card;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class BattleDeckCon {

    @FXML
    private BorderPane mainBorder;

    @FXML
    private ImageView upperCard;

    @FXML
    private ImageView lowerCard;

    @FXML
    private Button changeButton;

    @FXML
    private Button backButton;

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

    @FXML
    private ImageView outCard1;

    @FXML
    private ImageView outCard2;

    @FXML
    private ImageView outCard3;

    @FXML
    private ImageView outCard4;

    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == backButton) {
            FXManager.goTo("MainMenu.fxml", Config.primaryStage);
        } else {
            throw new Exception("unknown event");
        }
    }

    public void initialize() {
        ArrayList<ImageView> deck = new ArrayList<>(4);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        deck.add(card7);
        deck.add(card8);

        ArrayList<ImageView> outDeck = new ArrayList<>(4);
        outDeck.add(outCard1);
        outDeck.add(outCard2);
        outDeck.add(outCard3);
        outDeck.add(outCard4);

        ArrayList<Card> outCards = new ArrayList<>(4);
        for (Card card : Config.cards) {
            if (!Config.client.getDeckCards().contains(card))
                outCards.add(card);
        }

        for (int i = 0; i < 8; i++) {
            String resource = "/Cards/" + Config.client.getDeckCards().get(i).getClass().getSimpleName() + ".png";
            Image image = FXManager.getImage(resource);
            deck.get(i).setImage(image);
        }

        for (int i = 0; i < 4; i++) {
            String resource = "/Cards/" + outCards.get(i).getClass().getSimpleName() + ".png";
            Image image = FXManager.getImage(resource);
            outDeck.get(i).setImage(image);
        }
    }
}
