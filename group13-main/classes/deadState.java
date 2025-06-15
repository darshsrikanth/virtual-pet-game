package classes;

/**
 * The {@code deadState} class represents the pet being dead.
 * In this state, no interactions are allowed and all commands are disabled.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class deadState implements State {
	private Pet petMood;
	private Commands controlPet;

	/**
	 * Constructs the dead state with a reference to the pet and its command controller.
	 *
	 * @param petMood the current pet
	 */
	public deadState(Pet petMood) {
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
	 * Activates the dead state by disabling all possible pet commands.
	 */
	public void enableState() {
		for (int i = 0; i <= 5; i++) {
			controlPet.disableCommand(i);
		}
	}

	public int getStateType(){
		return 1;
	}

}
