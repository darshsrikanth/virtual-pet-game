package classes;

/**
 * Sandcastle decor item object to be equipped in the player's tank.
 * <br><br>
 * The Sandcastle decor item is the medium-tier decor item. This is reflected in its medium price.
 * This class inherits from the DecorItem class.
 *
 * @see DecorItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Sandcastle extends DecorItem {
    /**
     * Sandcastle constructor. Creates a new Sandcastle object using the parent DecorItem's constructor.
     * The Sandcastle object is initialized with owned and equipped variables set to false, and price set to 75.
     */
    public Sandcastle() {
        super(false, false, 75);
    }
}
