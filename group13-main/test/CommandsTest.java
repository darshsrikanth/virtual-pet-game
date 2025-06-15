package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    private Commands commands;
    private PetStub pet;

    // basic pet stub for testing
    static class PetStub extends Pet {
        private int health = 50;
        private int hunger = 50;
        private int sleep = 50;
        private int happiness = 50;
        private State currentState;

        public PetStub() {
            super("TestPet", new Animal() {
                public int getHungerDecay() { return 5; }
                public int getSleepDecay() { return 5; }
                public int getHappinessDecay() { return 10; }
                public String getAnimalType() { return "axolotl"; }
            });
        }

        @Override public int getHealth() { return health; }
        @Override public void setHealth(int h) { health = h; }
        @Override public int getHunger() { return hunger; }
        @Override public void setHunger(int h) { hunger = h; }
        @Override public int getSleep() { return sleep; }
        @Override public void setSleep(int s) { sleep = s; }
        @Override public int getHappiness() { return happiness; }
        @Override public void setHappiness(int h) { happiness = h; }
        @Override public void changeState(State mood) { currentState = mood; }
        @Override public State getState() { return currentState; }
    }

    @BeforeEach
    void setUp() {
        // create new pet and commands each time
        pet = new PetStub();
        commands = new Commands(pet);
    }

    @AfterEach
    void tearDown() {
        // clean everything up
        pet = null;
        commands = null;
    }

    @Test
    void isDisabled() {
        // test disabling feed command
        commands.disableCommand(0);
        assertTrue(commands.isDisabled(0));
        // gift should still be enabled
        assertFalse(commands.isDisabled(1));
    }

    @Test
    void checkStat() {
        // check all stats are returned correctly
        assertEquals(50, commands.checkStat(0));
        assertEquals(50, commands.checkStat(1));
        assertEquals(50, commands.checkStat(2));
        assertEquals(50, commands.checkStat(3));
    }

    @Test
    void disableCommand() {
        // try disabling a command and confirm it's off
        commands.disableCommand(1);
        assertTrue(commands.isDisabled(1));
    }

    @Test
    void enableCommand() {
        // disable then enable again
        commands.disableCommand(2);
        commands.enableCommand(2);
        assertFalse(commands.isDisabled(2));
    }

    @Test
    void feed() {
        // run feed on all food types — not checking values here
        commands.feed(0);
        commands.feed(1);
        commands.feed(2);
    }

    @Test
    void giveGift() {
        // check that giving a gift boosts happiness
        int oldHappiness = pet.getHappiness();
        commands.giveGift(0);
        assertTrue(pet.getHappiness() > oldHappiness);
    }

    @Test
    void putToBed() {
        // pet should go to sleep and all commands should be off
        commands.putToBed();
        assertTrue(commands.isDisabled(0));
        assertTrue(commands.isDisabled(5));
    }

    @Test
    void vet() {
        // vet should restore some health and disable bed command
        pet.setHealth(30);
        commands.vet();
        assertTrue(pet.getHealth() > 30);
        assertTrue(commands.isDisabled(2));
    }

    @Test
    void play() {
        // play should increase happiness and disable play
        pet.setHappiness(10);
        commands.play();
        assertTrue(pet.getHappiness() > 10);
        assertTrue(commands.isDisabled(3));
    }

    @Test
    void exercise() {
        // exercise should boost health and reduce sleep + hunger
        pet.setSleep(50);
        pet.setHunger(50);
        pet.setHealth(30);
        commands.exercise();
        assertEquals(40, pet.getSleep());
        assertEquals(40, pet.getHunger());
        assertEquals(55, pet.getHealth());
    }

    @Test
    void foodEffect() {
        // check that feeding applies hunger increase
        FoodItem carrot = new Carrot();
        commands.foodEffect(carrot);
        assertTrue(pet.getHunger() > 50);
    }

    @Test
    void declineHappiness() {
        // make sure happiness drops after decay
        pet.setHappiness(50);
        commands.declineHappiness();
        assertTrue(pet.getHappiness() < 50);
    }

    @Test
    void updateVitals() {
        // this should switch state to hungryState
        pet.setHunger(0);
        commands.updateVitals();
        assertTrue(pet.getState() instanceof hungryState);
    }

    @Test
    void declineHunger() {
        // just check that it runs without crashing
        commands.declineHunger();
    }
}
