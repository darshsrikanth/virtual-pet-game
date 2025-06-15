package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class sleepStateTest {

    private PetStub pet;
    private CommandsStub commands;
    private sleepState state;

    // fake Commands class just for testing
    static class CommandsStub extends Commands {
        boolean[] disabled = new boolean[6];
        boolean[] enabled = new boolean[6];
        boolean vitalsUpdated = false;

        public CommandsStub(Pet pet) {
            super(pet);
        }

        @Override
        public void disableCommand(int command) {
            if (command >= 0 && command < 6) {
                disabled[command] = true;
                enabled[command] = false;
            }
        }

        @Override
        public void enableCommand(int command) {
            if (command >= 0 && command < 6) {
                enabled[command] = true;
                disabled[command] = false;
            }
        }

        public boolean isDisabled(int command) {
            return command >= 0 && command < 6 && disabled[command];
        }

        @Override
        public void updateVitals() {
            vitalsUpdated = true;
        }
    }

    // fake Pet class just for testing sleep
    static class PetStub extends Pet {
        private int sleep = 50;
        private int health = 100;
        private final CommandsStub stubbedCommands;

        public PetStub() {
            super("Sleepy", new Animal() {
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

        @Override
        public int getSleep() {
            return sleep;
        }

        @Override
        public void setSleep(int value) {
            this.sleep = value;
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public void setHealth(int value) {
            this.health = value;
        }
    }

    @BeforeEach
    void setUp() {
        // start a new sleepy pet before each test
        pet = new PetStub();
        commands = (CommandsStub) pet.getCommands();
        state = new sleepState(pet);
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
        // make sure disabling a command works
        state.disableCommand(2);
        assertTrue(commands.isDisabled(2));
    }

    @Test
    void enableCommandTest() {
        // make sure enabling a command works
        state.enableCommand(4);
        assertFalse(commands.isDisabled(4));
    }

    @Test
    void enableStateTest() {
        // sleep state should disable all commands
        state.enableState();
        for (int i = 0; i <= 5; i++) {
            assertTrue(commands.isDisabled(i));
        }
    }

    @Test
    void increaseSleepTest1() {
        // increase sleep from less than 100
        pet.setSleep(90);
        state.increaseSleep();
        assertEquals(91, pet.getSleep());
        assertTrue(commands.vitalsUpdated);
    }

    @Test
    void increaseSleepTest2() {
        // should stay at 100 if already max
        pet.setSleep(100);
        state.increaseSleep();
        assertEquals(100, pet.getSleep());
        assertTrue(commands.vitalsUpdated);
    }

    @Test
    void decreaseHealthTest() {
        // health should go down by 25
        pet.setHealth(80);
        state.decreaseHealth();
        assertEquals(55, pet.getHealth());
    }

    @Test
    void getStateTypeTest() {
        // sleep state should return type 2
        assertEquals(2, state.getStateType());
    }
}
