package entity;

import entity.profiles.Organization;
import entity.profiles.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CorporateUser extends User implements Iterator {

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

    private final String orgName;
    private final ArrayList<Person> contacts;
    private final HashMap<Organization, String> associatedOrgs;

    // setters

    public CorporateUser(String orgName, ArrayList<Company> associatedOrganizations) {
        this.orgName = orgName;
        this.associatedOrgs = new HashMap<>();
        this.contacts = new ArrayList<>();
    }

    // manipulators

    /** Add a Person to this CorporateUser's contact list
     *
     * @param p Person
     * @return true if the client is added to contacts successfully
     */
    @Override
    public boolean addContact(Person p) {
        if (!contacts.contains(p)) {
            contacts.add(p);
            return true;
        } else {
            return false;
        }
    }

    /** Remove a contact from this CorporateUser's contact list
     *
     * @param p Person
     * @return true if client is removed from contacts successfully
     */
    @Override
    public boolean removeContact(Person p) {
        if (contacts.contains(p)) {
            contacts.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /** Add other companies that are associated to the current one,
     * such as parent companies or child companies
     * @param o Organization
     * @param association String
     */
    public void addOrgs(Organization o, String association) {
        associatedOrgs.put(o, association);
    }

    // the getters

    /** As of right now, just returns an ArrayList of all the contacts.
     * Maybe add another method that produces a String of all the contacts if need
     * something for human use
     *
     * @return ArrayList Clients
     */
    @Override
    public Object getContact() {
        return contacts;
    }

    public String getOrgName() {
        return orgName;
    }

    // other methods to get info about the organization

    public int getOrgSize() {
        return contacts.size();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
