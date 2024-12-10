package game;

public class Alien {
    private int aggression;
    private int health;
    private String assignedRoom;

    public Alien() {
        this.aggression = 0; 
        this.health = 15; 
    }

    public int getAggression() {
        return aggression;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health = Math.max(0, health - amount); // Prevent health from dropping below 0
        System.out.println("The alien's health is now: " + health);
    }

    public void increaseAggression(int amount) {
        aggression = Math.min(10, aggression + amount); // Cap aggression at 10 
        System.out.println("The alien's aggression level increased to " + aggression + ".");
    }

    public void decreaseAggression(int amount) {
        aggression = Math.max(0, aggression - amount); // Aggression can't go below 0
        System.out.println("The alien's aggression level decreased to " + aggression + ".");
    }

    public void assignRoom(String roomName) {
        assignedRoom = roomName;
    }

    public boolean isInRoom(String roomName) {
        return roomName.equals(assignedRoom);
    }

    // for a non-aggressive alien
    public boolean isKind() {
        return aggression <= 5;
    
    }

    // for a medium aggressive alien
    public boolean isPeeved() {
        return aggression >= 6 && aggression <= 7;
    }
    
    // for the most aggressive alien
    public boolean isHostile() {
        return aggression >= 8;
    }

    // This was supposed to be for when the alien attacked the player,
    // It is not quite complete.
    public void attackPlayer(Player player) {
        int damage = isHostile() ? 3 : 1; // Hostile alien does more damage
        player.reduceHealth(damage);
        System.out.println("The alien attacks! You take " + damage + " damage.");
    }


}


