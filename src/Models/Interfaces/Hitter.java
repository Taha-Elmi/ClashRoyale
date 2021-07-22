package Models.Interfaces;

import Models.Cards.Card;

/**
 * The interface Hitter.
 */
public interface Hitter {
    /**
     * Hit.
     *
     * @param damageable the card
     */
    void hit(Damageable damageable);
}
