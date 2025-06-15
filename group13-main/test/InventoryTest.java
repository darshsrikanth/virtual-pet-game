package test;

import classes.Inventory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        // create a fresh inventory before each test
        inventory = new Inventory();
    }

    @AfterEach
    void tearDown() {
        // reset after each test
        inventory = null;
    }

    @Test
    void getFoodItemQuantityTest() {
        // should start with 0 quantity for all food items
        for (int i = 0; i < 3; i++) {
            assertEquals(0, inventory.getFoodItemQuantity(i));
        }
    }

    @Test
    void getGiftItemQuantityTest() {
        // should start with 0 quantity for all gift items
        for (int i = 0; i < 3; i++) {
            assertEquals(0, inventory.getGiftItemQuantity(i));
        }
    }

    @Test
    void setItemQuantityTest1() {
        // manually set quantity of shrimp to 3
        inventory.setItemQuantity(0, 1, 3);
        assertEquals(3, inventory.getFoodItemQuantity(1));
    }

    @Test
    void setItemQuantityTest2() {
        // manually set quantity of pearl to 5
        inventory.setItemQuantity(1, 2, 5);
        assertEquals(5, inventory.getGiftItemQuantity(2));
    }

    @Test
    void buyItemTest1() {
        // buy carrot twice and check if count goes up
        inventory.buyItem(0, 0);
        assertEquals(1, inventory.getFoodItemQuantity(0));
        inventory.buyItem(0, 0);
        assertEquals(2, inventory.getFoodItemQuantity(0));
    }

    @Test
    void buyItemTest2() {
        // buy message in bottle twice
        inventory.buyItem(1, 1);
        assertEquals(1, inventory.getGiftItemQuantity(1));
        inventory.buyItem(1, 1);
        assertEquals(2, inventory.getGiftItemQuantity(1));
    }

    @Test
    void useItemTest1() {
        // set fish to 2, use once, should go down to 1
        inventory.setItemQuantity(0, 2, 2);
        assertTrue(inventory.useItem(0, 2));
        assertEquals(1, inventory.getFoodItemQuantity(2));
    }

    @Test
    void useItemTest2() {
        // try to use coral without any in inventory
        assertFalse(inventory.useItem(1, 0));
    }

    @Test
    void useItemTest3() {
        // invalid item type should return false
        assertFalse(inventory.useItem(99, 0));
    }
}
