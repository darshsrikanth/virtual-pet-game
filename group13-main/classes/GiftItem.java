package classes;

/**
 * Gift Item parent object.
 * <br><br>
 * The gift item class provides a general format for each of the gift item subclasses (Pearl, Coral and MessageInBottle).
 * It has variables price, quantity and happyEffective which are useful for all subclasses.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class GiftItem implements Item {
    /** The increment by which the pet's happiness is affected when the item is used. */
    protected int happyEffective;
    /** The price for a gift item. */
    protected int price;
    /** The quantity of the item the player currently has in their inventory. */
    protected int quantity;

    /**
     * Gift item constructor. Creates a new gift item.
     *
     * @param happyEffective the amount by which the gift increments pet happiness
     * @param price the price of the gift item
     * @param quantity the quantity of the item in the player inventory
     */
    public GiftItem(int happyEffective, int price, int quantity) {
        this.happyEffective = happyEffective;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the quantity of the item the player currently has in their inventory.
     *
     * @return an integer representing the quantity of the item.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Called when the player uses an item. Subtracts one from the item's quantity.
     */
    public void useItem() {
        this.quantity -= 1;
    }

    /**
     * Called when the player buys an item from the shop. Adds one to the item's quantity.
     */
    public void buyItem() {
        this.quantity += 1;
    }

    /**
     * Retrieves the increment by which the pet's happiness is affected when the item is used.
     *
     * @return an integer representing the amount by which to increment the pet happiness.
     */
    public int getHappyEffective() {
        return this.happyEffective;
    }

    /**
     * Retrieves the price of the item to be used when the player is purchasing items from the shop.
     *
     * @return an integer representing the price of the item.
     */
    public int getPrice() {
        return this.price;
    }
}
