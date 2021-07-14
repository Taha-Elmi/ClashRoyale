package Models.Cards;

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
}
