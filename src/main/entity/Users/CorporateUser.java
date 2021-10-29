package entity.Users;

import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;

import java.util.HashMap;

/**
 * The account of an Organization on kard
 *
 * Stores information about each corporate User's contacts.
 * Stores information about each corporate User's affiliations with
 * other corporations and association, which is how they're related
 * to them (ex. partner, rival, parent company, etc).
 *
 * If no connection was declared during initialization of contact, then
 * connection is null.
 *
 * Provides methods to add, remove, display, and modify nature of association
 * with connections.
 *
 */

public class CorporateUser extends User {

    /** Represents the User account created by an Organization
     *
     * Suggestion: refactor Corporate user as "OrganizationUser"
     *
     * Stores information about the User's contacts and provides
     * associated methods
     *
     * Currently also implements Iterator to prepare for any iterative
     * mechanism to fetch information about contacts
     */

    private final HashMap<ProfileType, String> employees;
    private final HashMap<ProfileType, String> affiliations;

    // Initialize two empty HashMaps for each connection
    public CorporateUser() {
        this.employees = new HashMap<>();
        this.affiliations = new HashMap<>();
    }

    /**
     * Add an employee to a User's employees list
     *
     * This method uses methods defined in Connections, which provides an
     * algorithmic framework for the types of connections a corporation
     * can have:
     *
     * 1. Employees - connections with individual accounts
     * 2. Affiliations - connections with other organization accounts
     *
     * @param p Person to add as an employee
     * @return success of adding a new employee to the HashMap
     */
    @Override
    public boolean addContact(Person p) {
        Connections conn = new Connections();
        return conn.addConnection(employees, p);
    }

    /** Remove a contact from the User's contact list.
     *
     * @param p Person
     * @return true if client is removed from contacts successfully
     */
    @Override
    public boolean removeContact(Person p) {
        Connections conn = new Connections();
        return conn.removeConnection(employees, p);
    }

    /**
     * Return some sort of container that contains all employees of this User
     * @return HashMap of all employees
     */
    @Override
    public Object getContact() {
        return employees.keySet();
    }

    public String getContacts() {
        if (employees.isEmpty()) {
            return "your employees list is empty!";
        }
        Connections conn = new Connections();
        return conn.getConnections(employees);
    }

    @Override
    public boolean addAffiliation(Organization o) {
        Connections conn = new Connections();
        return conn.addConnection(affiliations, o);
    }

    public boolean addAffiliation(Organization o, String association) {
        Connections conn = new Connections();
        return conn.addConnection(affiliations, o, association);
    }

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
     * PersonalUser's contact list.
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
}
