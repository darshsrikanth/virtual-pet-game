package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class hungryStateTest {

    private PetStub pet;
    private CommandsStub commands;
    private hungryState state;

    // stubbed Commands that tracks enable/disable calls and happiness decay
    static class CommandsStub extends Commands {
        boolean[] enabled = new boolean[6];
        boolean[] disabled = new boolean[6];
        boolean happinessDeclined = false;

        public CommandsStub(Pet pet) {
            super(pet);
        }

        @Override
        public void enableCommand(int command) {
            enabled[command] = true;
            disabled[command] = false;
        }

        @Override
        public void disableCommand(int command) {
            disabled[command] = true;
            enabled[command] = false;
        }

        @Override
        public void declineHappiness() {
            happinessDeclined = true;
        }

        public boolean isEnabled(int command) {
            return enabled[command];
        }

        public boolean isDisabled(int command) {
            return disabled[command];
        }
    }

    // stubbed Pet that lets us manipulate health
    static class PetStub extends Pet {
        private int health = 100;
        private final CommandsStub stubbedCommands;

        public PetStub() {
            super("Hungry", new Animal() {
                public int getHungerDecay() { return 0; }
                public int getSleepDecay() { return 0; }
                public int getHappinessDecay() { return 0; }
                public String getAnimalType() { return "stub"; }
            });
            stubbedCommands = new CommandsStub(this);
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public void setHealth(int health) {
            this.health = health;
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
        state = new hungryState(pet);
    }

    @AfterEach
    void tearDown() {
        pet = null;
        commands = null;
        state = null;
    }

    @Test
    void disableCommand() {
        // testing if it actually disables a command
        state.disableCommand(3);
        assertTrue(commands.isDisabled(3));
        assertFalse(commands.isEnabled(3));
    }

    @Test
    void enableCommand() {
        // enabling one and checking if it's flipped
        state.enableCommand(1);
        assertTrue(commands.isEnabled(1));
        assertFalse(commands.isDisabled(1));
    }

    @Test
    void enableState() {
        // should enable all 6 commands
        state.enableState();
        for (int i = 0; i <= 5; i++) {
            assertTrue(commands.isEnabled(i));
        }
    }

    @Test
    void decreaseHealth() {
        // simulating hunger health penalty
        pet.setHealth(80);
        state.decreaseHealth();
        assertEquals(70, pet.getHealth());
    }

    @Test
    void decreaseHappiness() {
        // making sure it calls the decay method
        state.decreaseHappiness();
        assertTrue(commands.happinessDeclined);
    }

    @Test
    void getStateType() {
        // hungry state is mapped to type 4
        assertEquals(4, state.getStateType());
    }
}
