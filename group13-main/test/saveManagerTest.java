import classes.save;
import classes.saveManager;
import classes.GameInstance;
import classes.parentalControls;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class saveManagerTest {

    private saveManager sm;

    @BeforeEach
    void setUp() {
        sm = new saveManager();
    }

    @AfterEach
    void tearDown() {
        sm = null;
    }

    private save createDummySave() {
        int[] foodItems = {1, 2, 3};
        int[] giftItems = {0, 1, 0};
        boolean[][] decorItems = {
                {true, false},
                {false, false},
                {true, true}
        };
        return new save("Bubbles", 0, 100, 50, 25, 75, 500, 300, foodItems, giftItems, decorItems);
    }

    private parentalControls createDummyPC() {
        return new parentalControls(LocalTime.of(9, 0), LocalTime.of(21, 0), true);
    }

    @Test
    void getSave() {
        save dummy = createDummySave();
        sm.setSave(1, dummy);
        save retrieved = sm.getSave(1);
        assertNotNull(retrieved);
        assertEquals("Bubbles", retrieved.getName());
    }

    @Test
    void getMenuData() {
        sm.setSave(1, createDummySave());
        String[] menuData = sm.getMenuData(1);
        assertNotNull(menuData);
        assertEquals("Bubbles", menuData[0]);
        assertEquals("0", menuData[1]); // Pet type "0" = Shark
    }

    @Test
    void setSave() {
        save dummy = createDummySave();
        boolean result = sm.setSave(2, dummy);
        assertTrue(result);

        File file = new File("saves/save_two.json");
        assertTrue(file.exists());
    }

    @Test
    void revivePet() {
        int[] foodItems = {1, 2, 3};
        int[] giftItems = {0, 1, 0};
        boolean[][] decorItems = {
                {true, false},
                {false, false},
                {true, true}
        };
        save deadPet = new save("Bubbles", 0, 0, 50, 25, 75, 500, 300, foodItems, giftItems, decorItems);
        sm.setSave(3, deadPet);
        boolean revived = sm.revivePet(3);
        assertTrue(revived);
    }


    @Test
    void getGameInstance() {
        sm.setSave(1, createDummySave());
        GameInstance gi = sm.getGameInstance(1);
        assertNotNull(gi);
        assertEquals("Bubbles", gi.getName());
    }

    @Test
    void saveGameInstance() {
        GameInstance gi = new GameInstance(0, "Hams", 80, 40, 20, 90, 250, 100, new int[]{1, 2, 1}, new int[]{2, 1, 1}, new boolean[][]{{true, false}, {false, false}, {true, true}});
        sm.saveGameInstance(1, gi);
        save s = sm.getSave(1);
        assertEquals("Hams", s.getName());
    }

    @Test
    void PCMaker() {
        parentalControls pc = createDummyPC();
        boolean success = sm.PCMaker(pc);
        assertTrue(success);

        File file = new File("saves/parental_controls.json");
        assertTrue(file.exists());
    }

    @Test
    void PCReader() {
        parentalControls pc = createDummyPC();
        sm.PCMaker(pc);

        parentalControls loaded = sm.PCReader();
        assertNotNull(loaded);
        assertEquals(LocalTime.of(9, 0), loaded.getAllowedStart());
        assertEquals(LocalTime.of(21, 0), loaded.getAllowedEnd());
    }
}