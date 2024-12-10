package game;

public class Player {
    private String currentItem;
    private int health;

    public Player() {
        this.currentItem = null;
        this.health = 10; // Starting health
    }

    public String getCurrentItem() {
        return currentItem;
    }


    public void pickUpItem(Room room) {
        if (room.getItem() == null) {
            System.out.println("There’s nothing to pick up here.");
            return;
        }
    
        if (currentItem != null) {
            System.out.println("You are already carrying a " + currentItem + ".");
            System.out.println("Drop it to pick up the " + room.getItem() + "? (yes/no)");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String response = scanner.nextLine().toLowerCase();
    
            if (response.equals("yes")) { 
                System.out.println("You dropped the " + currentItem + " and picked up the " + room.getItem() + ".");
                String droppedItem = currentItem; // Store the current item
                currentItem = room.getItem();    // Update to the new item
                room.setItem(droppedItem);      // Place the dropped item in the room
            } else {
                System.out.println("You decide to keep the " + currentItem + ".");
            }
        } else {
            System.out.println("You picked up the " + room.getItem() + ".");
            currentItem = room.getItem(); // Pick up the new item
            room.removeItem();            // Remove the picked-up item from the room
        }
    }
    
    public boolean attackAlien(Alien alien) {
        int damage = currentItem != null && currentItem.equals("Weapon") ? 3 : 1;
        alien.reduceHealth(damage);
        return true; // Always hits for simplicity
    }

    public void useScanner(Room currentRoom, Alien alien) {
        System.out.println("Scanning...");
        System.out.println(alien.isInRoom(currentRoom.getName()) ? "Alien is here!" : "Room is clear.");
    }    

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health = Math.max(0, health - amount);
        System.out.println("Your health: " + health);
    }

    public void runToNewRoom() {
        System.out.println("You ran to a different room!");
    }
}

