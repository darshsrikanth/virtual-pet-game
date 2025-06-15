package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    private Pet pet;
    private Animal stubAnimal;

    // simple test animal that returns basic values
    static class TestAnimal implements Animal {
        public int getHungerDecay() { return 1; }
        public int getSleepDecay() { return 2; }
        public int getHappinessDecay() { return 3; }
        public String getAnimalType() { return "blobfish"; }
    }

    @BeforeEach
    void setUp() {
        stubAnimal = new TestAnimal();
        pet = new Pet("Noodle", stubAnimal);
    }

    @AfterEach
    void tearDown() {
        pet = null;
        stubAnimal = null;
    }

    @Test
    void changeState() {
        // switching to hungry state and checking that the instance updates
        State newState = new hungryState(pet);
        pet.changeState(newState);
        assertTrue(pet.getState() instanceof hungryState);
    }

    @Test
    void getState() {
        // making sure default constructor sets no state
        State defaultState = new defaultState(pet);
        pet.changeState(defaultState);
        assertSame(defaultState.getClass(), pet.getState().getClass());
    }

    @Test
    void getName() {
        // name should just return what was passed in
        assertEquals("Noodle", pet.getName());
    }

    @Test
    void getPetType() {
        // should return the same animal we used to construct
        assertEquals(stubAnimal, pet.getPetType());
    }

    @Test
    void getHealth() {
        // health starts at 100
        assertEquals(100, pet.getHealth());
    }

    @Test
    void getHunger() {
        // hunger starts at 100
        assertEquals(100, pet.getHunger());
    }

    @Test
    void getSleep() {
        // sleep starts at 100
        assertEquals(100, pet.getSleep());
    }

    @Test
    void getHappiness() {
        // happiness starts at 100
        assertEquals(100, pet.getHappiness());
    }

    @Test
    void getCommands() {
        // commands object should never be null
        assertNotNull(pet.getCommands());
    }

    @Test
    void setHealth() {
        // updating health should reflect in getter
        pet.setHealth(75);
        assertEquals(75, pet.getHealth());
    }

    @Test
    void setHunger() {
        pet.setHunger(60);
        assertEquals(60, pet.getHunger());
    }

    @Test
    void setSleep() {
        pet.setSleep(45);
        assertEquals(45, pet.getSleep());
    }

    @Test
    void setHappiness() {
        pet.setHappiness(30);
        assertEquals(30, pet.getHappiness());
    }

    @Test
    void printCommands() {
        // making sure no crashes
        assertDoesNotThrow(() -> pet.printCommands());
    }
}
