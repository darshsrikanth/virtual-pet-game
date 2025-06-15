package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class deadStateTest {

    private PetStub pet;
    private CommandsStub commands;
    private deadState state;

    // fake Commands class so we can check what gets enabled or disabled
    static class CommandsStub extends Commands {
        private final boolean[] disabled = new boolean[6];
        private final boolean[] enabled = new boolean[6];

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

    // fake Pet class just for testing this state
    static class PetStub extends Pet {
        private final CommandsStub stubbedCommands;

        public PetStub() {
            super("DeadPet", new Animal() {
                public int getHungerDecay() { return 0; }
                public int getSleepDecay() { return 0; }
                public int getHappinessDecay() { return 0; }
                public String getAnimalType() { return "ghost"; }
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
        // start fresh before every test
        pet = new PetStub();
        commands = (CommandsStub) pet.getCommands();
        state = new deadState(pet);
    }

    @AfterEach
    void tearDown() {
        // clean up after each test
        pet = null;
        commands = null;
        state = null;
    }

    @Test
    void disableCommandTest() {
        // make sure the command gets disabled
        state.disableCommand(2);
        assertTrue(commands.isDisabled(2));
        assertFalse(commands.isEnabled(2));
    }

    @Test
    void enableCommandTest() {
        // make sure the command gets enabled
        state.enableCommand(3);
        assertTrue(commands.isEnabled(3));
        assertFalse(commands.isDisabled(3));
    }

    @Test
    void enableStateTest() {
        // when the state is enabled, all commands should be disabled
        state.enableState();
        for (int i = 0; i <= 5; i++) {
            assertTrue(commands.isDisabled(i));
        }
    }

    @Test
    void getStateTypeTest() {
        // deadState should return 1
        assertEquals(1, state.getStateType());
    }
}
