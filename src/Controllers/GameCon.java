package Controllers;

import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.troops.Troop;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GameCon implements Controller {
    private Card chosenCard;
    @FXML
    private GridPane deck;
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private Label opponentName;

    @FXML
    private Label opponentLevel;

    @FXML
    private Label opponentHp;

    @FXML
    private Label name;

    @FXML
    private Label level;

    @FXML
    private Label hp;

    @FXML
    private HBox mainBorder;

    @FXML
    private ImageView next;

    @FXML
    private Pane boardPane;


    @FXML
    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/Game/jungle.jpg"), mainBorder);
        setCardsImages();
        name.setText(Config.client.getName());
        level.setText("" + Config.client.getLevel());
        hp.setText("MAX");
        Platform.runLater(() -> {
            FXManager.setStageReadyForGame(Config.primaryStage);
        });
    }

    private void setCardsImages() {
        card1.setImage(CardImage.find(Config.client.getDeckCards().get(0)).getImage());
        card2.setImage(CardImage.find(Config.client.getDeckCards().get(1)).getImage());
        card3.setImage(CardImage.find(Config.client.getDeckCards().get(2)).getImage());
        card4.setImage(CardImage.find(Config.client.getDeckCards().get(3)).getImage());
        next.setImage(CardImage.find(Config.client.getDeckCards().get(4)).getImage());
    }
    @Override
    public void actionHandler(ActionEvent ae) throws Exception {

    }
    @FXML
    private void dragHandler(DragEvent de) {

    }
    @FXML
    private void dragDetectionHandler(MouseEvent me) {
        if (!(me.getSource() instanceof ImageView)) {
            return;
        }
        Dragboard db = ((ImageView) me.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        chosenCard = CardImage.find(((ImageView) me.getSource()).getImage()).getCard();
        cb.putImage(((ImageView) me.getSource()).getImage());
        db.setContent(cb);
        me.consume();
    }
    @FXML
    private void dragOverHandler(DragEvent de) {
        if (de.getDragboard().hasImage()) {
            de.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    private void dragDroppedHandler(DragEvent de) {
        bornCard(chosenCard,de);
        playCard(chosenCard);
        chosenCard = null;
    }

    private void bornCard(Card card,DragEvent de) {
        try {
            double x = de.getX();
            double y = de.getY();
            for (int i = 0; i < card.getNumber(); i++) {
                ImageView imageView = new ImageView(card.born());
                if (i % 2 == 0) {
                    x += 20;
                } else {
                    y += 20;
                }
                imageView.setX(x);
                imageView.setY(y);
                boardPane.getChildren().add(imageView);
                if (card instanceof Troop) {
                    Troop troop = (Troop) card;
                    Platform.runLater(() -> troop.move(imageView,new Point2D(420,60)));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void playCard(Card card) {
        Game.getInstance().getPlayer1().playCard(card);
        setCardsImages();
    }
}
