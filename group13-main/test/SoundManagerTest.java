import classes.SoundManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundManagerTest {

    @BeforeEach
    void setUp() {
        // No setup needed for static methods, but method exists for consistency
    }

    @AfterEach
    void tearDown() {
        // Make sure background music is stopped after each test
        SoundManager.stopBackgroundMusic();
    }

    @Test
    void setClickVol() {
        // Valid values
        assertDoesNotThrow(() -> SoundManager.setClickVol(0.0f));
        assertDoesNotThrow(() -> SoundManager.setClickVol(0.5f));
        assertDoesNotThrow(() -> SoundManager.setClickVol(1.0f));

        // Invalid values
        assertThrows(IllegalArgumentException.class, () -> SoundManager.setClickVol(-0.1f));
        assertThrows(IllegalArgumentException.class, () -> SoundManager.setClickVol(1.5f));
    }

    @Test
    void setBackVol() {
        // Valid values
        assertDoesNotThrow(() -> SoundManager.setBackVol(0.0f));
        assertDoesNotThrow(() -> SoundManager.setBackVol(0.8f));
        assertDoesNotThrow(() -> SoundManager.setBackVol(1.0f));

        // Invalid values
        assertThrows(IllegalArgumentException.class, () -> SoundManager.setBackVol(-0.5f));
        assertThrows(IllegalArgumentException.class, () -> SoundManager.setBackVol(2.0f));
    }

    @Test
    void playSound() {
        // Invalid file should throw an exception
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> SoundManager.playSound("nonexistent.wav")
        );
        assertEquals("Sound file not found.", exception.getMessage());
    }

    @Test
    void playBackTrack() {
        // Invalid file should throw an exception
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> SoundManager.playBackTrack("missing_track.wav")
        );
        assertEquals("Back track not found.", exception.getMessage());
    }

    @Test
    void stopBackgroundMusic() {
        // Should be safe to call, even when no track is running
        assertDoesNotThrow(SoundManager::stopBackgroundMusic);
        assertDoesNotThrow(SoundManager::stopBackgroundMusic);
    }
}
