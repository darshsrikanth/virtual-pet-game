package classes;

/**
 * The {@code Axolotl} class is an implementation of the {@link Animal} interface
 * that represents a specific type of virtual pet. This class provides fixed decay values
 * unique to Axolotl for hunger, sleep, and happiness.
 *
 * <p>These values define how quickly an Axolotl becomes hungry, tired, or unhappy in the game loop.
 *
 * @version 1.0
 * author Beril Kayla Coban
 */
public class Axolotl implements Animal {

    /** Hunger decay rate for Axolotl */
    private final int hungerDecay = 10;

    /** Sleep decay rate for Axolotl */
    private final int sleepDecay = 15;

    /** Happiness decay rate for Axolotl */
    private final int happinessDecay = 10;

    /**
     * Gets the hunger decay rate for the Axolotl.
     *
     * @return the hunger decay rate as an int.
     */
    @Override
    public int getHungerDecay() {
        return hungerDecay;
    }

    /**
     * Gets the sleep decay rate for the Axolotl.
     *
     * @return the sleep decay rate as an int.
     */
    @Override
    public int getSleepDecay() {
        return sleepDecay;
    }

    /**
     * Gets the happiness decay rate for the Axolotl.
     *
     * @return the happiness decay rate as an int.
     */
    @Override
    public int getHappinessDecay() {
        return happinessDecay;
    }

    /**
     * Gets a string name of the animal type.
     *
     * @return a string of the animal name.
     */
    @Override
    public String getAnimalType() {
        return "axolotl";
    }
}
