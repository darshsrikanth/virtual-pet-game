package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DecorItemTest {

    private PineappleHouse decor;

    @BeforeEach
    void setUp() {
        // start with a fresh decor item before each test
        decor = new PineappleHouse();
    }

    @AfterEach
    void tearDown() {
        // clean up after each test
        decor = null;
    }

    @Test
    void isOwned() {
        // should be false at the start
        assertFalse(decor.isOwned());

        // once bought, it should be true
        decor.buyItem();
        assertTrue(decor.isOwned());
    }

    @Test
    void getPrice() {
        // pineapple house is set to cost 100
        assertEquals(100, decor.getPrice());
    }

    @Test
    void getUseStatus() {
        // not equipped when created
        assertFalse(decor.getUseStatus());

        // once equipped, this should change
        decor.buyItem();
        decor.useItem();
        assertTrue(decor.getUseStatus());
    }

    @Test
    void useItem() {
        // can't equip if not owned
        decor.useItem();
        assertFalse(decor.getUseStatus());

        // equip it
        decor.buyItem();
        decor.useItem();
        assertTrue(decor.getUseStatus());

        // use again to unequip
        decor.useItem();
        assertFalse(decor.getUseStatus());
    }

    @Test
    void buyItem() {
        // initially false
        assertFalse(decor.isOwned());

        // should switch to true after buying
        decor.buyItem();
        assertTrue(decor.isOwned());
    }
}
