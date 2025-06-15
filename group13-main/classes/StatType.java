package classes;

/**
 * The {@code StatType} class defines constant integer values representing
 * different types of pet statistics such as health, hunger, sleep, and happiness.
 *
 * These constants are used to identify and manipulate specific stats within the game logic.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class StatType {

    private final int HEALTH = 0;
    private final int HUNGER = 1;
    private final int SLEEP = 2;
    private final int HAPPINESS = 3;

    /**
     * Returns the integer constant for the health stat.
     *
     * @return the value representing the health stat
     */
    public int getHEALTH() {
        return HEALTH;
    }

    /**
     * Returns the integer constant for the hunger stat.
     *
     * @return the value representing the hunger stat
     */
    public int getHUNGER() {
        return HUNGER;
    }

    /**
     * Returns the integer constant for the sleep stat.
     *
     * @return the value representing the sleep stat
     */
    public int getSLEEP() {
        return SLEEP;
    }

    /**
     * Returns the integer constant for the happiness stat.
     *
     * @return the value representing the happiness stat
     */
    public int getHAPPINESS() {
        return HAPPINESS;
    }
}
