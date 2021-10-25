package entity;

public class CorporateUser extends User {

    public CorporateUser() {

    }

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
