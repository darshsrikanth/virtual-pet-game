import classes.parentalControls;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class parentalControlsTest {

    private parentalControls pc;

    @BeforeEach
    void setUp() {
        pc = new parentalControls(LocalTime.of(10, 0), LocalTime.of(20, 0), true);
    }

    @AfterEach
    void tearDown() {
        pc = null;
    }

    @Test
    void setTimeRestriction() {
        pc.setTimeRestriction(LocalTime.of(9, 0), LocalTime.of(21, 0));
        assertEquals(LocalTime.of(9, 0), pc.getAllowedStart());
        assertEquals(LocalTime.of(21, 0), pc.getAllowedEnd());
    }

    @Test
    void toggleTimeRestriction() {
        pc.toggleTimeRestriction(false);
        assertFalse(pc.isRestrictionEnabled());

        pc.toggleTimeRestriction(true);
        assertTrue(pc.isRestrictionEnabled());
    }

    @Test
    void isPlayAllowed() {
        // should always return true
        pc.toggleTimeRestriction(false);
        assertTrue(pc.isPlayAllowed());
    }

    @Test
    void displayStatistics() {
    }

    @Test
    void revivePet() {
    }

    @Test
    void getPassword() {
        assertEquals("group13thebest", pc.getPassword());
    }

    @Test
    void setPassword() {
        pc.setPassword("group13thebest");
        assertEquals("group13thebest", pc.getPassword());
    }

    @Test
    void getAllowedStart() {
        assertEquals(LocalTime.of(10, 0), pc.getAllowedStart());
    }

    @Test
    void getAllowedEnd() {
        assertEquals(LocalTime.of(20, 0), pc.getAllowedEnd());
    }

    @Test
    void isRestrictionEnabled() {
        assertTrue(pc.isRestrictionEnabled());
        pc.toggleTimeRestriction(false);
        assertFalse(pc.isRestrictionEnabled());
    }

    @Test
    void resetTimeLog() {
        pc.resetTimeLog();
        File file = new File("saves/time_log.json");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void logStartTime() {
        assertDoesNotThrow(() -> pc.logStartTime());
    }

    @Test
    void logEndTime() {
        pc.logStartTime(); // need to set start before calling end
        assertDoesNotThrow(() -> pc.logEndTime());
    }
}