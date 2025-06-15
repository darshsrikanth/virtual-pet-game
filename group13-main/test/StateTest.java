package test;

import classes.Animal;
import classes.Pet;
import classes.State;
import classes.defaultState;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    private Pet pet;
    private State state;

    //animal stub for Pet
    static class TestAnimal implements Animal {
        public int getHungerDecay() { return 0; }
        public int getSleepDecay() { return 0; }
        public int getHappinessDecay() { return 0; }
        public String getAnimalType() { return "test"; }
    }

    @BeforeEach
    void setUp() {
        pet = new Pet("Testy", new TestAnimal());
        state = new defaultState(pet);
    }

    @AfterEach
    void tearDown() {
        pet = null;
        state = null;
    }

    @Test
    void disableCommandTest() {
        pet.getCommands().enableCommand(0);
        assertFalse(pet.getCommands().isDisabled(0));

        state.disableCommand(0);
        assertTrue(pet.getCommands().isDisabled(0));
    }

    @Test
    void enableCommandTest() {
        pet.getCommands().disableCommand(1);
        assertTrue(pet.getCommands().isDisabled(1));

        state.enableCommand(1);
        assertFalse(pet.getCommands().isDisabled(1));
    }

    @Test
    void enableStateTest() {
        // disable all commands firstly
        for (int i = 0; i <= 5; i++) {
            pet.getCommands().disableCommand(i);
            assertTrue(pet.getCommands().isDisabled(i));
        }

        state.enableState(); // should enable all commands

        for (int i = 0; i <= 5; i++) {
            assertFalse(pet.getCommands().isDisabled(i));
        }
    }
}
