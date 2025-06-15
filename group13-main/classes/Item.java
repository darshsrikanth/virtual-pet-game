package classes;

/**
 * Item interface.
 * <br><br>
 * The Item interface provides a general format for each of the item subclasses (FoodItem, DecorItem, GiftItem).
 * It has methods useItem, buyItem and getPrice which are useful to all implementing classes.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public interface Item {
    void useItem();
    void buyItem();
    int getPrice();

}
