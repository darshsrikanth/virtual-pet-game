package classes;

/**
 * Class that represents a save file and the data contained within it.
 *
 * <p>This class provides the methods to retrieve the data from a save instance. A save object is generated
 * from a .json file with all the save data within.</p>
 *
 * @version 1.0
 * @author Everett Guyea
 */
public class save {

    private String name;
    private int petType;
    private int health;
    private int fatigue;
    private int hunger;
    private int happiness;
    private int playerScore;
    private int sandDollars;
    // {Carrot, Shrimp, Fish}
    private int[] foodItems;
    // {Coral, MessageInBottle, Pearl}
    private int[] giftItems;
    // {
    private boolean[][] decorItems;


    /**
     * Constructor for save class. Initializes instance variables.
     *
     * @param name  The name of the pet.
     * @param petType The type of the pet.
     * @param health The pets health vital.
     * @param fatigue The pets fatigue.
     * @param hunger The pets hunger.
     * @param happiness The pets happiness.
     * @param playerScore The players score.
     * @param sandDollars The players sand dollars.
     * @param foodItems The players owned food items and their quantities.
     * @param giftItems The players owned gift items and their quantities.
     * @param decorItems The players owned decor items.
     */
    public save(String name, int petType, int health, int fatigue, int hunger, int happiness, int playerScore,
                int sandDollars, int[] foodItems, int[] giftItems, boolean[][] decorItems) {
        this.name = name;
        this.petType = petType;
        this.health = health;
        this.fatigue = fatigue;
        this.hunger = hunger;
        this.happiness = happiness;
        this.playerScore = playerScore;
        this.sandDollars = sandDollars;
        this.foodItems = foodItems;
        this.giftItems = giftItems;
        this.decorItems = decorItems;
    }

    /**
     * A constructor to create a save object using a gameInstance.
     *
     * @param gameInstance A gameInstance object to create a save object from.
     */
    public save(GameInstance gameInstance) {
        this.name = gameInstance.getName();
        if (gameInstance.getPetType().getAnimalType().equals("shark")) {this.petType = 0;}
        else if (gameInstance.getPetType().getAnimalType().equals("pufferfish")) {this.petType = 1;}
        else {this.petType = 2;}
        this.health = gameInstance.getHealth();
        this.fatigue = gameInstance.getFatigue();
        this.hunger = gameInstance.getHunger();
        this.happiness = gameInstance.getHappiness();
        this.playerScore = gameInstance.getPlayerScore();
        this.sandDollars = gameInstance.getSandDollars();
        this.foodItems = gameInstance.getFoodItems();
        this.giftItems = gameInstance.getGiftItems();
        this.decorItems = gameInstance.getDecorItems();
    }

    /**
     * Method to revive a deceased pet that sets its health to 100% if it is dead
     *
     * @return A boolean value that is true if the pet was revived and false if the pet is already alive.
     */
    public boolean revivePet() {
        // If the pet is dead, sets all pet vitals to 100 and returns true.
        if (health == 0) {
            health = 100;
            fatigue = 100;
            hunger = 100;
            happiness = 100;

            return true;
        }
        else {  // If the pet is alive, returns false
            return false;
        }
    }

    /**
     * @return An integer defining the type of pet.
     */
    public int getPetType() {
        return petType;
    }

    /**
     * @return The name of the pet.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The pets health vital.
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return The pets fatigue vital.
     */
    public int getFatigue() {
        return fatigue;
    }

    /**
     * @return The pets hunger vital.
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * @return The happiness vital.
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * @return The players score.
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * @return The players amount of sand dollars.
     */
    public int getSandDollars() {
        return sandDollars;
    }

    /**
     * @return An integer array of the food items owned in a respective order where the numbers represent the quantity.
     */
    public int[] getFoodItems() {
        return foodItems;
    }

    /**
     * @return An integer array of the gift items owned in a respective order where the numbers represent the quantity.
     */
    public int[] getGiftItems() {
        return giftItems;
    }

    /**
     * @return A boolean array in a respective order where the value represents if the decor item is owned.
     */
    public boolean[][] getDecorItems() {
        return decorItems;
    }
}
