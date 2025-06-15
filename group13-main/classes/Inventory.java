package classes;

/**
 * Player Inventory.
 * <br><br>
 * The player inventory contains the food items (Carrot, Shrimp, Fish) and gift items (Coral, MessageInBottle and Pearl)
 * and their respective quantities. It includes methods for obtaining the quantities of each item type and setting
 * the quantity of each item type, useful for purchasing new items or setting up a game save where the player
 * already has a number of items purchased.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Inventory {
    /** An array to store the food items in the inventory. */
    private FoodItem[] foodItems;
    /** An array to store the gift items in the inventory. */
    private GiftItem[] giftItems;

    /**
     * Inventory constructor.
     * When the Inventory object is created the arrays of food items and gift items are initialized with new
     * instances of each item type.
     */
    public Inventory() {
        this.foodItems = new FoodItem[]{new Carrot(), new Shrimp(), new Fish()};
        this.giftItems = new GiftItem[]{new Coral(), new MessageInBottle(), new Pearl()};
    }

    /**
     * Retrieves the quantity of the specified type of food item currently in the player inventory.
     *
     * @param index the type of food item whose quantity is being checked.
     * @return an integer representing the quantity of the item.
     */
    public int getFoodItemQuantity(int index) {
        return this.foodItems[index].getQuantity();
    }

    /**
     * Retrieves the quantity of the specified type of gift item currently in the player inventory.
     *
     * @param index the type of gift item whose quantity is being checked.
     * @return an integer representing the quantity of the item.
     */
    public int getGiftItemQuantity(int index) {
        return this.giftItems[index].getQuantity();
    }

    /**
     * Sets the quantity of an item in the player inventory to a specific amount.
     *
     * @param type integer indicating the type of item whose quantity is being set -- 0 being a food item, 1 a gift item
     * @param index integer indicating which item in the food/gift item list needs its quantity changed
     * @param amt integer representing what the item quantity will be set to
     */
    public void setItemQuantity(int type, int index, int amt) {
        if (type == 0) { // if the item is a food item
            for (int i = 0; i < amt; i++) { // looping "amt" number of times and incrementing the item quantity with each iteration
                this.foodItems[index].buyItem();
            }
        } else if (type == 1) { // if the item is a gift item
            for (int i = 0; i < amt; i++) { // looping "amt" number of times and incrementing the item quantity with each iteration
                this.giftItems[index].buyItem();
            }
        }


    }

    /**
     * Increments the quantity of an item in the player inventory by one.
     *
     * @param type integer indicating the type of item being bought -- 0 being a food item, 1 a gift item
     * @param index integer indicating which item in the food/gift item list is being bought
     */
    public void buyItem(int type, int index) {
        if (type == 0) { // if the item is a food item, increment the item quantity at index in the foodItems array
            this.foodItems[index].buyItem();
        } else if (type == 1) { // if the item is a gift item, increment the item quantity at index in the giftItems array
            this.giftItems[index].buyItem();
        }
    }

    /**
     * Method to use one of the items in the player inventory.
     *
     * @param type integer indicating the type of item being used -- 0 being a food item, 1 a gift item
     * @param index integer indicating which item in the food/gift item list is being used
     * @return boolean true if the item was successfully used, false if there wasn't any quantity left of that item to be used
     */
    public boolean useItem(int type, int index) {
        if (type == 0) { // if the item was a food item
            if (this.foodItems[index].getQuantity() > 0) { // checking that the player owns at least one of that item
                this.foodItems[index].useItem(); // if so, use it and return true
                return true;
            } else { // if the player doesn't own at least one of that item, return false
                return false;
            }
        } else if (type == 1) { // if the item was a gift item
            if (this.giftItems[index].getQuantity() > 0) { // checking that the player owns at least one of that item
                this.giftItems[index].useItem(); // if so, use it and return true
                return true;
            } else { // if the player doesn't own at least one of that item, return false
                return false;
            }
        } else { // if an incorrect item type was entered, return false
            return false;
        }
    }
}
