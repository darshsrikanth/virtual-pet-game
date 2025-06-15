package classes;

/**
 * Shop for items.
 * <br><br>
 * The shop contains instances of each of the food, gift and decor items for the player to view and purchase.
 * It includes methods for retrieving the prices of each item, purchasing decor items and equipping or unequipping them.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Shop {
    /** Two-dimensional array to store respective arrays for the food, gift and decor items. */
    private Item[][] shopArray;

    /**
     * Shop constructor.
     * When the Shop object is created new instances of each food, gift and decor item are also created in their
     * respective arrays.
     */
    public Shop() {
        this.shopArray = new Item[][]{new FoodItem[]{new Carrot(), new Shrimp(), new Fish()}, new GiftItem[]{new Coral(), new MessageInBottle(), new Pearl()}, new DecorItem[]{new Sandcastle(), new TreasureChest(), new PineappleHouse()}};
    }

    /**
     * Retrieves the price of the item of the indicated type and index in the shop list.
     *
     * @param type integer indicating whether the item belongs to the food item, gift item or decor item array.
     * @param index integer representing the index of the item within its corresponding type array.
     * @return an integer representing the price of the item.
     */
    public int getItemPrice(int type, int index) {
        return this.shopArray[type][index].getPrice();
    }

    /**
     * Equips an owned decor item.
     *
     * @param index integer representing the index of the item within the decor item array.
     */
    public void equipItem(int index) {
        this.shopArray[2][index].useItem();
    }

    /**
     * Unequips an owned decor item.
     *
     * @param index integer representing the index of the item within the decor item array.
     */
    public void unequipItem(int index) {
        this.shopArray[2][index].useItem();
    }

    /**
     * Returns the ownership status of the decor object.
     * @param index integer representing the index of the item within the decor item array.
     * @return boolean true if the decor item is owned, false otherwise.
     */
    public boolean decorOwnedStatus(int index) {
        DecorItem decor = (DecorItem) this.shopArray[2][index];
        return decor.isOwned();
    }

    /**
     * Returns the equipped status of the decor object.
     * @param index integer representing the index of the item within the decor item array.
     * @return boolean true if the decor item is equipped, false otherwise.
     */
    public boolean decorEquippedStatus(int index) {
        DecorItem decor = (DecorItem) this.shopArray[2][index];
        return decor.getUseStatus();
    }

    /**
     * Buy a decor item.
     * This method allows the player to buy a decor item if it is not already owned.
     *
     * @param index integer representing the index of the item within the decor item array.
     */
    public void buyDecor(int index) {
        Item item = this.shopArray[2][index];
        // casting the item being bought to a DecorItem object so we can set its owned status
        DecorItem thisItem = (DecorItem) item;
        if (!thisItem.isOwned()) { // if the item is not already owned
            thisItem.buyItem(); // changes the item's owned status to true
        }
    }
}
