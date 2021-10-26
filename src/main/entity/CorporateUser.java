package entity;

import java.util.ArrayList;
import java.util.Iterator;

public class CorporateUser extends User implements Iterator {

    /** Represents the User account created by an Organization
     *
     * Suggestion: refactor Corporate user as "OrganizationUser"
     *
     * Currently also implements Iterator to prepare for any iterative
     * mechanism to fetch information about contacts
     */

    private final String orgName;
    private final ArrayList<Client> contacts;

    // setters

    public CorporateUser(String orgName) {
        this.orgName = orgName;
        this.contacts = new ArrayList<>();
    }

    // manipulators

    /** Add a contact to this CorporateUser's contact list
     *
     * @param e Client
     * @return true if the client is added to contacts successfully
     */
    @Override
    public boolean addContact(Client e) {
        if (!contacts.contains(e)) {
            contacts.add(e);
            return true;
        } else {
            return false;
        }
    }

    /** Remove a contact from this CorporateUser's contact list
     *
     * @param e Client
     * @return true if client is removed from contacts successfully
     */
    @Override
    public boolean removeContact(Client e) {
        if (contacts.contains(e)) {
            contacts.remove(e);
            return true;
        } else {
            return false;
        }
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
