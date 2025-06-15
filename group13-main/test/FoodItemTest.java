package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemTest {

    private Carrot carrot;
    private Fish fish;
    private Shrimp shrimp;

    @BeforeEach
    void setUp() {
        carrot = new Carrot();
        fish = new Fish();
        shrimp = new Shrimp();
    }

    @AfterEach
    void tearDown() {
        carrot = null;
        fish = null;
        shrimp = null;
    }

    @Test
    void getQuantity() {
        // all items should start with 0
        assertEquals(0, carrot.getQuantity());
        assertEquals(0, fish.getQuantity());
        assertEquals(0, shrimp.getQuantity());
    }

    @Test
    void useItem() {
        // add 1 and use it so quantity drops back to 0
        fish.buyItem();
        fish.useItem();
        assertEquals(0, fish.getQuantity());
    }

    @Test
    void buyItem() {
        // buying should increase quantity by 1
        shrimp.buyItem();
        assertEquals(1, shrimp.getQuantity());
    }

    @Test
    void getPrice() {
        // all food items should be priced at 10
        assertEquals(10, carrot.getPrice());
        assertEquals(10, fish.getPrice());
        assertEquals(10, shrimp.getPrice());
    }
}
