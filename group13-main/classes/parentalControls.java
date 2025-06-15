package classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * A class representing the parental control menu.
 *
 * <p>
 *     parentalControls allows a 'parent' actor to change the earliest and latest in the day that a user can begin a
 *     game session. It contains the password set by the 'parent' actor, methods for saving/reading the time_log,
 *     and displaying the player statistics (time played and average session time).
 * </p>
 *
 * @version 1.0
 * @author Everett Guyea
 */
public class parentalControls {
    private String password = "group13thebest";
    private String allowedStart;
    private String allowedEnd;
    private String sessionStart;
    private String sessionEnd;
    private boolean isRestrictionEnabled;


    /**
     * Constructor for the parentalControls class.

     * @param allowedStart A LocalTime object containing the earliest time a player is allowed to start a game session.
     * @param allowedEnd A LocalTime object containing the latest time a player is allowed to start a game session.
     * @param isRestrictionEnabled A boolean value that is true if the time restriction feature is enabled and false otherwise.
     */
    public parentalControls(LocalTime allowedStart, LocalTime allowedEnd, boolean isRestrictionEnabled) {
        this.allowedStart = allowedStart.toString();
        this.allowedEnd = allowedEnd.toString();
        this.isRestrictionEnabled = isRestrictionEnabled;
    }

    /**
     * A method to set the time restriction parameters to dictate when a player is allowed to start a game session.
     *
     * @param start A Time object containing the time that is the earliest in the day a user can start a game session.
     * @param end A Time object containing the time that is the latest in the day a user can start a game session.
     */
    public void setTimeRestriction(LocalTime start, LocalTime end) {
        allowedStart = start.toString();
        allowedEnd = end.toString();
    }

    /**
     * A method toggle the time restriction feature.
     *
     * @param toggle A boolean value dictating if the time restriction feature should be enabled(true) or not(false).
     */
    public void toggleTimeRestriction(boolean toggle) {
        isRestrictionEnabled = toggle;
    }

    /**
     * A method to check if the game can be played based on the allowedStart and allowedEnd times.
     *
     * @return A boolean value that is true if the current time is between the allowed play times set by the 'parent' actor
     */
    public boolean isPlayAllowed() {
        if (!isRestrictionEnabled) {return true;}
        else {
            // If the current time is after the allowedStart and before the allowedEnd then play is allowed.
            return LocalTime.now().isAfter(LocalTime.parse(allowedStart)) && LocalTime.now().isBefore(LocalTime.parse(allowedEnd));
        }
    }

    /**
     * A method to retrieve the players total time and average time across game sessions.
     *
     * @return An integer array where the first value is the total time and the second is average time.
     */
    public String[] displayStatistics() {
        long[] timeLog = readTimeLog();
        int totalTime = 0;
        int i;

        for (i = 0; i < timeLog.length; i++) {
            totalTime += timeLog[i];
        }
        int avgTime = totalTime / (i + 1);

        return new String[]{String.valueOf(totalTime), String.valueOf(avgTime)};
    }

    /**
     * A method to revive a deceased pet in a specified save slot.
     *
     * @param slot An integer containing the specified save slot (1 - 3)
     */
    public void revivePet(int slot) {
        saveManager saveManager = new saveManager();
        saveManager.revivePet(slot);
    }

    /**
     * A getter for the password variable.
     *
     * @return A String containing the password set by the 'parent' actor.
     */
    public String getPassword() {
        return password;
    }

    /**
     * A setter for the password variable.
     *
     * @param password A String containing the password set by the 'parent' actor.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * A getter for the allowedStart variable.
     *
     * @return A LocalTime object containing the earliest time in the day a user is allowed to play the game at.
     */
    public LocalTime getAllowedStart() {
        return LocalTime.parse(allowedStart);
    }

    /**
     * A getter for the allowedEnd variable.
     *
     * @return A LocalTime object containing the latest time in the day a user is allowed to play until.
     */
    public LocalTime getAllowedEnd() {
        return LocalTime.parse(allowedEnd);
    }

    public boolean isRestrictionEnabled() {return isRestrictionEnabled;}

    /**
     * Saves an array of integers representing a users play time per session (in hours) to a .json file named
     * 'saves/time_log.json'
     *
     * @param timeLog An array of integers representing the amount of time a user has played in each game session.
     * @return A boolean value representing if the save was successful.
     */
    private boolean saveTimeLog(long[] timeLog) {
        Gson gson = new Gson();
        int newLogLen = timeLog.length + 1;
        long[] newTimeLog = new long[newLogLen];
        int i;

        for (i = 0; i < timeLog.length; i++) {
            newTimeLog[i] = timeLog[i];
        }
        if (sessionStart != null && sessionEnd != null) {
            LocalTime start = LocalTime.parse(sessionStart);
            LocalTime end = LocalTime.parse(sessionEnd);
            newTimeLog[i] = start.until(end, ChronoUnit.HOURS);
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("times", gson.toJsonTree(newTimeLog));

        try(FileWriter fileWriter = new FileWriter("saves/time_log.json")) {
            gson.toJson(jsonObject, fileWriter);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * A method to read the time log that contains the play times (in hours) from previous game sessions.
     *
     * @return An integer array containing the times (in hours) from each previous game session.
     */
    private long[] readTimeLog() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("saves/time_log.json")) {
            JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
            return gson.fromJson(jsonObject.getAsJsonArray("times"), long[].class);
        }
        catch (IOException e) {
            return null;
        }
    }

    /**
     * Method to reset the time_log.json file
     */
    public void resetTimeLog() {
        long[] timeLog = {0};
        saveTimeLog(timeLog);
    }

    /**
     * A method to log the current time of a game session.
     */
    public void logStartTime() {
        sessionStart = LocalTime.now().toString();
    }

    /**
     * A method to log the end time of a game session.
     */
    public void logEndTime() {
        sessionEnd = LocalTime.now().toString();
        saveTimeLog(readTimeLog());
    }
}
