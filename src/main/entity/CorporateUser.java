package entity;

import java.util.ArrayList;

public class CorporateUser extends User {

    /** Represents the User account created by an Organization
     *
     * Suggestion: refactor Corporate user as "OrganizationUser
     */

    private final String orgName;
    private final ArrayList<Client> contacts;
    private Integer size;

    // setters

    public CorporateUser(String orgName) {
        this.orgName = orgName;
        this.contacts = new ArrayList<>();
    }

    public void setOrgSize(Integer size) {
        this.size = size;
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

    @Override
    public Object getContact() {
        return null;
    }

    public String getOrgName() {
        return orgName;
    }
}



/* Unimplemented Class - Commented out for simplicity

TODO: 2021-10-16 Determine implementation and implement.

package entity;

// This is a company account for kard
public class CorporateUser extends User {
    // Details are WIP

    @Override
    public boolean addContact(Client e) {
        return false;
    }

    @Override
    public boolean removeContact(Client e) {
        return false;
    }

    @Override
    public Object getContact() {
        return null;
    }
}

*/
