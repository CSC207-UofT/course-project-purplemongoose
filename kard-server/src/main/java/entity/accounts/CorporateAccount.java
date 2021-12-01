package entity.accounts;

import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
 * TODO Suggestion: refactor Corporate user as "OrganizationUser"
 * TODO Suggestion: edit Connection.java so that it is static and does not need to be instantiated for every
 *      method call to it.
 */

public class CorporateAccount extends Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 9026775769082677576L;
    /**
     * Represents the User account created by an Organization
     *
     * Stores information about the User's contacts and provides
     * associated methods
     *
     * Currently also implements Iterator to prepare for any iterative
     * mechanism to fetch information about contacts
     */
    private final HashSet<String> employees;
    private final HashMap<ProfileType, String> affiliations;

    // Initialize two empty HashMaps for each connection
    public CorporateAccount() {
//        this.employees = new HashMap<>();
        this.employees = new HashSet<>();
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
     * @param person Person to add as an employee
     */
    @Override
    public void addContact(Person person){
        this.employees.add(person.getUsername());
    }

    /** Remove a contact from the User's contact list.
     *
     * @param person Person
     */
    @Override
    public void removeContact(Person person){
        this.employees.remove(person.getUsername());
    }

    /**
     * Gets a  set that contains all employees of this Account
     *
     * @return set of all the employees connected to this CorporateAccount
     */
    @Override
    public HashSet<String> getContacts(){
        return this.employees;
    }

    /**
     * Checks if a certain person is stored as an employee of an Organization
     *
     * @param person Person being queried
     * @return true if the Person being queried is in this Organization's employees list
     */
    @Override
    public boolean checkContacts(Person person) {
//        return employees.containsKey(p);
        return employees.contains(person.getUsername());
    }

    /**
     * Adds a new Organization to this CorporateAccount's affiliations with an annotation of the affiliation
     *
     * @param organization another Organization to be added
     * @return true if the new Organization is added
     */
    public boolean addAffiliation(Organization organization) {
        return Connections.addConnection(affiliations, organization);
    }

    /**
     * Adds a new Organization to this CorporateAccount's affiliations with an annotation of the affiliation
     *
     * @param organization another Organization to be added
     * @param association string annotating the nature of the affiliation
     * @return true if the new Organization is added
     */
    public boolean addAffiliation(Organization organization, String association) {
        return Connections.addConnection(affiliations, organization, association);
    }

    /**
     * Removes a given Organization from this CorporateAccount's connections
     *
     * @param organization Organization to be removed
     * @return true if the Organization is successfully removed
     */
    public boolean removeAffiliation(Organization organization) {
        return Connections.removeConnection(affiliations, organization);
    }

    /**
     * Gets a set of all the other organizations that this CorporateAccount is affiliated to
     *
     * @return set of all the affiliated Organizations
     */
    @Override
    public Set<ProfileType> getAffiliations() {
        return affiliations.keySet();
    }
}
