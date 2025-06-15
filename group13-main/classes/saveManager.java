package classes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

/**
 * A class to manage save files by doing read/write operations on them.
 *
 * <p>
 *     This class provides the necessary methods for saving the game, loading a game, and creating a new game. It also
 *     includes a method for reviving a deceased pet. It stores 3 slots for saves, each having a fixed file directory.
 * </p>
 *
 * @version 1.1
 * @author Everett Guyea
 */
public class saveManager {
    private Gson gson = new Gson();
    private save saveOne;
    private save saveTwo;
    private save saveThree;
    //private gameInstance gameInstance;
    private String PCFilePath = "saves/parental_controls.json";
    private String[] saveFilePaths = {"saves/save_one.json", "saves/save_two.json", "saves/save_three.json"};


    public saveManager() {
        saveOne = readSave(1);
        saveTwo = readSave(2);
        saveThree = readSave(3);
    }

    /**
     * @param saveNum The number of the save file being requested (1-3).
     * @return The respective save file.
     */
    public save getSave(int saveNum) {
        return switch (saveNum) {
            case 1 -> saveOne;
            case 2 -> saveTwo;
            case 3 -> saveThree;
            default -> null;
        };
    }

    /**
     * Returns pet type and pet name
     * <p>
     * Example:
     * {"John Doe", "0"} - 0 Corresponds to 'Shark'
     * (Check Number Correspondence for Type of Pet Documentation).
     * If save file DNE or is empty, returns null.
     * </p>
     *
     * @param saveNum A number representing the specified save file (1 - 3).
     * @return A string array containing (in order) the pets name and type number.
     */
    public String[] getMenuData(int saveNum) {
        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader(saveFilePaths[saveNum - 1])) {
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            // Checks for empty or incorrectly formatted .json file
            if (!jsonElement.isJsonObject()) {
                return null;
            }

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Grab name and petType attributes to be used in displaying save data in the GUI.
            String name = jsonObject.get("name").getAsString();
            String petType = jsonObject.get("petType").getAsString();

            // Returns {name, petType} in string array
            return new String[]{name, petType};
        } catch (IOException e) { // If the file does not exist, then there is no save data
            return null;
        }
    }

    /**
     * Method for assigning a save object to one of the three saves in the classes.saveManager and creating its save file
     * ('saves/save_#.json').
     *
     * @param saveNum  A number representing the specified save file (1 - 3).
     * @param saveData A save object containing the data to be saved.
     * @return A boolean value that is true if the save was successful and false otherwise.
     */
    public boolean setSave(int saveNum, save saveData) {
        Gson gson = new Gson();

        switch (saveNum) {
            case 1:
                saveOne = saveData;
                break;
            case 2:
                saveTwo = saveData;
                break;
            case 3:
                saveThree = saveData;
                break;
        }

        try (FileWriter fileWriter = new FileWriter(saveFilePaths[saveNum - 1])) {
            gson.toJson(saveData, fileWriter);
            return true;
        } catch (IOException e) { // Catches and displays IO error
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method for reviving a deceased pet
     *
     * @param saveNum A number representing the specified save file (1 - 3).
     * @return A boolean value that is true if the pet is revived, and false if it was not.
     */
    public boolean revivePet(int saveNum) {
        boolean saveSuccessful;
        save save = getSave(saveNum);

        if (save == null) {return false;}

        saveSuccessful = save.revivePet();
        if (saveSuccessful) {
            setSave(saveNum, save);
        }
        return saveSuccessful;
    }

    /**
     * Method for creating a gameInstance object out of a save file.
     *
     * @param saveNum An integer representing which save slot to access (1 - 3).
     * @return A gameInstance object constructed with the data from a save slot.
     */
    public GameInstance getGameInstance(int saveNum) {
        try (FileReader fileReader = new FileReader(saveFilePaths[saveNum - 1])) {
            return new GameInstance(gson.fromJson(fileReader, save.class));
        } catch (IOException e) { // If the file does not exist, then there is no save data
            return null;
        }
    }

    /**
     * Method for saving a gameInstance object.
     *
     * @param saveNum      An integer representing which save slot to access (1 - 3).
     * @param gameInstance A gameInstance object that will be saved.
     */
    public void saveGameInstance(int saveNum, GameInstance gameInstance) {
        save newSave = new save(gameInstance);
        setSave(saveNum, newSave);
    }

    /**
     * Method to generate a save object based on one of the 3 built in save slots.
     *
     * @param saveNum A number representing the specified save file (1 - 3).
     * @return A save object containing the data from the specified save file. If the save is empty, returns null.
     */
    private save readSave(int saveNum) {
        try (FileReader fileReader = new FileReader(saveFilePaths[saveNum - 1])) {
            return gson.fromJson(fileReader, save.class);
        } catch (IOException e) { // If the file does not exist, then there is no save data
            return null;
        }
    }

    /**
     * A method to create a save file in 'saves/parental_controls.json' containing the parental controls settings.
     *
     * @param parentalControls The parentalControls object that will be saved.
     * @return A boolean value that is true if the save was successful and false otherwise.
     */
    public boolean PCMaker(parentalControls parentalControls) {
        try (FileWriter fileWriter = new FileWriter(PCFilePath)) {
            gson.toJson(parentalControls, fileWriter);
            return true;
        } catch (IOException e) { // Catches and displays IO error
            e.printStackTrace();
            return false;
        }
    }

    /**
     * A method to read the parental controls save file at 'saves/parental_controls.json'.
     *
     * @return A parentalControls object generated with the saved settings.
     */
    public parentalControls PCReader() {
        try (FileReader fileReader = new FileReader(PCFilePath)) {
            return gson.fromJson(fileReader, parentalControls.class);
        } catch (IOException e) { // If the file does not exist, then there is no save data
            return null;
        }
    }
}