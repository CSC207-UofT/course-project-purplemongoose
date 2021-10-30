package entity.Users;

import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;

import java.util.HashMap;

/**
 * The account of a Person of kard
 *
 * Stores information about each individual Account's contacts and optionally,
 * their association, which is how they're related to them (ex. current coworker,
 * previous coworker, client, etc).
 *
 * If no connection was declared during initialization of contact, then
 * connection is null.
 *
 * Provides methods to add, remove, display, and modify nature of association
 * with connections.
 *
 * This class uses methods that were defined in class Connections, which provides an
 * algorithmic framework of the types of connections a person can have:
 *
 * 1. Contacts - connections with other individual accounts
 * 2. Affiliations - connections with organization accounts
 *
 */
public class PersonalAccount extends Account {

    // Two types of connections: contacts and affiliations
    // Same data structure, different data.
    // A local copy of the Account's contacts:
    private final HashMap<ProfileType, String> contacts;
    // A local copy of the Account's affiliations:
    private final HashMap<ProfileType, String> affiliations;


    // Initialize two empty HashMaps for each connection
    public PersonalAccount() {
        this.contacts = new HashMap<>();
        this.affiliations = new HashMap<>();
    }

    /**
     * Add a contact to a Account's contacts list
     *
     * The following two methods overload each other: if no association is
     * provided, then add a new contact to contacts with a null value.
     *
     * If there is an association provided, then add a new contact to contacts
     * with the value determined in association.
     *
     * @param p Person to be connected to
     * @return success of adding a new person to the HashMap of connections
     */

    @Override
    public boolean addContact(Person p) {
        Connections conn = new Connections();
        return conn.addConnection(contacts, p);
    }

    public boolean addContact(Person p, String association) {
        Connections conn = new Connections();
        return conn.addConnection(contacts, p, association);
    }

    /**
     * Remove a person to the Account's contact list.
     *
     * @param p person to remove from contacts list
     * @return Success of removing from contacts list
     */
    @Override
    public boolean removeContact(Person p) {
        Connections conn = new Connections();
        return conn.removeConnection(contacts, p);
    }

    /**
     * @return a set of all the Persons that this Account is connected to
     */
    @Override
    public Object getContact() {
        return contacts.keySet();
    }

    /**
     * Get a String representation of all other Persons in this instance of
     * PersonalAccount's contact list.
     *
     * Strings are formatted in the following way:
     *      "[name of user] | [phone number of user] | [email of user]"
     *      with one user per line.
     *
     * @return String representation of contacts list
     */
    public String getContacts() {
        if (contacts.isEmpty()) {
            return "your contacts list is empty!";
        }
        Connections conn = new Connections();
        return conn.getConnections(contacts);
    }

    @Override
    public boolean addAffiliation(Organization o) {
        Connections conn = new Connections();
        return conn.addConnection(affiliations, o);
    }

    @Override
    public boolean removeAffiliation(Organization o) {
        Connections conn = new Connections();
        return conn.removeConnection(affiliations, o);
    }

    @Override
    public Object getAffiliation() {
        return affiliations.keySet();
    }

    /**
     * Get a String representation of all other Organizations in this instance of
     * PersonalAccount's contact list.
     *
     * Strings are formatted in the following way:
     *      "[name of user] | [phone number of user] | [email of user]"
     *      with one user per line.
     *
     * @return String representation of contacts list
     */
    public String getAffiliations() {
        if (affiliations.isEmpty()) {
            return "your affiliations list is empty!";
        }
        Connections conn = new Connections();
        return conn.getConnections(affiliations);
    }

//    /**
//     * Get a String representation of all people in this instance of PersonalAccount's contact list.
//     *
//     * Strings are formatted in the following way:
//     *      "[name of user] | [phone number of user] | [email of user]"
//     *      with one user per line.
//     *
//     * @return String representation of contacts list
//     */
//    @Override
//    public Object getContact() {
//        // The type of object this method returns will depend on how we choose to display the contacts
//        StringBuilder ret = new StringBuilder();
//        if(this.contacts.isEmpty()) return "your contacts list is empty!";
//
//        for (Client p: this.contacts)
//            ret.append(String.format("%s | %s | %s\n", p.getName(), p.getPhone(), p.getEmail()));
//
//        return ret.toString();
//    }
}
