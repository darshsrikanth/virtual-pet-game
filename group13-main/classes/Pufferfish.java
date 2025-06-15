package classes;

/**
 * The {@code Pufferfish} class is an implementation of the {@link Animal} interface
 * that represents a specific type of virtual pet. This class provides fixed decay values
 * unique to Pufferfish for hunger, sleep, and happiness.
 *
 * <p>These values define how quickly a Pufferfish becomes hungry, tired, or unhappy in the game loop.
 *
 * @version 1.0
 * author Beril Kayla Coban
 */
public class Pufferfish implements Animal {

    /** Hunger decay rate for Pufferfish */
    private final int hungerDecay = 10;

    /** Sleep decay rate for Pufferfish */
    private final int sleepDecay = 10;

    /** Happiness decay rate for Pufferfish */
    private final int happinessDecay = 15;

    /**
     * Gets the hunger decay rate for the Pufferfish.
     *
     * @return the hunger decay rate as an int.
     */
    @Override
    public int getHungerDecay() {
        return hungerDecay;
    }

    /**
     * Gets the sleep decay rate for the Pufferfish.
     *
     * @return the sleep decay rate as an int.
     */
    @Override
    public int getSleepDecay() {
        return sleepDecay;
    }

    /**
     * Gets the happiness decay rate for the Pufferfish.
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
        return "pufferfish";
    }
}
