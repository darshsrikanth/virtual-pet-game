package classes;

import forms.mainMenuScreen;

import java.time.LocalTime;

public class GameDriver {

    public static void main(String[] args) {
        saveManager gameSaveManager = new saveManager();
        parentalControls parentalControlsInit = new parentalControls(LocalTime.MIDNIGHT, LocalTime.NOON,false);
        gameSaveManager.PCMaker(parentalControlsInit);

        parentalControls gameParentalControls = gameSaveManager.PCReader();

        SoundManager.playBackTrack("sound/backtrack.wav");
        mainMenuScreen mainMenu = new mainMenuScreen(gameSaveManager);
        mainMenu.setVisible(true);


    }
}


