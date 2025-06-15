package test;

import classes.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }

    @AfterEach
    void tearDown() {
        shop = null;
    }

    @Test
    void getItemPrice() {
        // checking that food, gift, and decor return valid prices
        int foodPrice = shop.getItemPrice(0, 0);
        int giftPrice = shop.getItemPrice(1, 1);
        int decorPrice = shop.getItemPrice(2, 2);

        assertTrue(foodPrice > 0);
        assertTrue(giftPrice > 0);
        assertTrue(decorPrice > 0);
    }

    @Test
    void equipItem() {
        // decor needs to be owned before it can be equipped
        shop.buyDecor(0); // buy sandcastle
        assertFalse(shop.decorEquippedStatus(0)); // should be false at first

        shop.equipItem(0); // equip sandcastle
        assertTrue(shop.decorEquippedStatus(0));
    }

    @Test
    void unequipItem() {
        // equip then unequip to toggle it off
        shop.buyDecor(1); // buy treasure chest
        shop.equipItem(1);
        assertTrue(shop.decorEquippedStatus(1));

        shop.unequipItem(1); // should toggle back off
        assertFalse(shop.decorEquippedStatus(1));
    }

    @Test
    void decorOwnedStatus() {
        // item should not be owned by default
        assertFalse(shop.decorOwnedStatus(2));

        shop.buyDecor(2); // buy pineapple house
        assertTrue(shop.decorOwnedStatus(2));
    }

    @Test
    void decorEquippedStatus() {
        // make sure it's off by default, then flip it on
        assertFalse(shop.decorEquippedStatus(0));

        shop.buyDecor(0);
        shop.equipItem(0);

        assertTrue(shop.decorEquippedStatus(0));
    }

    @Test
    void buyDecor() {
        // buying a decor item should update ownership
        assertFalse(shop.decorOwnedStatus(1));
        shop.buyDecor(1);
        assertTrue(shop.decorOwnedStatus(1));
    }
}
