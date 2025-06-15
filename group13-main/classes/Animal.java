package classes;

/**
 * The {@code Animal} interface represents a virtual pet with basic decay attributes
 * that affect its well-being over time. All animals used in the virtual pet game
 * must implement this interface and define their unique decay rates.
 *
 * <p>These decay values determine how quickly a pet becomes hungry, tired, or unhappy
 * as the game progresses.</p>
 *
 * @version 1.0
 * @author Beril Kayla Coban
 */
public interface Animal {

    /*
    FOR FUTURE EDITORS:
    Ideally, we would have the decay instance variables here as well (hungerDecay etc.). Unfortunately,
    that is not possible to do in Java interfaces.

    The need for the variables is already quite explicit given the getters, so this is not needed, but if
    one wanted to be EXTREMELY explicit it would likely need to be converted to a factory pattern design.
     */


    /**
     * Returns the hunger decay rate for the animal.
     * Determines how fast the animal becomes hungry.
     *
     * @return the hunger decay rate
     */
    int getHungerDecay();

    /**
     * Returns the sleep decay rate for the animal.
     * Determines how fast the animal becomes tired.
     *
     * @return the sleep decay rate
     */
    int getSleepDecay();

    /**
     * Returns the happiness decay rate for the animal.
     * Determines how fast the animal becomes unhappy.
     *
     * @return the happiness decay rate
     */
    int getHappinessDecay();

    /**
     * Returns the name of the animal type.
     * Determines what type of sprite will be used.
     *
     * @return the name of the animal
     */
    String getAnimalType();
}
