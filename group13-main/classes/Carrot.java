package classes;

/**
 * Carrot food item object to be fed to the pet.
 * <br><br>
 * The carrot food item has different impacts on the pet's hunger depending on the pet type.
 * This class inherits from the FoodItem class.
 * It is most effective when fed to a pufferfish pet.
 * It has medium effectiveness for an axolotl pet, and lowest effectiveness for a shark pet.
 *
 * @see FoodItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Carrot extends FoodItem {
    /**
     * Carrot constructor. Creates a new carrot object using the parent FoodItem's constructor.
     * The carrot object is initialized with a low effectiveness on the shark pet, high effectiveness on
     * the pufferfish pet, and a medium effectiveness on the axolotl pet. Quantity is initialized to zero.
     */
    public Carrot() {
        super(5,15,10,0);
    }

}
