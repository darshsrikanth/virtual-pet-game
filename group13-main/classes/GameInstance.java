package classes;

/**
 * Game Instance
 * <br><br>
 * The game instance object stores all relevant information necessary for loading or creating a new game save.
 * Variable petType is an integer corresponding to the type of animal
 * Variable petName is a string corresponding to the pet's name
 * Variables health, sleep, hunger and happiness store the pets' vital statistics.
 * Variable playerScore stores the player's current score.
 * Variable sandDollars stores the amount of sand dollars the player currently has available to spend.
 * Variable foodItems is an integer array of the quantities for each food item in the inventory.
 * Variable giftItems is an integer array of the quantities for each gift item in the inventory.
 * Variable decorItems is a 2D array of boolean pairs, indicating the owned and equipped status for each decor item.
 * Variable inventory stores an instance of the Inventory object.
 * Variable shop stores an instance of the Shop object.
 * Variable pet stores an instance of the Pet object.
 *
 * @version 1.1
 * @author Cadence Megan McGillicuddy
 * @author Everett Guyea
 */
public class GameInstance {
    private String name;
    private Animal petType;
    private int health;
    private int fatigue;
    private int hunger;
    private int happiness;
    private int playerScore;
    private int sandDollars;
    private int[] foodItems;
    private int[] giftItems;
    private boolean[][] decorItems;
    private Inventory inventory;
    private Shop shop;
    private Pet pet; // PET OBJECT DOESNT EXIST YET

    /**
     * GameInstance constructor. Creates a new GameInstance.
     *
     * @param petType Animal object indicating whether the pet is a shark, pufferfish or axolotl
     * @param name String indicating the pet's name
     * @param health integer indicating pet's current health
     * @param fatigue integer indicating pet's current sleepiness level
     * @param hunger integer indicating pet's current hunger level
     * @param happiness integer indicating pet's current happiness level
     * @param playerScore integer indicating the player's current score
     * @param sandDollars integer indicating the number of sand dollars the player currently has to spend
     * @param foodItems integer array with each element indicating the quantity of the food item currently at that index in player's inventory
     * @param giftItems integer array with each element indicating the quantity of the gift item currently at that index in player's inventory
     * @param decorItems 2-dimensional boolean array, with the first element in each pair indicating the corresponding decor item's owned status, and the second element indicating the corresponding decor item's equipped status
     */
    public GameInstance(int petType, String name, int health, int fatigue, int hunger, int happiness, int playerScore, int sandDollars, int[] foodItems, int[] giftItems, boolean[][] decorItems) {
        if (petType == 0) {this.petType = new Shark();}
        else if (petType == 1) {this.petType = new Pufferfish();}
        else {this.petType = new Axolotl();}
        this.name = name;
        this.health = health;
        this.fatigue = fatigue;
        this.hunger = hunger;
        this.happiness = happiness;
        this.playerScore = playerScore;
        this.sandDollars = sandDollars;
        this.foodItems = foodItems;
        this.giftItems = giftItems;
        this.decorItems = decorItems;
        this.inventory = new Inventory();
        this.shop = new Shop();

        //
        int[] stats = {health, hunger, fatigue, happiness};
        this.pet = new Pet(name, this.petType, stats);


        // nested loop sets up correct item quantities for player inventory
        for (int c = 0; c < 2; c++) { // c looping through the types of items in the inventory (food items first, then gift)
            for (int t = 0; t < 3; t++) { // t looping through each type of food item or each type of gift item
                if (c == 0) {
                    this.inventory.setItemQuantity(c, t, this.foodItems[t]); // setting each food item to the correct quantity
                } else {
                    this.inventory.setItemQuantity(c,t, this.giftItems[t]); // setting each gift item to the correct quantity
                }
            }
        }

        // loop sets up correct owned/equipped statuses for decor items through the shop object
        for (int d = 0; d < 3; d++) { // looping through each decor item
            if (this.decorItems[d][0]) { // if first element at the index of the corresponding decor item is true (owned)
                this.shop.buyDecor(d); // then buy the decor item to set its owned status to true
                if (this.decorItems[d][1]) { // if second element at the index of the corresponding decor item is true (equipped)
                    this.shop.equipItem(d); // then equip the item
                }
            }
        }
    }

