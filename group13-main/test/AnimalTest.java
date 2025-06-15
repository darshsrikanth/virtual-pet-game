package test;

import classes.Animal;
import classes.Axolotl;
import classes.Pufferfish;
import classes.Shark;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal axolotl;
    private Animal pufferfish;
    private Animal shark;

    @BeforeEach
    void setUp() {
        // create one of each animal before every test
        axolotl = new Axolotl();
        pufferfish = new Pufferfish();
        shark = new Shark();
    }

    @AfterEach
    void tearDown() {
        // clean up after each test
        axolotl = null;
        pufferfish = null;
        shark = null;
    }

    @Test
    void getHungerDecayTest() {
        // check hunger decay values match expected for each type
        assertEquals(10, axolotl.getHungerDecay());
        assertEquals(10, pufferfish.getHungerDecay());
        assertEquals(15, shark.getHungerDecay());
    }

    @Test
    void getSleepDecayTest() {
        // test how fast each animal gets tired
        assertEquals(15, axolotl.getSleepDecay());
        assertEquals(10, pufferfish.getSleepDecay());
        assertEquals(10, shark.getSleepDecay());
    }

    @Test
    void getHappinessDecayTest() {
        // verify happiness decay is different between animals
        assertEquals(10, axolotl.getHappinessDecay());
        assertEquals(15, pufferfish.getHappinessDecay());
        assertEquals(10, shark.getHappinessDecay());
    }

    @Test
    void getAnimalTypeTest() {
        // make sure each type reports the correct string
        assertEquals("axolotl", axolotl.getAnimalType());
        assertEquals("pufferfish", pufferfish.getAnimalType());
        assertEquals("shark", shark.getAnimalType());
    }

}
