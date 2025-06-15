package classes;

/**
 * The {@code sleepState} class represents the pet being asleep.
 * While in this state, all commands are disabled. Sleep increases over time,
 * and once max sleep is reached, the pet wakes up and returns to a normal state.
 * Health decreases if sleep was triggered due to exhaustion.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public class sleepState implements State {
	private Pet petMood;
	private Commands controlPet;

	/**
	 * Constructs the sleep state for the given pet.
	 *
	 * @param petMood the pet entering the sleep state
	 */
	public sleepState(Pet petMood) {
		this.petMood = petMood;
		this.controlPet = petMood.getCommands();
	}

	/**
	 * Disables the given command while in sleep state.
	 *
	 * @param command the command to disable
	 */
	public void disableCommand(int command) {
		controlPet.disableCommand(command);
	}

	/**
	 * Enables the given command while in sleep state.
	 *
	 * @param command the command to enable
	 */
	public void enableCommand(int command) {
		controlPet.enableCommand(command);
	}

	/**
	 * Puts the pet into sleep mode and disables all commands.
	 */
	public void enableState() {
		for (int i = 0; i <= 5; i++) {
			disableCommand(i);
		}
	}

	/**
	 * Gradually increases sleep by 1 point per second until max.
	 * Once sleep reaches 100, pet wakes up and returns to previous state.
	 */
	public void increaseSleep() {
		int current = petMood.getSleep();
		if (current < 100) {
			petMood.setSleep(current + 1);
			System.out.println(petMood.getSleep());
			controlPet.updateVitals(); // GUI should reflect live updates
		} else {
			controlPet.updateVitals();
		}
	}

	/**
	 * Applies a health penalty for entering sleep state due to exhaustion.
	 */
	public void decreaseHealth() {
		int newHealth = petMood.getHealth() - 25;
		petMood.setHealth(newHealth);
		// GUI should reflect health drop immediately
	}

	public int getStateType(){
		return 2;
	}
}
