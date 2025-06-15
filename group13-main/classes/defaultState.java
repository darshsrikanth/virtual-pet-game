package classes;

/**
 * The {@code defaultState} class represents the normal state of the pet.
 * In this state, all commands are enabled and the pet is not experiencing
 * hunger, sleepiness, anger, or death.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class defaultState implements State {
	private Pet petMood;
	private Commands controlPet;

	/**
	 * Constructs the default state with a reference to the pet and its command controller.
	 *
	 * @param petMood the current pet
	 */
	public defaultState(Pet petMood) {
		this.petMood = petMood;
		this.controlPet = petMood.getCommands();
	}

	/**
	 * Disables the given command.
	 *
	 * @param command the command to disable
	 */
	public void disableCommand(int command) {
		controlPet.disableCommand(command);
	}

	/**
	 * Enables the given command.
	 *
	 * @param command the command to enable
	 */
	public void enableCommand(int command) {
		controlPet.enableCommand(command);
	}

	/**
	 * Enables all commands and logs that the pet has returned to its normal state.
	 */
	public void enableState() {
		for (int i = 0; i <= 5; i++) {
			enableCommand(i);
		}
	}

	public int getStateType(){
		return 5;
	}

}
