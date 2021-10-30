package entity.Accounts;

import entity.profiles.Organization;
import entity.profiles.Person;

public abstract class Account {

    /** A Account is any account on kard.
     *
     * All subclasses of Account manage the relationships between the
     * individual users between the contacts
     *
     * All Users must have an ArrayList that contains all of its contacts
     *
     * Each Account may have two kinds of connections, compiled in ArrayLists:
     *
     * 1. Contacts: other individuals they are associated to
     * 2. Affiliations: organizations they are associated to
     *
     * A PersonalAccount is each individual human being that is on the app
     * A CorporateAccount is any corporation, organization that is on the app
     *
     */

    public abstract boolean addContact(Person p); // return true if added successfully
    public abstract boolean removeContact(Person p); // return true if removed successfully
    public abstract Object getContact(); // return some sort of container for contacts

    public abstract boolean addAffiliation(Organization o); // return true if added successfully
    public abstract boolean removeAffiliation(Organization o); // return true if removed successfully
    public abstract Object getAffiliation(); // return some sort of container for affiliations

}
