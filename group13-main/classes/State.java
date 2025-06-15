package classes;

/**
 * The {@code State} interface defines the behavioral contract for
 * all mood-based states that a pet can be in (e.g., Sleep, Angry, Hungry, Dead).
 *
 * Each state can enable/disable commands and perform setup actions
 * upon entering the state via {@code enableState()}.
 *
 * Implementing classes should override the methods to apply their specific logic
 * to control pet behavior based on the current state.
 *
 * @author Darsh Srikanth
 * @version 1.0
 */
public interface State {

	/**
	 * Disables a specific command while in this state.
	 *
	 * @param command the integer value representing the command to disable
	 */
	void disableCommand(int command);

	/**
	 * Enables a specific command while in this state.
	 *
	 * @param command the integer value representing the command to enable
	 */
	void enableCommand(int command);

	/**
	 * Applies logic specific to entering the current state.
	 * Typically includes setting up command availability
	 * and triggering any animations or effects.
	 */
	void enableState();

	/**
	 * Tells what number state the state is
	 *
	 * @return the integer value representing the state number
	 */
	int getStateType();

}
