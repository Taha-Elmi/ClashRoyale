package Models.Interfaces;


/**
 * The interface Damageable.
 */
public interface Damageable {
    /**
     * Gets damage.
     *
     * @param damage the damage
     */
    void gotDamage(int damage);

    /**
     * checks if dead
     * @return true if dead, false otherwise
     */
    boolean isDead();
}
