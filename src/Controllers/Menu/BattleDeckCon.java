package Controllers.Menu;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Label message;

    public void actionHandler(ActionEvent ae) throws Exception {
        if (ae.getSource() == backButton) {
            FXManager.goTo("MainMenu.fxml", Config.primaryStage);
        } else {
            throw new Exception("unknown event");
        }
    }

    public void initialize() {
        updateOnScreenCards();
    }

    private void updateOnScreenCards() {
        Image cardsq = FXManager.getImage("/icons/cardsq.png");
        upperCard.setImage(cardsq);
        lowerCard.setImage(cardsq);

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

        ArrayList<CardImage> outCards = new ArrayList<>(4);
        for (CardImage cardImage : Config.cardImages) {
            if (!Config.client.getDeckCards().contains(cardImage.getCard()))
                outCards.add(cardImage);
        }

        for (int i = 0; i < 8; i++) {
            for (CardImage cardImage : Config.cardImages) {
                if (Config.client.getDeckCards().get(i).equals(cardImage.getCard())) {
                    deck.get(i).setImage(cardImage.getImage());
                    break;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            outDeck.get(i).setImage(outCards.get(i).getImage());
        }
    }

    @FXML
    void cardOnClick(MouseEvent event) {
        upperCard.setImage(((ImageView)event.getSource()).getImage());
    }

    @FXML
    void outCardOnClick(MouseEvent event) {
        lowerCard.setImage(((ImageView)event.getSource()).getImage());
    }

    @FXML
    void changeButtonOnAction(ActionEvent event) {
        if (!Config.cardImages.contains(new CardImage(null, upperCard.getImage()))
        || !Config.cardImages.contains(new CardImage(null, lowerCard.getImage()))) {
            message.setText("You have not chosen the card you want to change.");
        } else {
            message.setText("Done.");
        }
    }
}
