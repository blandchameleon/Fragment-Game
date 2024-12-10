package game;

import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private Player player;
    private AlienManager alienManager;
    private RoomManager roomManager;
    private CommandProcessor commandProcessor;
    private int roomCount;

    private void printSeparator() {
        System.out.println("==============================");
    }

    public Game() {
        player = new Player();
        String escapeCode = generateEscapeCode();
        roomManager = new RoomManager(escapeCode);
        currentRoom = roomManager.getStartingRoom();
        alienManager = new AlienManager(roomManager.getRooms());
        commandProcessor = new CommandProcessor(player, roomManager.getRooms(), alienManager);
        roomCount = 0;
    }

    // This is to make a new randomly generated escape code every new game.
    private String generateEscapeCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int digit = (int) (Math.random() * 10); // Random digit from 0-9
            code.append(digit);
        }
        // System.out.println("DEBUG: Escape Code is " + code); // Debugging only
        return code.toString();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Story introduction
        System.out.println("Welcome to *The Fragment*, a spaceship adventure.");
        System.out.println("You are the first mate on the international exploration spaceship *Fragment*.");
        System.out.println("Your ship recently stopped on an asteroid to refuel and collect samples.");
        System.out.println("Since then, strange things have been happening...");
        System.out.println("- Lights have been flickering unpredictably.");
        System.out.println("- Crew members report hearing strange noises coming from the air ducts.");
        System.out.println("Your captain, worried about the safety of the ship, has ordered you to investigate.");
        System.out.println();
        System.out.print("Do you follow orders and begin this mission? (Y/n): ");

        // Get user input to start or exit
        String input = scanner.nextLine().toLowerCase();
        System.out.println();

        if (input.equals("n")) {
            System.out.println("You choose to ignore the captain's orders. The ship remains shrouded in mystery...");
            System.out.println("Mission Aborted.");
            System.exit(0);
        } else if (input.equals("y") || input.isEmpty()) {
            System.out.println("You follow orders and step into the Command Center, ready to begin your mission.");
            System.out.println("Here's how you navigate the ship:");
            System.out.println("- Type 'info' at any time to see your current room, available rooms, and your health status.");
            System.out.println("- Use room shortcuts ('c' for Command Center, 'e' for Engine Room, etc.) to move.");
            System.out.println("- Type 'help' for a list of all commands.");
            System.out.println("Your mission begins now...");
            printSeparator();
            currentRoom.enterRoom(); // Show the initial room details only once
        } else {
            System.out.println("Invalid choice. Assuming you follow orders...");
            System.out.println("You step into the Command Center, ready to begin your mission.");
            System.out.println("Here's how you navigate the ship:");
            System.out.println("- Type 'info' at any time to see your current room, available rooms, and your health status.");
            System.out.println("- Use room shortcuts ('c' for Command Center, 'e' for Engine Room, etc.) to move.");
            System.out.println("- Type 'help' for a list of all commands.");
            System.out.println("Your mission begins now...");
            printSeparator();
            currentRoom.enterRoom(); // Show the initial room details only once
        }

    // Game loop begins
    while (player.getHealth() > 0 && !alienManager.isAlienDefeated()) {
        System.out.print("Enter command: ");
        input = scanner.nextLine().toLowerCase();

        // Process player command
        if (commandProcessor.processCommand(input, currentRoom, scanner)) {
            // Only handle room navigation commands
            Room newRoom = roomManager.getRoom(input);
            if (newRoom != null && !newRoom.equals(currentRoom)) {
                currentRoom = newRoom; 
                roomCount++;           
                currentRoom.enterRoom();

                // Assign alien to a new room
                alienManager.assignAlienRoom(currentRoom, roomCount);

                // Trigger alien interaction if conditions are met
                if (alienManager.shouldTriggerInteraction(currentRoom, roomCount)) {
                    alienManager.forceInteraction(scanner, player);
                }
            }
        }
    }



        // End of game conditions
        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated. Game over.");
        } else if (alienManager.isAlienDefeated()) {
            System.out.println("Congratulations! You defeated the alien!");
        } else {
            System.out.println("You successfully escaped using the escape pod! Well done!");
        }

        scanner.close();
    }
}
