package classes;

/**
 * The {@code Pet} class defines a virtual pet's core data and behavior.
 * It contains its statistics, current state, and available commands.
 *
 * The pet transitions between different mood states and is affected by
 * hunger, sleep, health, and happiness values.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class Pet {
    private Animal petType;
    private String petName;
    private int health;
    private int hunger;
    private int sleep;
    private int happiness;
    private State mood;
    private Commands commands;

    /**
     * Constructs a pet with default stats (all set to 100).
     *
     * @param petName  the name of the pet
     * @param petType  the animal type of the pet
     */
    public Pet(String petName, Animal petType) {
        this.petName = petName;
        this.petType = petType;
        this.health = 100;
        this.hunger = 100;
        this.sleep = 100;
        this.happiness = 100;
        this.commands = new Commands(this);
    }

    /**
     * Constructs a pet with custom stats.
     *
     * @param petName the name of the pet
     * @param petType the animal type of the pet
     * @param stats   the array of stats: health, hunger, sleep, happiness
     */
    public Pet(String petName, Animal petType, int[] stats) {
        this.petName = petName;
        this.petType = petType;
        this.health = stats[0];
        this.hunger = stats[1];
        this.sleep = stats[2];
        this.happiness = stats[3];
        this.commands = new Commands(this);
        this.mood = new defaultState(this);
    }

    /**
     * Changes the pet's state and triggers state behavior.
     *
     * @param mood the new state to transition into
     */
    public void changeState(State mood) {
        this.mood = mood;

        if (mood instanceof defaultState) {
            mood = new defaultState(this);
            mood.enableState();
        } else if (mood instanceof angryState) {
            mood = new angryState(this);
            mood.enableState();
        } else if (mood instanceof sleepState) {
            mood = new sleepState(this);
            mood.enableState();
        } else if (mood instanceof hungryState) {
            mood = new hungryState(this);
            mood.enableState();
        } else if (mood instanceof deadState) {
            mood = new deadState(this);
            mood.enableState();
        }
    }

    /**
     * Returns the current state of the pet.
     *
     * @return the current mood/state
     */
    public State getState() {
        return mood;
    }

    /**
     * Gets the pet's name.
     *
     * @return the name of the pet
     */
    public String getName() {
        return petName;
    }

    /**
     * Gets the type of animal this pet is.
     *
     * @return the pet type (Animal)
     */
    public Animal getPetType() {
        return petType;
    }

    /**
     * Gets the current health level of the pet.
     *
     * @return the health value (0–100)
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the current hunger level of the pet.
     *
     * @return the hunger value (0–100)
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Gets the current sleep level of the pet.
     *
     * @return the sleep value (0–100)
     */
    public int getSleep() {
        return sleep;
    }

    /**
     * Gets the current happiness level of the pet.
     *
     * @return the happiness value (0–100)
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Returns the commands manager tied to this pet.
     *
     * @return the Commands object for the pet
     */
    public Commands getCommands() {
        return commands;
    }

    /**
     * Sets the pet's health to a specified value.
     *
     * @param health the new health value (0–100)
     */
    public void setHealth(int health) {
        if (health <= 0){
            this.health = 0;
        }
        else if (health >= 100){
            this.health = 100;
        }
        else {
            this.health = health;
        }
    }

    /**
     * Sets the pet's hunger to a specified value.
     *
     * @param hunger the new hunger value (0–100)
     */
    public void setHunger(int hunger) {
        if (hunger <= 0){
            this.hunger = 0;
        }
        else if (hunger >= 100){
            this.hunger = 100;
        }
        else {
            this.hunger = hunger;
        }
    }

    /**
     * Sets the pet's sleep to a specified value.
     *
     * @param sleep the new sleep value (0–100)
     */
    public void setSleep(int sleep) {
        if (sleep <= 0){
            this.sleep = 0;
        }
        else if (sleep >= 100){
            this.sleep = 100;
        }
        else {
            this.sleep = sleep;
        }
    }

    /**
     * Sets the pet's happiness to a specified value.
     *
     * @param happiness the new happiness value (0–100)
     */
    public void setHappiness(int happiness) {
        if (happiness <= 0){
            this.happiness = 0;
        }
        else if (happiness >= 100){
            this.happiness = 100;
        }
        else {
            this.happiness = happiness;
        }
    }

    /**
     * Prints the status of all available commands for the pet.
     *
     * Each command from 0 to 5 is checked, and a message is printed
     * indicating whether it is currently enabled or disabled.
     * Useful for debugging and verifying state-based command availability.
     *
     * Command indexes:
     * 0 - Feed
     * 1 - Gift
     * 2 - Bed
     * 3 - Vet
     * 4 - Play
     * 5 - Exercise
     */
    public void printCommands() {
        System.out.println("----- Printing Commands -----");
        for(int i = 0; i <= 5; i++) {
            System.out.println(commands.isDisabled(i) ? i + " is Disabled" : i + " is Enabled");
        }
    }


}

