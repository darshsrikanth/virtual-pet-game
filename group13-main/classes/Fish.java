package classes;

/**
 * Fish food item object to be fed to the pet.
 * <br><br>
 * The fish food item has different impacts on the pet's hunger depending on the pet type.
 * This class inherits from the FoodItem class.
 * It is most effective when fed to a shark pet.
 * It has medium effectiveness for a pufferfish pet, and lowest effectiveness for an axolotl pet.
 *
 * @see FoodItem
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class Fish extends FoodItem {
    /**
     * Fish constructor. Creates a new fish object using the parent FoodItem's constructor.
     * The fish object is initialized with a high effectiveness on the shark pet, medium effectiveness on
     * the pufferfish pet, and a low effectiveness on the axolotl pet. Quantity is initialized to zero.
     */
    public Fish() {
        super(15,10,5,0);
    }
}
