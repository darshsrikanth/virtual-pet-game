package classes;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code Commands} class handles all player interactions with a pet.
 * It manages which commands are currently enabled, and executes logic for
 * feeding, playing, gifting, exercising, sleeping, and vet visits.
 *
 * It also monitors and updates the pet's vital statistics and changes states
 * based on thresholds (e.g., sleep, hunger, happiness, health).
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class Commands {

    private Pet controlPet;
    private boolean feedEnabled = true;
    private boolean giftEnabled = true;
    private boolean bedEnabled = true;
    private boolean vetEnabled = true;
    private boolean playEnabled = true;
    private boolean exerciseEnabled = true;

    CommandType commandType = new CommandType();
    StatType statType = new StatType();
    GiftItem giftItem;
    FoodItem foodItem;

    /**
     * Constructs a new Commands controller for the specified pet.
     *
     * @param controlPet the pet this Commands object will manage
     */
    public Commands(Pet controlPet) {
        this.controlPet = controlPet;
    }

    /**
     * Checks if a command is currently disabled.
     *
     * @param command the command ID
     * @return {@code true} if disabled, {@code false} if enabled
     */
    public boolean isDisabled(int command) {
        if (command == commandType.getFEED()) return !feedEnabled;
        if (command == commandType.getGIFT()) return !giftEnabled;
        if (command == commandType.getBED()) return !bedEnabled;
        if (command == commandType.getVET()) return !vetEnabled;
        if (command == commandType.getPLAY()) return !playEnabled;
        if (command == commandType.getEXERCISE()) return !exerciseEnabled;
        return true;
    }

    /**
     * Returns the value of the specified stat from the pet.
     *
     * @param stat the stat type (e.g. health, hunger)
     * @return the current stat value
     */
    public int checkStat(int stat) {
        if (stat == statType.getHEALTH())
            return controlPet.getHealth();
        else if (stat == statType.getHUNGER())
            return controlPet.getHunger();
        else if (stat == statType.getSLEEP())
            return controlPet.getSleep();
        else if (stat == statType.getHAPPINESS())
            return controlPet.getHappiness();
        else return -1;
    }

    /**
     * Disables the specified command so the player can no longer use it.
     *
     * @param command the command ID to disable
     */
    public void disableCommand(int command) {
        if (command == commandType.getFEED()) feedEnabled = false;
        if (command == commandType.getGIFT()) giftEnabled = false;
        if (command == commandType.getBED()) bedEnabled = false;
        if (command == commandType.getVET()) vetEnabled = false;
        if (command == commandType.getPLAY()) playEnabled = false;
        if (command == commandType.getEXERCISE()) exerciseEnabled = false;
    }

    /**
     * Enables the specified command so the player can use it.
     *
     * @param command the command ID to enable
     */
    public void enableCommand(int command) {
        if (command == commandType.getFEED()) feedEnabled = true;
        if (command == commandType.getGIFT()) giftEnabled = true;
        if (command == commandType.getBED()) bedEnabled = true;
        if (command == commandType.getVET()) vetEnabled = true;
        if (command == commandType.getPLAY()) playEnabled = true;
        if (command == commandType.getEXERCISE()) exerciseEnabled = true;
    }

    /**
     * Feeds the pet with a food item based on the player's choice.
     *
     * @param foodNum the selected food item (0=carrot, 1=Shrimp, 2=fish)
     */
    public void feed(int foodNum) {
        if (feedEnabled) {
            if (foodNum == 0) {

                foodItem = new Carrot();
                if (foodItem.getQuantity() > -1) {
                    foodEffect(foodItem);
                }
            } else if (foodNum == 1) {
                foodItem = new Shrimp();
                if (foodItem.getQuantity() > -1) {
                    foodEffect(foodItem);
                }
            } else if (foodNum == 2) {
                foodItem = new Fish();
                if (foodItem.getQuantity() > -1) {
                    foodEffect(foodItem);
                }
            }
        }
    }

    /**
     * Gives a gift to the pet and increases its happiness based on the item.
     *
     * @param giftNum the selected gift (0=Coral, 1=Message, 2=Pearl)
     */
    public void giveGift(int giftNum) {
        if (giftEnabled) {
            if (giftNum == 0) {
                giftItem = new Coral();
                controlPet.setHappiness(giftItem.getHappyEffective() + controlPet.getHappiness());
            } else if (giftNum == 1) {
                giftItem = new MessageInBottle();
                controlPet.setHappiness(controlPet.getHappiness() + giftItem.getHappyEffective());
            } else if (giftNum == 2) {
                giftItem = new Pearl();
                controlPet.setHappiness(controlPet.getHappiness() + giftItem.getHappyEffective());
            }
        }
    }

    /**
     * Sends the pet to bed and triggers the sleep state.
     */
    public void putToBed() {
        if (bedEnabled) {
            controlPet.changeState(new sleepState(controlPet));

            disableCommand(0);
            disableCommand(1);
            disableCommand(2);
            disableCommand(3);
            disableCommand(4);
            disableCommand(5);
            Timer timer = new Timer();
            TimerTask petRest = new TimerTask(){
                @Override public void run(){
                    controlPet.setSleep(controlPet.getSleep() + 1);

                    if (controlPet.getSleep() > 99){
                        controlPet.changeState(new defaultState(controlPet));
                        enableCommand(0);
                        enableCommand(1);
                        enableCommand(2);
                        enableCommand(3);
                        enableCommand(4);
                        enableCommand(5);
                        timer.cancel();
                    }
                }
            };
            timer.schedule(petRest, 0, 1000);
        }
    }

    /**
     * Takes the pet to the vet, restoring 50 health and triggering a cooldown.
     */
    public void vet() {
        if (vetEnabled) {
            controlPet.setHealth(controlPet.getHealth() + 50);
            disableCommand(2);
        }
    }

    /**
     * Plays with the pet, restoring health and triggering a cooldown.
     */
    public void play() {
        if (playEnabled) {
            controlPet.setHappiness(controlPet.getHappiness() + 25);
            disableCommand(3);
        }
    }

    /**
     * Exercises the pet, increasing health while reducing hunger and sleep.
     */
    public void exercise() {
        if (exerciseEnabled) {
            controlPet.setSleep(controlPet.getSleep() - 10);
            controlPet.setHunger(controlPet.getHunger() - 10);
            controlPet.setHealth(controlPet.getHealth() + 25);
        }
    }

    /**
     * Applies the effects of a specific food item based on pet type.
     *
     * @param foodItem the food item to apply
     */
    public void foodEffect(FoodItem foodItem) {
        if (controlPet.getPetType() instanceof Shark){
            controlPet.setHunger(controlPet.getHunger() + foodItem.sharkEffective);
        }
        else if (controlPet.getPetType() instanceof Pufferfish){
            controlPet.setHunger(controlPet.getHunger() + foodItem.pufferEffective);
        }
        else {
            controlPet.setHunger(controlPet.getHunger() + foodItem.axolEffective);
        }
    }

    /**
     * Decreases the pet's happiness based on decay rate.
     * If in the hungry state, happiness decays 25% faster.
     */
    public void declineHappiness() {

        int decay = controlPet.getPetType().getHappinessDecay();
        int happinessLoss;

        if (controlPet.getState() instanceof hungryState) {
            happinessLoss = (int)(decay * 1.25);
        } else {
            happinessLoss = decay;
        }

        controlPet.setHappiness(controlPet.getHappiness() - happinessLoss);
    }

    /**
     * Updates the pet's vital statistics and handles transitions
     * between states such as sleep, angry, hungry, or dead.
     */
    public void updateVitals() {
        if (controlPet.getHealth() <= 0 && !(controlPet.getState() instanceof deadState)) {
            controlPet.changeState(new deadState(controlPet));
            return;
        }

        else if (controlPet.getSleep() <= 0 && !(controlPet.getState() instanceof sleepState)) {
            controlPet.changeState(new sleepState(controlPet));
            return;
        }

        else if (controlPet.getSleep() < 100 && controlPet.getState() instanceof sleepState) {
            ((sleepState) controlPet.getState()).increaseSleep();
        }

        else if (controlPet.getHappiness() <= 0 && !(controlPet.getState() instanceof angryState)) {
            controlPet.changeState(new angryState(controlPet));
        }

        else if (controlPet.getHunger() <= 0 && !(controlPet.getState() instanceof hungryState)) {
            controlPet.changeState(new hungryState(controlPet));
            return;
        }

        if (controlPet.getState() instanceof angryState) {
            ((angryState) controlPet.getState()).checkHappinessRecovery();
        }

        if (controlPet.getState() instanceof hungryState) {
            ((hungryState) controlPet.getState()).decreaseHealth();
            ((hungryState) controlPet.getState()).decreaseHappiness();
        }
    }

    public void declineHunger(){
        Timer timer = new Timer();
        TimerTask loopHunger = new TimerTask(){
            @Override public void run(){
                controlPet.setHealth(controlPet.getHealth() - 10);
                if (controlPet.getHunger() > 0){
                    timer.cancel();
                }
            }
        };
        timer.schedule(loopHunger, 0, 120000);

    }
}
