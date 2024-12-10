package game;

import java.util.HashMap;

public class RoomManager {
    private HashMap<String, Room> rooms;
    private Room startingRoom; 



    public RoomManager(String escapeCode) {
        rooms = new HashMap<>();
        setupRooms(escapeCode);
    }

    private void setupRooms(String escapeCode) {
        rooms.put("c", new Room("Command Center",
                "The heart of the ship, full of blinking consoles. As you enter, you notice a crumpled note in your pocket titled 'Escape Pod Code'. " +
                        "You can only make out the first digit. A " + escapeCode.charAt(0) + ".", "Weapon"));
        rooms.put("e", new Room("Engine Room",
                "A massive room with the hum of machinery all around. " +
                        "On the wall, someone has scrawled, 'The second digit is " + escapeCode.charAt(1) + ".'", "Toolbox"));
        rooms.put("b", new Room("Bio Lab",
                "Specimens float in jars. It smells... off. " +
                        "You find a dusty clipboard that reads, 'The last digit is " + escapeCode.charAt(3) + ".'", "First Aid Kit"));
        rooms.put("x", new Room("Escape Pod",
                "A small pod with barely enough room for one. You notice the control panel blinking ominously.", "Flute"));

        startingRoom = rooms.get("c"); // Starting room is the Command Center
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }

    public Room getRoom(String key) {
        return rooms.getOrDefault(key, null);
    }
}