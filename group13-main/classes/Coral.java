package classes;

/**
 * Coral gift item object to be given to the pet.
 * <br><br>
 * The coral gift item is the lowest tier gift item available for the player to purchase, which is
 * reflected in its lower price and impact of its use.
 * This class inherits from the GiftItem class.
 *
 * @see GiftItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Coral extends GiftItem {
    /**
     * Coral constructor. Creates a new coral object using the parent GiftItem's constructor.
     * The coral object is initialized with happyEffective = 10, price = 25 and quantity = 0.
     */
    public Coral() {
        super(10,25,0);
    }
}
