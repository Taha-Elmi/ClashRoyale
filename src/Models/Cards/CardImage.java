package Models.Cards;

import Main.Config;
import Models.Cards.troops.Troop;
import Models.Graphic.FXManager;
import javafx.scene.image.Image;

public class CardImage {
    private Card card;
    private Image image;
    private Image forwardGif;
    private Image backwardGif;
    private Image forwardDamagingGif;
    private Image backwardDamagingGif;

    public CardImage(Card card, Image image) {
        this.card = card;
        this.image = image;
    }

    public CardImage(Card card, Image image, int playerNumber) {
        this.card = card;
        this.image = image;
        if (card instanceof Troop)
            assignGifs(playerNumber);
    }

    public Card getCard() {
        return card;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CardImage))
            return false;
        return image.equals(((CardImage)obj).getImage());
    }

    /**
     * gets an image and returns associated CardImage
     * @param image the image which should be included in the return CardImage
     * @return the CardImage included the parameter image
     */
    public static CardImage find(Image image) {
        for (CardImage cardImage : Config.cardImages) {
            if (cardImage.getImage().equals(image))
                return cardImage;
        }
        return null;
    }

    /**
     * gets a card and returns associated CardImage
     * @param card the card which should be included in the return CardImage
     * @return the CardImage included the parameter card
     */
    public static CardImage find(Card card) {
        for (CardImage cardImage : Config.cardImages) {
            if (cardImage.getCard().equals(card))
                return cardImage;
        }
        return null;
    }

    private void assignGifs(int playerNumber) {
        String cartName = card.getClass().getSimpleName();
        if (playerNumber == 1) {
            forwardGif = image;
            forwardDamagingGif = FXManager.getImage("/Gifs/" + cartName + "/forward_damage.gif");
        } else if (playerNumber == 2) {
            backwardGif = image;
            backwardDamagingGif = FXManager.getImage("/Gifs/" + cartName + "/backward_damage.gif");
        }
    }

    public void setDamageGif() {
        if (forwardDamagingGif != null)
            image = forwardDamagingGif;
        else
            image = backwardDamagingGif;
    }

    public void setNormalGif() {
        if (forwardGif != null)
            image = forwardGif;
        else
            image = backwardGif;
    }

    public boolean isGoingForward() {
        return forwardGif != null;
    }
}
