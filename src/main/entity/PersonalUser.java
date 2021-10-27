package entity;

import entity.connections.Contacts;
import entity.profiles.Organization;
import entity.profiles.Person;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The account of a Person of kard
 *
 * Stores information about each individual User's contacts and optionally,
 * their association, which is how they're related to them (ex. current coworker,
 * previous coworker, client, etc).
 *
 * If no connection was declared during initialization of contact, then
 * connection is null.
 *
 * Provides methods to add and remove connections and display them.
 *
 */
public class PersonalUser extends User {

    // Two types of connections: contacts and affiliations
    // A local copy of the User's contacts:
    private final HashMap<Person, String> contacts;
    // A local copy of the User's affiliations:
    private final ArrayList<Organization> affiliations;


    // Initialize two empty ArrayLists for each connection
    public PersonalUser() {
        this.contacts = new HashMap<>();
        this.affiliations = new ArrayList<>();
    }

    @Override
    public boolean addContact(Person p) {
        Connections conn = new Connections();
        conn.addConnection(contacts, p);

    }

//    /**
//     * Add a person to the User's contact list
//     *
//     * This is an overloaded method: if no association is provided,
//     * then add new contact to contacts as a key with a null value
//     *
//     * @param p person to add to contacts list
//     * @return Success of adding to contacts list
//     */
//    @Override
//    public boolean addContact(Person p) {
//        if (!contacts.containsKey(p)) {
//            contacts.put(p, null);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * Add a person to the User's contact list
//     *
//     * This is an overloaded method: if an association is provided,
//     * then add a new contact to contacts as a key with the value
//     * provided in association
//     *
//     * @param p person to add to contacts list
//     * @param association type of association to user
//     * @return success of adding to contacts list
//     */
//    public boolean addContact(Person p, String association) {
//        if (!contacts.containsKey(p)) {
//            contacts.put(p, association);
//            return true;
//        } else {
//            return false;
//        }
//    }

    /**
     * Remove a person to the user's contact list. Return true if the person was successfully removed.
     *
     * @param p person to remove from contacts list
     * @return Success of removing from contacts list
     */
    @Override
    public boolean removeContact(Person p) {
        if (contacts.containsKey(p)) {
            contacts.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a String representation of all people in this instance of PersonalUser's contact list.
     *
     * Strings are formatted in the following way:
     *      "[name of user] | [phone number of user] | [email of user]"
     *      with one user per line.
     *
     * @return String representation of contacts list
     */
    @Override
    public Object getContact() {
        // The type of object this method returns will depend on how we choose to display the contacts
        StringBuilder ret = new StringBuilder();
        if(this.contacts.isEmpty()) return "your contacts list is empty!";

        for (Client p: this.contacts)
            ret.append(String.format("%s | %s | %s\n", p.getName(), p.getPhone(), p.getEmail()));

        return ret.toString();
    }
}
