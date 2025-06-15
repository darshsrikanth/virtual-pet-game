package test;

import classes.Carrot;
import classes.Item;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        // using carrot since it's a simple food item that implements Item
        item = new Carrot();
    }

    @AfterEach
    void tearDown() {
        item = null;
    }

    @Test
    void useItem() {
        // add one, then use it and check quantity dropped
        item.buyItem();
        item.useItem();

        assertEquals(0, ((Carrot) item).getQuantity());
    }

    @Test
    void buyItem() {
        // should increase quantity to 1
        item.buyItem();

        assertEquals(1, ((Carrot) item).getQuantity());
    }

    @Test
    void getPrice() {
        // carrot price is fixed at 10
        assertEquals(10, item.getPrice());
    }
}
