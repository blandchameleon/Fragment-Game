package game;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class AlienManager {
    private Alien alien;
    private HashMap<String, Room> rooms;

    public AlienManager(HashMap<String, Room> rooms) {
        this.alien = new Alien();
        this.rooms = rooms;
    }

    public boolean isAlienDefeated() {
        return alien.getHealth() <= 0;
    }

    public void assignAlienRoom(Room playerRoom, int roomCount) {
        Random random = new Random();
        if (roomCount < 2) {  // Alien isn't in any room before the second move
            alien.assignRoom(null); // No alien interaction before room count reaches 2
            return;
        }
        
        // 70% chance the alien is in the player's current room, 30% chance in a random room
        String assignedRoom = random.nextInt(100) < 70 ? playerRoom.getName() : getRandomRoom();
        alien.assignRoom(assignedRoom);
    }

    public boolean shouldTriggerInteraction(Room currentRoom, int roomCount) {
        return roomCount >= 2 && alien.isInRoom(currentRoom.getName());
    }

    public Alien getAlien() {
        return alien;
    }
    

    public void forceInteraction(Scanner scanner, Player player) {
        System.out.println("You see the alien! What do you do?");
        System.out.println("1) Run to another room");
        System.out.println("2) Try to calm it");
        System.out.println("3) Attack");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1": // Run
                player.runToNewRoom();
                alien.increaseAggression(1);
                break;
            case "2": // Calm
                if (player.getCurrentItem() != null && player.getCurrentItem().equals("Music Device")) {
                    // If the player has the calming item, there's a 75% chance it works
                    if (Math.random() < 0.75) {
                        System.out.println("The music soothes the alien.");
                        alien.decreaseAggression(2);
                    } else {
                        System.out.println("The alien remains hostile.");
                        alien.increaseAggression(1);
                    }
                } else {
                    System.out.println("You have nothing to calm it. The alien grows more aggressive.");
                    alien.increaseAggression(2);
                }
                break;
            case "3": // Attack
                if (player.attackAlien(alien)) {
                    System.out.println("You hit the alien!");
                } else {
                    System.out.println("You miss, and the alien retaliates!");
                    alien.attackPlayer(player);
                }
                break;
            default:
                System.out.println("Invalid choice. The alien grows more aggressive.");
                alien.increaseAggression(1);
        }
    }

    private String getRandomRoom() {
        // Select a random room from the available rooms for when the player runs away
        Object[] keys = rooms.keySet().toArray();
        return (String) keys[new Random().nextInt(keys.length)];
    }
}