package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GiftItemTest {

    private Coral coral;
    private Pearl pearl;
    private MessageInBottle bottle;

    @BeforeEach
    void setUp() {
        coral = new Coral();
        pearl = new Pearl();
        bottle = new MessageInBottle();
    }

    @AfterEach
    void tearDown() {
        coral = null;
        pearl = null;
        bottle = null;
    }

    @Test
    void getQuantity() {
        // by default, all items should start with 0
        assertEquals(0, coral.getQuantity());
        assertEquals(0, pearl.getQuantity());
        assertEquals(0, bottle.getQuantity());
    }

    @Test
    void useItem() {
        // add some quantity and then use one
        coral.buyItem();
        coral.useItem();
        assertEquals(0, coral.getQuantity());
    }

    @Test
    void buyItem() {
        // should increase quantity by 1
        pearl.buyItem();
        assertEquals(1, pearl.getQuantity());
    }

    @Test
    void getHappyEffective() {
        // fixed values for coral and pearl
        assertEquals(10, coral.getHappyEffective());
        assertEquals(25, pearl.getHappyEffective());

        // bottle is random, just make sure it's one of the expected values
        int value = bottle.getHappyEffective();
        assertTrue(value == 5 || value == 40 || value == -15);
    }

    @Test
    void getPrice() {
        // checking the expected prices
        assertEquals(25, coral.getPrice());
        assertEquals(100, pearl.getPrice());
        assertEquals(50, bottle.getPrice());
    }
}
