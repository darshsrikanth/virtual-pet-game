package classes;

/**
 * Food Item parent object.
 * <br><br>
 * The food item class provides a general format for each of the food item subclasses (Shrimp, Carrot and Fish).
 * It has variables price, quantity, sharkEffective, pufferEffective and axolEffective
 * which are useful for all subclasses.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class FoodItem implements Item {
    /** The price for a food item. */
    private final int price = 10;
    /** The quantity of the item the player currently has in their inventory. */
    protected int quantity;
    /** The effectiveness level for a shark pet. */
    protected int sharkEffective;
    /** The effectiveness level for a pufferfish pet. */
    protected int pufferEffective;
    /** The effectiveness level for an axolotl pet. */
    protected int axolEffective;

    /**
     * Food item constructor. Creates a new food item.
     *
     * @param sharkEffective the effectiveness the respective food item has on a shark pet
     * @param pufferEffective the effectiveness the respective food item has on a pufferfish pet
     * @param axolEffective the effectiveness the respective food item has on an axolotl pet
     * @param quantity the quantity of the item in the player inventory
     */
    public FoodItem(int sharkEffective, int pufferEffective, int axolEffective, int quantity) {
        this.sharkEffective = sharkEffective;
        this.pufferEffective = pufferEffective;
        this.axolEffective = axolEffective;
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
     * Retrieves the price of the item to be used when the player is purchasing items from the shop.
     *
     * @return an integer representing the price of the item.
     */
    public int getPrice() {
        return this.price;
    }
}