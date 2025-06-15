package classes;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Sound Manager
 * <br><br>
 * The game instance object stores all relevant information necessary for loading or creating a new game save.
 * The sound manager object stores all relevant methods for starting/stopping the game background music
 * and playing the button sound effects.
 *
 * Variable clickVol is a float representing the volume of the button click sound effect.
 * Variable backVol is a float representing the volume of the background music.
 * Variable backTrack is a Clip object representing the background music.
 *
 * @version 1.0
 * @author Cadence Megan McGillicuddy
 */
public class SoundManager {
    private static float clickVol = 0.8f;
    private static float backVol = 1.0f;
    private static Clip backTrack;

    /**
     * Changes the volume of the button click sound effect to the indicated value.
     * @param volume float indicating the volume the button click sound effect should be set to
     */
    public static void setClickVol(float volume) {
        if (volume < 0.0f || volume > 1.0f) { // if the given volume is not within the valid range
            throw new IllegalArgumentException("Volume has to be between 0.0 and 1.0");
        }
        clickVol = volume; // if valid, setting the volume
    }

    /**
     * Changes the volume of the background music to the indicated value.
     * @param volume float indicating the volume the background music should be set to
     */
    public static void setBackVol(float volume) {
        if (volume < 0.0f || volume > 1.0f) { // if the given volume is not within the valid range
            throw new IllegalArgumentException("Volume has to be between 0.0 and 1.0");
        }
        backVol = volume; // if valid, setting the volume

        if (backTrack != null && backTrack.isRunning()) { // if the back track is already playing, apply the new volume to it
            setVolume(backTrack, backVol);
        }
    }

    /**
     * Plays the indicated sound effect.
     * @param fileName String indicating the name and file path of the sound to be played.
     */
    public static void playSound(String fileName) {
        try {
            URL sound = SoundManager.class.getClassLoader().getResource(fileName); // loading the sound effect from the given filepath
            if (sound == null) { // if not found, throw exception
                throw new IllegalArgumentException("Sound file not found.");
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip(); // create a clip object to play the sound
            clip.open(audioIn);
            setVolume(clip, clickVol); // setting sound volume

            clip.start(); // play the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the indicated background music.
     * @param fileName String indicating the name and file path of the background music to be played.
     */
    public static void playBackTrack(String fileName) {
        try {
            URL sound = SoundManager.class.getClassLoader().getResource(fileName); // loading the background music from the given filepath
            if (sound == null) { // if not found, throw exception
                throw new IllegalArgumentException("Back track not found.");
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
            backTrack = AudioSystem.getClip(); // create a clip object to play the background music
            backTrack.open(audioIn);

            setVolume(backTrack, backVol); // setting volume of the background music

            backTrack.loop(Clip.LOOP_CONTINUOUSLY); // indicates that the background music should continue to play until stopped
            backTrack.start(); // play background music
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the background music from playing.
     */
    public static void stopBackgroundMusic() { // stops back track from running
        if (backTrack != null && backTrack.isRunning()) { // if the background music exists and is currently running
            // stop and close the background track
            backTrack.stop();
            backTrack.close();
        }
    }


    /**
     * Sets the volume of a clip object.
     * @param clip the clip object whose volume should be increased.
     * @param volume float indicating the value the clip's volume should be set to.
     */
    private static void setVolume(Clip clip, float volume) {
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // adjusts the decibels of the clip object
        float min = gain.getMinimum();
        float max = gain.getMaximum();
        float dB = min + (max - min) * volume; // converts the 0.0-1.0 volume into an appropriate decibel value to be applied to the clip object's volume
        gain.setValue(dB);
    }
}
