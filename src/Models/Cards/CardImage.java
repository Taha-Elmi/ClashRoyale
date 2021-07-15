package Models.Cards;

import Main.Config;
import javafx.scene.image.Image;

public class CardImage {
    private Card card;
    private Image image;

    public CardImage(Card card, Image image) {
        this.card = card;
        this.image = image;
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
}
