package database;

import entity.Client;
import java.util.HashMap;


/**
 * Mainframe is the database for the Kard program
 *
 * The current implementation is simplified for the purpose of the skeleton program
 */
public class MainFrame {
    private HashMap<String, Client> users;

    public MainFrame() {
        // Each Person would have some identifier associated with them
        // to deal with people with the same name
        this.users = new HashMap<>();
    }

    /**
     * Return the user instance of the individual with unique identifier ID
     *
     * @param id unique identifier of the person to find
     * @return the unique user with the ID: id
     */
    public Client query(String id) {
        return users.get(id);
    }

    /**
     * Add an entity to the mainframe
     *
     * @param e the user to add to the mainframe
     * @param id the ID of the user to add
     */
    public void addEntity(Client e, String id) {
        users.put(id, e);
    }

    /**
     * Remove an entity from the mainframe
     *
     * @param id the ID of the user to remove
     */
    public void removeEntity(String id) {
        users.remove(id);
    }
}
