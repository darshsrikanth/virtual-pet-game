package classes;

/**
 * Decor Item parent object.
 * <br><br>
 * The Decor item class provides a general format for each of the decor item subclasses (PineappleHouse, TreasureChest
 * and Sandcastle).
 * It has variables price, owned and equipped which are useful to all subclasses.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class DecorItem implements Item {
    /** boolean indicating whether the decor item is owned or not */
    private boolean owned;
    /** boolean indicating whether the decor item is equipped or not */
    protected boolean equipped;
    /** integer indicating the price of the decor item */
    protected int price;

    /**
     * Decor item constructor. Creates a new Decor item.
     *
     * @param owned boolean indicating whether the decor item is owned or not
     * @param equipped boolean indicating whether the decor item is equipped or not
     * @param price the price of the decor item
     */
    public DecorItem(boolean owned, boolean equipped, int price) {
        this.owned = owned;
        this.equipped = equipped;
        this.price = price;
    }

    /**
     * Retrieves whether the player already owns the decor item or not
     *
     * @return boolean true if the decor item has already been purchased by the player, false otherwise
     */
    public boolean isOwned() {
        return this.owned;
    }

    /**
     * Retrieves the price of the item to be used when the player is purchasing items from the shop.
     *
     * @return an integer representing the price of the item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Retrieves whether the decor item is currently equipped
     *
     * @return boolean true if the decor item is equipped, false otherwise
     */
    public boolean getUseStatus() {
        return this.equipped;
    }

    /**
     * Called when the player equips a decor item.
     * The action is successful only if the player owns the item.
     */
    private void equip() {
        if (isOwned()) {
            this.equipped = true;
        }
    }

    /**
     * Called when the player unequips a decor item.
     * The action is successful only if the player owns the item.
     */
    private void unequip() {
        if (isOwned()) {
            this.equipped = false;
        }
    }

    public void useItem() {
        if (!equipped) {
            equip();
        } else {
            unequip();
        }
    }

    /**
     * Called when the player buys an item from the shop. Changes the item's boolean owned to true.
     */
    public void buyItem() {
        this.owned = true;
    }

}
