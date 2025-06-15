package classes;

public class TestCommands {
    public static void main(String[] args) {
        // Create dummy pet
        Pet testPet = new Pet("Fatty", new Shark()); // Shark test pet
        Commands commands = testPet.getCommands();
        CommandType commandType = new CommandType();

        System.out.println("----- Test 1: Disable & Enable Commands -----");
        commands.disableCommand(commandType.getFEED());
        System.out.println(commands.isDisabled(commandType.getFEED()) ? "FEED DISABLED" : "FEED ENABLED");
        commands.enableCommand(commandType.getFEED());
        System.out.println(commands.isDisabled(commandType.getFEED()) ? "FEED DISABLED" : "FEED ENABLED");

        System.out.println("----- Test 2: Give Gift -----");
        System.out.println("Happiness before: " + testPet.getHappiness());
        commands.giveGift(1); // Pearl
        System.out.println("Happiness after: " + testPet.getHappiness());

        System.out.println("----- Test 3: Feed Command -----");
        System.out.println("Hunger before: " + testPet.getHunger());
        commands.feed(0); // Feed fish
        System.out.println("Hunger after: " + testPet.getHunger());

        System.out.println("----- Test 5: Angry State Activation -----");
        testPet.setHappiness(0);
        commands.updateVitals(); // Should trigger angry state
        testPet.printCommands();

        System.out.println("----- Test 4: Sleep State Activation -----");
        testPet.setSleep(0);
        commands.updateVitals(); // Should transition to sleep state
        testPet.printCommands();

        System.out.println("Increasing sleep for wake-up logic...");

        commands.updateVitals(); //pet gains sleep points until it wakes up

        System.out.println("Raising happiness to 50 for recovery...");
        testPet.setHappiness(50);
        commands.updateVitals(); //pet becomes normal
        testPet.printCommands();

        System.out.println("----- Test 6: Hungry State Activation -----");
        testPet.setHunger(0);
        commands.updateVitals(); //pet becomes hungry
        System.out.println("Health: " + testPet.getHealth());
        commands.updateVitals();
        System.out.println("Health after decay: " + testPet.getHealth());

        System.out.println("----- Test 7: Dead State Activation -----");
        testPet.setHealth(0);
        commands.updateVitals();
        testPet.printCommands();
    }
}
