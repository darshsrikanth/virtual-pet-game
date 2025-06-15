import classes.GameInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameInstanceTest {

    // just dummy numbers
    private GameInstance createTestInstance() {
        int petType = 0; // Shark
        String name = "Bubbles";
        int health = 100;
        int fatigue = 50;
        int hunger = 30;
        int happiness = 80;
        int score = 250;
        int sandDollars = 300;
        int[] foodItems = {2, 1, 0};
        int[] giftItems = {1, 2, 1};
        boolean[][] decorItems = {
                {true, false},
                {false, false},
                {true, true}
        };
        return new GameInstance(petType, name, health, fatigue, hunger, happiness, score, sandDollars, foodItems, giftItems, decorItems);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void buyItem() {
        GameInstance gi = createTestInstance();
        int originalSandDollars = gi.getSandDollars();
        boolean result = gi.buyItem(0, 0); // food item index 0
        assertTrue(result);
        assertTrue(gi.getSandDollars() < originalSandDollars); // should decrease
    }

    @Test
    void getInventory() {
        GameInstance gi = createTestInstance();
        assertNotNull(gi.getInventory());
    }

    @Test
    void getShop() {
        GameInstance gi = createTestInstance();
        assertNotNull(gi.getShop());
    }

    @Test
    void getPet() {
        GameInstance gi = createTestInstance();
        assertNotNull(gi.getPet());
    }

    @Test
    void getPlayerScore() {
        GameInstance gi = createTestInstance();
        assertEquals(250, gi.getPlayerScore());
    }

    @Test
    void setPlayerScore() {
        GameInstance gi = createTestInstance();
        gi.setPlayerScore(400);
        assertEquals(400, gi.getPlayerScore());
        gi.setPlayerScore(-50);
        assertEquals(0, gi.getPlayerScore());
    }

    @Test
    void getSandDollars() {
        GameInstance gi = createTestInstance();
        assertEquals(300, gi.getSandDollars());
    }

    @Test
    void setSandDollars() {
        GameInstance gi = createTestInstance();
        gi.setSandDollars(1000);
        assertEquals(1000, gi.getSandDollars());
    }

    @Test
    void setVariables() {
        GameInstance gi = createTestInstance();
        gi.setVariables();
        assertEquals(100, gi.getHealth());
        assertEquals(50, gi.getFatigue());
    }

    @Test
    void getName() {
        GameInstance gi = createTestInstance();
        assertEquals("Bubbles", gi.getName());
    }

    @Test
    void getPetType() {
        GameInstance gi = createTestInstance();
        assertNotNull(gi.getPetType());
    }

    @Test
    void getHealth() {
        GameInstance gi = createTestInstance();
        assertEquals(100, gi.getHealth());
    }

    @Test
    void getFatigue() {
        GameInstance gi = createTestInstance();
        assertEquals(50, gi.getFatigue());
    }

    @Test
    void getHunger() {
        GameInstance gi = createTestInstance();
        assertEquals(30, gi.getHunger());
    }

    @Test
    void getHappiness() {
        GameInstance gi = createTestInstance();
        assertEquals(80, gi.getHappiness());
    }

    @Test
    void getFoodItems() {
        GameInstance gi = createTestInstance();
        assertArrayEquals(new int[]{2, 1, 0}, gi.getFoodItems());
    }

    @Test
    void getGiftItems() {
        GameInstance gi = createTestInstance();
        assertArrayEquals(new int[]{1, 2, 1}, gi.getGiftItems());
    }

    @Test
    void getDecorItems() {
        GameInstance gi = createTestInstance();
        boolean[][] expected = {
                {true, false},
                {false, false},
                {true, true}
        };
        assertArrayEquals(expected, gi.getDecorItems());
    }
}