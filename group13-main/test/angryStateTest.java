package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class angryStateTest {

    private PetStub pet;
    private CommandsStub commands;
    private angryState state;

    // setting up a fake Commands class to help track what gets enabled/disabled
    static class CommandsStub extends Commands {
        boolean[] enabled = new boolean[6];
        boolean[] disabled = new boolean[6];
        boolean vitalsUpdated = false;

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

        @Override
        public void updateVitals() {
            vitalsUpdated = true;
        }
    }

    // fake Pet to isolate testing
    static class PetStub extends Pet {
        private int happiness = 0;
        private final CommandsStub stubbedCommands;

        public PetStub() {
            super("Angry", new Animal() {
                public int getHungerDecay() { return 0; }
                public int getSleepDecay() { return 0; }
                public int getHappinessDecay() { return 0; }
                public String getAnimalType() { return "stub"; }
            });
            stubbedCommands = new CommandsStub(this);
        }

        @Override
        public int getHappiness() {
            return happiness;
        }

        public void setHappiness(int value) {
            this.happiness = value;
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
        state = new angryState(pet);
    }

    @AfterEach
    void tearDown() {
        pet = null;
        commands = null;
        state = null;
    }

    @Test
    void disableCommandTest() {
        // only commands other than 1 and 4 should be disabled
        state.disableCommand(0); // should be disabled
        state.disableCommand(1); // gift - should stay enabled
        state.disableCommand(4); // play - should stay enabled

        assertTrue(commands.isDisabled(0));
        assertFalse(commands.isDisabled(1));
        assertFalse(commands.isDisabled(4));
    }

    @Test
    void enableCommandTest() {
        // only 1 and 4 (gift and play) should be allowed to be enabled
        state.enableCommand(1); // gift
        state.enableCommand(4); // play
        state.enableCommand(2); // vet - should do nothing

        assertTrue(commands.isEnabled(1));
        assertTrue(commands.isEnabled(4));
        assertFalse(commands.isEnabled(2));
    }

    @Test
    void enableStateTest() {
        // angry state should disable everything except gift and play
        state.enableState();
        for (int i = 0; i <= 5; i++) {
            if (i == 1 || i == 4) {
                assertTrue(commands.isEnabled(i));
            } else {
                assertTrue(commands.isDisabled(i));
            }
        }
    }

    @Test
    void checkHappinessRecoveryTest() {
        // should only update vitals if happiness is 50 or more
        pet.setHappiness(45);
        state.checkHappinessRecovery();
        assertFalse(commands.vitalsUpdated);

        pet.setHappiness(50);
        state.checkHappinessRecovery();
        assertTrue(commands.vitalsUpdated);
    }

    @Test
    void getStateTypeTest() {
        // make sure it returns the correct ID for angry state
        assertEquals(3, state.getStateType());
    }
}
