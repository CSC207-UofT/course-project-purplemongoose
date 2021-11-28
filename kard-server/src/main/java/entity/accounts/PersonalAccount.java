package entity.accounts;

import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

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
public class PersonalAccount extends Account implements Serializable {

    // Two types of connections: contacts and affiliations
    // Same data structure, different data.
    // A local copy of the User's contacts:
//    private final HashMap<ProfileType, String> contacts;
    private final HashSet<String> contacts;
     // A local copy of the User's affiliations:
    private final HashMap<ProfileType, String> affiliations;


    // Initialize two empty HashMaps for each connection
    public PersonalAccount() {
//        this.contacts = new HashMap<>();
        this.contacts = new HashSet<>();
        this.affiliations = new HashMap<>();
    }

    /**
     * Add a contact to a User's contacts list
     *
     * The following two methods overload each other: if no association is
     * provided, then add a new contact to contacts with a null value.
     *
     * If there is an association provided, then add a new contact to contacts
     * with the value determined in association.
     *
     * @param p Person to be connected to
     * @return true if the new person is added to the HashMap of contacts
     */

    @Override
    public void addContact(Person p) {
//        return Connections.addConnection(contacts, p);
        this.contacts.add(p.getUsername());
    }
    @Serial
    private static final long serialVersionUID = 8267757690267757690L;
    /**
     * Overloaded method
     *
     * String association annotates the type of connection between the person that this account
     * belongs to and the Person being added
     *
     * Examples of associations include "rival", "partner", "client", "colleague", etc.
     *
     * @param p Person to be connected to
     * @param association string to annotate the type of assocation between this user and the queried
     * @return true if the new person is added to the HashMap of contacts
     */
//    public boolean addContact(Person p, String association) {
//        return Connections.addConnection(contacts, p, association);
//    }

    /**
     * Remove a person to the User's contact list.
     *
     * @param p person to remove from contacts list
     * @return Success of removing from contacts list
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

    /**
     * Adds a new organization as an affiliation of the person who this Account belongs to
     *
     * @param o Organization being queried
     * @return true if the Organization is added as a new connection, or affiliation
     */
    @Override
    public boolean addAffiliation(Organization o) {
        return Connections.addConnection(affiliations, o);
    }

    /**
     * Removes an organization as an affiliation of the person who this Account belongs to
     * @param o Organization being queried
     * @return true if the Organization has been removed as an affiliation
     */
    @Override
    public boolean removeAffiliation(Organization o) {
        return Connections.removeConnection(affiliations, o);
    }

    /**
     *
     * @return a set of all the Organizations that this Account is connected to
     */
    @Override
    public Set<ProfileType> getAffiliations()  {
        return affiliations.keySet();
    }
}
