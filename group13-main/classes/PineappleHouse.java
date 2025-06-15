package classes;

/**
 * PineappleHouse decor item object to be equipped in the player's tank.
 * <br><br>
 * The PineappleHouse decor item is the highest tier decor item. This is reflected in its higher price.
 * This class inherits from the DecorItem class.
 *
 * @see DecorItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class PineappleHouse extends DecorItem {
    /**
     * PineappleHouse constructor. Creates a new PineappleHouse object using the parent DecorItem's constructor.
     * The PineappleHouse object is initialized with owned and equipped variables set to false, and price set to 100.
     */
    public PineappleHouse() {
        super(false, false, 100);
    }
}
