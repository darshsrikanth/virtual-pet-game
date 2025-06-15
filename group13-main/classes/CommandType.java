package classes;

/**
 * The {@code CommandType} class defines constants representing different
 * command actions a player can perform on their pet.
 *
 * Each command is mapped to a unique integer value used throughout the system
 * to enable, disable, and trigger pet actions like feeding, gifting, and exercising.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class CommandType {

    private final int FEED = 0;
    private final int BED = 1;
    private final int VET = 2;
    private final int PLAY = 3;
    private final int EXERCISE = 4;
    private final int GIFT = 5;

    /**
     * Returns the integer ID for the feed command.
     *
     * @return 0
     */
    public int getFEED() {
        return FEED;
    }

    /**
     * Returns the integer ID for the gift command.
     *
     * @return 1
     */
    public int getGIFT() {
        return GIFT;
    }

    /**
     * Returns the integer ID for the put-to-bed command.
     *
     * @return 2
     */
    public int getBED() {
        return BED;
    }

    /**
     * Returns the integer ID for the vet command.
     *
     * @return 3
     */
    public int getVET() {
        return VET;
    }

    /**
     * Returns the integer ID for the play command.
     *
     * @return 4
     */
    public int getPLAY() {
        return PLAY;
    }

    /**
     * Returns the integer ID for the exercise command.
     *
     * @return 5
     */
    public int getEXERCISE() {
        return EXERCISE;
    }
}