    /**
     * Constructor for gameInstance object that uses the data within a save object.
     *
     * @param save A save object with information to create the gameInstance.
     */
    public GameInstance(save save) {
        if (save.getPetType() == 0) {this.petType = new Shark();}
        else if (save.getPetType() == 1) {this.petType = new Pufferfish();}
        else {this.petType = new Axolotl();}
        this.name = save.getName();
        this.health = save.getHealth();
        this.fatigue = save.getFatigue();
        this.hunger = save.getHunger();
        this.happiness = save.getHappiness();
        this.playerScore = save.getPlayerScore();
        this.sandDollars = save.getSandDollars();
        this.foodItems = save.getFoodItems();
        this.giftItems = save.getGiftItems();
        this.decorItems = save.getDecorItems();
        this.inventory = new Inventory();
        this.shop = new Shop();

        //
        int[] stats = {health, hunger,fatigue, happiness};
        this.pet = new Pet(name, this.petType, stats);


        // nested loop sets up correct item quantities for player inventory
        for (int c = 0; c < 2; c++) { // c looping through the types of items in the inventory (food items first, then gift)
            for (int t = 0; t < 3; t++) { // t looping through each type of food item or each type of gift item
                if (c == 0) {
                    this.inventory.setItemQuantity(c, t, this.foodItems[t]); // setting each food item to the correct quantity
                } else {
                    this.inventory.setItemQuantity(c,t, this.giftItems[t]); // setting each gift item to the correct quantity
                }
            }
        }

        // loop sets up correct owned/equipped statuses for decor items through the shop object
        for (int d = 0; d < 3; d++) { // looping through each decor item
            if (this.decorItems[d][0]) { // if first element at the index of the corresponding decor item is true (owned)
                this.shop.buyDecor(d); // then buy the decor item to set its owned status to true
                if (this.decorItems[d][1]) { // if second element at the index of the corresponding decor item is true (equipped)
                    this.shop.equipItem(d); // then equip the item
                }
            }
        }
    }

    /**
     * Buy an item and subtract the corresponding price from the player's sand dollars.
     * If the item purchased is a food or gift item, its quantity variable is incremented by one. If the
     * item purchased is a decor item, its isOwned variable is set to true.
     *
     * @param type integer indicating whether the item belongs to the food item, gift item or decor item array.
     * @param index integer representing the index of the item within its corresponding type array.
     * @return boolean true if the purchase was successful, false otherwise.
     */
    public boolean buyItem(int type, int index) {
        // price functionality
        if (this.shop.getItemPrice(type,index) <= this.sandDollars) { // if the price of the item is less than or equal to the player's current sand dollar balance
            this.sandDollars -= this.shop.getItemPrice(type, index); // decrementing the sand dollar balance by the item price
        } else { // otherwise if the player doesn't have enough sand dollars, returning false
            return false;
        }
        if (type == 0 || type == 1) { // if the item being bought is a food or gift item
            this.inventory.buyItem(type, index); // buy that specific item and return true
            return true;
        } else if (type == 2) { // if the item being bought is a decor item
            this.shop.buyDecor(index); // buy that item and return true
            return true;
        } else { // otherwise an incorrect type was entered, return false
            return false;
        }

    }

    /**
     * Equip a decor item.
     * Uses the shop's equipItem method to change its equipped variable to true.
     *
     * @param index integer representing the index of the item within the array of decor items in the shop object.
     */
    private void equipItem(int index) {
        this.shop.equipItem(index);
    }

    /**
     * Unequip a decor item.
     * Uses the shop's unequipItem method to change its equipped variable to false.
     *
     * @param index integer representing the index of the item within the array of decor items in the shop object.
     */
    private void unequipItem(int index) {
        this.shop.unequipItem(index);
    }

    /**
     * Retrieves the associated inventory item.
     * @return Inventory object containing player's items
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Retrieves the associated shop object.
     * @return Shop object containing player's decor items.
     */
    public Shop getShop() {
        return this.shop;
    }

    /**
     * Retrieves the associated pet.
     * @return Pet containing relevant information.
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Retrieves the associated player score.
     * @return integer indicating player score.
     */
    public int getPlayerScore() {
        return this.playerScore;
    }

    /**
     * Sets the associated player score to an indicated value.
     * @param score the amount to which the player score should be set.
     */
    public void setPlayerScore(int score) {
        if (score == 500) {
            this.playerScore = 500;
        } else if (score < 0) {
            this.playerScore = 0;
        } else {
            this.playerScore = score;
        }
    }

    /**
     * Retrieves the associated sand dollar balance.
     * @return integer indicating sand dollar balance.
     */
    public int getSandDollars() {
        return this.sandDollars;
    }

    /**
     * Sets the associated sand dollar balance based on the indicated amount.
     * @param dollars the amount to which the sand dollar balance should be set.
     */
    public void setSandDollars(int dollars) {
        this.sandDollars = dollars;
    }

    /**
     * Retrieves all necessary values from the active game and stores them in the gameInstance's corresponding variables.
     * Should be called when the game is saved to ensure all variables are updated.
     */
    public void setVariables() {
        this.health = this.pet.getHealth();
        this.fatigue = this.pet.getSleep();
        this.hunger = this.pet.getHunger();
        this.happiness = this.pet.getHappiness();

        for (int c = 0; c < 3; c++) {
            this.foodItems[c] = this.inventory.getFoodItemQuantity(c);
            this.giftItems[c] = this.inventory.getGiftItemQuantity(c);
            this.decorItems[c][0] = this.shop.decorOwnedStatus(c);
            this.decorItems[c][1] = this.shop.decorEquippedStatus(c);
        }
    }

    public String getName() {
        return name;
    }

    public Animal getPetType() {
        return petType;
    }

    public int getHealth() {
        return health;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int[] getFoodItems() {
        return foodItems;
    }

    public int[] getGiftItems() {
        return giftItems;
    }

    public boolean[][] getDecorItems() {
        return decorItems;
    }
}
