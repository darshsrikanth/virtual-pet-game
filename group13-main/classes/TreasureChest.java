package classes;

/**
 * TreasureChest decor item object to be equipped in the player's tank.
 * <br><br>
 * The TreasureChest decor item is the lowest-tier decor item. This is reflected in its low price.
 * This class inherits from the DecorItem class.
 *
 * @see DecorItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class TreasureChest extends DecorItem {
    /**
     * TreasureChest constructor. Creates a new TreasureChest object using the parent DecorItem's constructor.
     * The TreasureChest object is initialized with owned and equipped variables set to false, and price set to 50.
     */
    public TreasureChest() {
        super(false,false,50);
    }
}
