package entity.accounts;


import entity.profiles.Person;
import entity.profiles.ProfileType;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The account of a Person of kard. Stores information about each individual User's contacts.
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
 */
public class PersonalAccount extends Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 8267757690267757690L;
    private final HashSet<String> contacts;

    public PersonalAccount() {
        this.contacts = new HashSet<>();
    }

    /**
     * Add a contact to a User's contacts list
     *
     * @param p Person to be connected to
     */
    @Override
    public void addContact(Person p) {
        this.contacts.add(p.getUsername());
    }

    /**
     * Remove a person to the User's contact list.
     *
     * @param p person to remove from contacts list
     */
    @Override
    public void removeContact(Person p) {
        this.contacts.remove(p.getUsername());
    }

    /**
     * @return a set of all the Persons that this Account is connected to
     */
    @Override
    public HashSet<String> getContacts() {
        return contacts;
    }

    /**
     *  Checks if the Person being queried has already been added to the contacts of the person that this
     *  Account belongs to
     *
     * @param p the Person being queried
     * @return true if this Person is already a contact of the person this Account belongs to
     */
    @Override
    public boolean checkContacts(Person p) {
        return contacts.contains(p.getUsername());
    }
}
