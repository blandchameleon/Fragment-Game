package game;

import java.util.HashMap;
import java.util.Scanner;

public class CommandProcessor {
    private Player player;
    private HashMap<String, Room> rooms;
    private AlienManager alienManager;

    public CommandProcessor(Player player, HashMap<String, Room> rooms, AlienManager alienManager) {
        this.player = player;
        this.rooms = rooms;
        this.alienManager = alienManager;
    }

    public boolean processCommand(String input, Room currentRoom, Scanner scanner) {
        // Process player commands based on input
        switch (input.toLowerCase()) {
            case "help":
                displayHelp();
                break;
            case "info":
                displayInfo(currentRoom);
                break;
            case "pickup":
                player.pickUpItem(currentRoom);
                break;
            case "scan":
                player.useScanner(currentRoom, alienManager.getAlien());
                break;
            case "quit":
                System.out.println("Exiting the game...");
                System.exit(0);
                break;
            default:
                return rooms.containsKey(input); // Room navigation commands
        }
        return false;
    }

    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("- 'c', 'e', 'b', 'x': Move between rooms");
        System.out.println("- 'pickup': Pick up an item");
        System.out.println("- 'scan': Use your scanner");
        System.out.println("- 'info': View your status");
        System.out.println("- 'quit': Exit the game");
    }

    private void displayInfo(Room currentRoom) {
        System.out.println("====== CURRENT STATUS ======");
        System.out.println("Current Room: " + currentRoom.getName());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Item: " + (player.getCurrentItem() == null ? "None" : player.getCurrentItem()));
        System.out.println("Available Rooms:");
        System.out.println("  c: Command Center");
        System.out.println("  e: Engine Room");
        System.out.println("  b: Bio Lab");
        System.out.println("  x: Escape Pod");
        System.out.println("============================");
    }
}
