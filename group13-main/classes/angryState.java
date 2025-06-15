package classes;

/**
 * The {@code angryState} class represents the pet being angry.
 * In this state, the pet only responds to happiness-increasing commands (gift and play).
 * Other commands are disabled until the pet's happiness is restored.
 * When happiness reaches 50 or higher, the pet exits this state.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class angryState implements State {
	private Pet petMood;
	private Commands controlPet;

	/**
	 * Constructs the angry state with a reference to the pet and its command controller.
	 *
	 * @param petMood the current pet
	 */
	public angryState(Pet petMood) {
		this.petMood = petMood;
		this.controlPet = petMood.getCommands();
	}

	/**
	 * Disables commands unless they are gift or play.
	 *
	 * @param command the command type to disable
	 */
	public void disableCommand(int command) {
		if (command != 1 && command != 4) {
			controlPet.disableCommand(command);
		}
	}

	/**
	 * Enables commands only if they are gift or play.
	 *
	 * @param command the command type to enable
	 */
	public void enableCommand(int command) {
		if (command == 1 || command == 4) {
			controlPet.enableCommand(command);
		}
	}

	/**
	 * Activates the angry state by disabling all commands,
	 * except for gift and play, which are allowed.
	 */
	public void enableState() {
		for (int i = 0; i <= 5; i++) {
			disableCommand(i);
		}
		enableCommand(1); // gift
		enableCommand(4); // play
	}

	/**
	 * Checks whether the pet has recovered from the angry state.
	 * If happiness is 50 or more, it updates the vitals to potentially exit the state.
	 */
	public void checkHappinessRecovery() {
		if (petMood.getHappiness() >= 50) {
			controlPet.updateVitals();
		}
	}

	public int getStateType(){
		return 3;
	}
}
