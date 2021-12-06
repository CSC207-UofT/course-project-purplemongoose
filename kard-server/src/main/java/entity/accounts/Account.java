package entity.accounts;

import entity.profiles.Person;

import java.util.HashSet;
import java.io.Serializable;

/**
 * A User is any account on kard. All subclasses of User manage the relationships between the
 * individual users between the contacts. All Users must have an ArrayList that contains all of its contacts
 *
 * Each User has connections, compiled in ArrayLists:
 * 1. Contacts: other individuals they are associated to
 *
 */

public abstract class Account implements Serializable {
    public abstract void addContact(Person person);
    public abstract void removeContact(Person person);
    public abstract boolean checkContacts(Person person);
    public abstract HashSet<String> getContacts();
}
