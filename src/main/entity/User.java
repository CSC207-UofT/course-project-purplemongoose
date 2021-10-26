package entity;

public abstract class User {

    /** A User is an account on the application
     *
     */

    public abstract boolean addContact(Person p); // return true if added successfully
    public abstract boolean removeContact(Person p); // return true if removed successfully
    public abstract Object getContact(); // return some sort of container for contacts

}
