package classes;

/**
 * Pearl gift item object to be given to the pet.
 * <br><br>
 * The pearl gift item is the highest tier gift item available for the player to purchase, which is
 * reflected in its higher price and impact of its use.
 * This class inherits from the GiftItem class.
 *
 * @see GiftItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Pearl extends GiftItem {
    /**
     * Pearl constructor. Creates a new pearl object using the parent GiftItem's constructor.
     * The pearl object is initialized with happyEffective = 25, price = 100 and quantity = 0.
     */
    public Pearl() {
        super(25,100,0);
    }

}
