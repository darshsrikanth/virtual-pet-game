import classes.save;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class saveTest {

    private save testSave;

    @BeforeEach
    void setUp() {
        int[] foodItems = {2, 0, 1};
        int[] giftItems = {0, 1, 3};
        boolean[][] decorItems = {
                {true, false},
                {false, false},
                {true, true}
        };

        testSave = new save("Bubbles", 0, 0, 20, 30, 40, 100, 200, foodItems, giftItems, decorItems);
    }

    @AfterEach
    void tearDown() {
        testSave = null;
    }

    // Original values testing

    @Test
    void getPetType() {
        assertEquals(0, testSave.getPetType());
    }

    @Test
    void getName() {
        assertEquals("Bubbles", testSave.getName());
    }

    @Test
    void getHealth_beforeRevive() {
        assertEquals(0, testSave.getHealth());
    }

    @Test
    void getFatigue_beforeRevive() {
        assertEquals(20, testSave.getFatigue());
    }

    @Test
    void getHunger_beforeRevive() {
        assertEquals(30, testSave.getHunger());
    }

    @Test
    void getHappiness_beforeRevive() {
        assertEquals(40, testSave.getHappiness());
    }

    @Test
    void getPlayerScore() {
        assertEquals(100, testSave.getPlayerScore());
    }

    @Test
    void getSandDollars() {
        assertEquals(200, testSave.getSandDollars());
    }

    @Test
    void getFoodItems() {
        assertArrayEquals(new int[]{2, 0, 1}, testSave.getFoodItems());
    }

    @Test
    void getGiftItems() {
        assertArrayEquals(new int[]{0, 1, 3}, testSave.getGiftItems());
    }

    @Test
    void getDecorItems() {
        boolean[][] expected = {
                {true, false},
                {false, false},
                {true, true}
        };
        assertArrayEquals(expected, testSave.getDecorItems());
    }

    // Revived state testing

    @Test
    void revivePet_shouldUpdateAllVitalsTo100() {
        assertTrue(testSave.revivePet()); // revive success
        assertEquals(100, testSave.getHealth());
        assertEquals(100, testSave.getFatigue());
        assertEquals(100, testSave.getHunger());
        assertEquals(100, testSave.getHappiness());
    }

    @Test
    void revivePet_shouldReturnFalseIfPetAlreadyAlive() {
        testSave.revivePet(); // revive once
        assertFalse(testSave.revivePet()); // now it should return false
    }

    @Test
    void getHealth_afterRevive() {
        testSave.revivePet();
        assertEquals(100, testSave.getHealth());
    }

    @Test
    void getFatigue_afterRevive() {
        testSave.revivePet();
        assertEquals(100, testSave.getFatigue());
    }

    @Test
    void getHunger_afterRevive() {
        testSave.revivePet();
        assertEquals(100, testSave.getHunger());
    }

    @Test
    void getHappiness_afterRevive() {
        testSave.revivePet();
        assertEquals(100, testSave.getHappiness());
    }
}
