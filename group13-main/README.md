# Tidal Tales Virtual Pet Simulator
**Team 13, Spring 2025**


## About the project
Tidal Tales is a Java desktop application that allows players to virtually simulate the experience of pet care. It offers three unique and adorable pet options: a pufferfish, an axolotl and a shark. Players can monitor and manage their pet's health, hunger, happiness, and sleep with a visually pleasing GUI while enjoying features like feeding, decorating the tank and earning sand dollars, the in-game currency!

## Required Libraries & Third-Party Tools

The following libraries and tools are required to build and run the application:


| Tool / Library | Version | Use |
|----------------|---------|---------|
| **Java Development Kit (JDK)** | 23 | Main programming language and runtime |
| **IntelliJ IDEA** | 2023.3 or later | Recommended IDE for development |
| **Maven** | 3.9+ | Dependency management and build automation |
| **Gson** (`com.google.code.gson:gson`) | 2.10.1 | Used for reading and writing JSON save files |
| **JUnit 5** (`org.junit.jupiter:junit-jupiter`) | 5.10.0 | Used for automated testing |
| **Apache Netbeans** (`AbsoluteLayout`) | 2.0 | Used for GUI generation |  


> Note: Dependencies were added via IntelliJ's **Project Structure > Libaraies** interface (not manually in `pom.xml`)

## How to Build the Software
1. Install JDK 23
2. Install IntelliJ IDEA
3. Clone or download the project, then open the project in IntelliJ
4. Ensure dependencies are installed
5. Build the project by running GameDriver

## How to Run the Compiled Software
1. Open the project
2. Open the Tidal Tales folder
3. Open the source folder
3. Run TidalTales.jar

## User Guide
1. Upon launching the game, click new game and go through the process of choosing and naming your pet
2. Click load game to open your newly created save file
3. Use the in-game buttons to interact with and take care of your pet!
4. Use the shop menu to purchase food, gifts, and decor for your tank
5. Monitor your pet's status bars (health, hunger, happiness, sleep) and adjust your care accordingly
6. As your score increases, so does your sand dollars, used in the shop
7. Save progress automatically through the exit button or manually with the save button

**Things to remember**
- Whenever any of the stats other than health hit zero, health starts to lower
- When a pet gets angry, all commands other than those that increase happiness are disabled until the pet has reached happiness 50%

## How to Access and Use Parental Controls
The parental controls menu can be accessed through the main menu of the game
- It is password protected, the hardcoded password being group13thebest
- You have the option to check player statistics, revive any deceased pet and set time limitations
- All automatically saved by the exit button

## What is Contained Within the Folders?

`classes` contains all non-GUI java classes and interfaces used to run the game (along with the GameDriver)  
`forms` contains all GUI java classes and forms  
`images` contains all images used by the classes in forms  
`lib` contains files for the libraries used  
`saves` contains all generated saves, the time log, and parental controls settings  
`sound` contains the backtrack and button click sound effect .wav files  
`test` contains all jUnit test classes  
`JavaDoc` contains generated JavaDoc
`TidalTales` contains the executable game


## Additional Notes for TA
- the game is entirely self-contained and does not require an internet connection

