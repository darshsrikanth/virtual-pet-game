package classes;

/**
 * Shrimp food item object to be fed to the pet.
 * <br><br>
 * The shrimp food item has different impacts on the pet's hunger depending on the pet type.
 * This class inherits from the FoodItem class.
 * It is most effective when fed to an axolotl pet.
 * It has medium effectiveness for a shark pet, and lowest effectiveness for a pufferfish pet.
 *
 * @see FoodItem
 * @author Cadence Megan McGillicuddy
 */
public class Shrimp extends FoodItem {
    /**
     * Shrimp constructor. Creates a new shrimp object using the parent FoodItem's constructor.
     * The shrimp object is initialized with a medium effectiveness on the shark pet, low effectiveness on
     * the pufferfish pet, and a high effectiveness on the axolotl pet. Quantity is initialized to zero.
     */
    public Shrimp() {
        super(10,5,15,0);
    }
}
