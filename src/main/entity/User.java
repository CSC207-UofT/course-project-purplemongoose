package entity;

import entity.profiles.Person;

public abstract class User {

    /** A User is any account on kard.
     *
     * All subclasses of User manage the relationships between the
     * individual users between the contacts
     *
     * All Users must have an ArrayList that contains all of its contacts
     *
     * Each User may have two kinds of connections, compiled in ArrayLists:
     *
     * 1. Contacts: other individuals they are associated to
     * 2. Affiliations: organizations they are associated to
     *
     * A PersonalUser is each individual human being that is on the app
     * A CorporateUser is any corporation, organization that is on the app
     *
     */

    public abstract boolean addContact(Person p); // return true if added successfully
    public abstract boolean removeContact(Person p); // return true if removed successfully
    public abstract Object getContact(); // return some sort of container for contacts

}
