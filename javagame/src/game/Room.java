package game;

public class Room {
    private String name;
    private String description;
    private String item;

    public Room(String name, String description, String item) {
        this.name = name;
        this.description = description;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void removeItem() {
        this.item = null;
    }

    public void enterRoom() {

        
        System.out.println("You enter the " + name + ".");
        System.out.println(description);
    
        if (item != null) {
            System.out.println("There is a " + item + " here.");
        }

    }
}
