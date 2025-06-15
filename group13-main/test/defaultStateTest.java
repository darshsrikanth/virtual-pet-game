package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class defaultStateTest {

    private PetStub pet;
    private CommandsStub commands;
    private defaultState state;

    // stub for Commands class
    static class CommandsStub extends Commands {
        boolean[] disabled = new boolean[6];
        boolean[] enabled = new boolean[6];

        public CommandsStub(Pet pet) {
            super(pet);
        }

        @Override
        public void disableCommand(int command) {
            if (command >= 0 && command <= 5) {
                disabled[command] = true;
                enabled[command] = false;
            }
        }

        @Override
        public void enableCommand(int command) {
            if (command >= 0 && command <= 5) {
                enabled[command] = true;
                disabled[command] = false;
            }
        }

        public boolean isDisabled(int command) {
            return disabled[command];
        }

        public boolean isEnabled(int command) {
            return enabled[command];
        }
    }

    // stub for Pet class
    static class PetStub extends Pet {
        private final CommandsStub stubbedCommands;

        public PetStub() {
            super("Normal", new Animal() {
                public int getHungerDecay() { return 0; }
                public int getSleepDecay() { return 0; }
                public int getHappinessDecay() { return 0; }
                public String getAnimalType() { return "stub"; }
            });
            stubbedCommands = new CommandsStub(this);
        }

        @Override
        public Commands getCommands() {
            return stubbedCommands;
        }
    }

    @BeforeEach
    void setUp() {
        pet = new PetStub();
        commands = (CommandsStub) pet.getCommands();
        state = new defaultState(pet);
    }

    @AfterEach
    void tearDown() {
        pet = null;
        commands = null;
        state = null;
    }

    @Test
    void disableCommand() {
        // checking that the command gets disabled
        state.disableCommand(2);
        assertTrue(commands.isDisabled(2));
        assertFalse(commands.isEnabled(2));
    }

    @Test
    void enableCommand() {
        // make sure enableCommand actually flips the state
        state.enableCommand(3);
        assertTrue(commands.isEnabled(3));
        assertFalse(commands.isDisabled(3));
    }

    @Test
    void enableState() {
        // enabling the full state should flip all commands to enabled
        state.enableState();
        for (int i = 0; i <= 5; i++) {
            assertTrue(commands.isEnabled(i));
        }
    }

    @Test
    void getStateType() {
        // default state should return 5
        assertEquals(5, state.getStateType());
    }
}
