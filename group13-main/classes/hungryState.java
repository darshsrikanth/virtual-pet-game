package classes;

/**
 * The {@code hungryState} class represents the pet being in a hungry condition.
 * In this state, all commands are enabled, but the pet's happiness decays faster,
 * and health is reduced every cycle until hunger is addressed.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class hungryState implements State {

	private Pet petMood;
	private Commands controlPet;

	/**
	 * Constructs the hungry state for the pet.
	 *
	 * @param petMood the pet entering the hungry state
	 */
	public hungryState(Pet petMood) {
		this.petMood = petMood;
		this.controlPet = petMood.getCommands();
	}

	/**
	 * Disables a specific command.
	 *
	 * @param command the command to disable
	 */
	public void disableCommand(int command) {
		controlPet.disableCommand(command);
	}

	/**
	 * Enables a specific command.
	 *
	 * @param command the command to enable
	 */
	public void enableCommand(int command) {
		controlPet.enableCommand(command);
	}

	/**
	 * Enables all commands and outputs a message.
	 */
	public void enableState() {
		for (int i = 0; i <= 5; i++) {
			controlPet.enableCommand(i);
		}
	}

	/**
	 * Decreases pet's health by 10 points due to hunger.
	 */
	public void decreaseHealth() {
		int newHealth = petMood.getHealth() - 10;
		petMood.setHealth(newHealth);
	}

	/**
	 * Applies the increased happiness decay effect.
	 */
	public void decreaseHappiness() {
		controlPet.declineHappiness();
	}

	public int getStateType(){
		return 4;
	}
}
