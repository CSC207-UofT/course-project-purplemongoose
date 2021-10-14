package main.database;

import main.entity.Entity;
import java.util.HashMap;


// Dumbed down version of an actual database we would use

public class MainFrame {
    private HashMap<String, Entity> users;

    public MainFrame() {
        // Each Person would have some identifier associated with them
        // to deal with people with the same name
        this.users = new HashMap<>();
    }

    public Entity query(String id) {
        return users.get(id);
    }

    public void addEntity(Entity e, String id) {
        users.put(id, e);
    }

    public void removeEntity(String id) {
        users.remove(id);
    }
}
