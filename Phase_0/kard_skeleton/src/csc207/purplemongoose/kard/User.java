package csc207.purplemongoose.kard;

import java.util.HashSet;


public class User {
    private HashSet contacts;

    public User()
    {
        this.contacts = new HashSet<Person>();
    }

    public void addContact(Person p) {
        contacts.add(p);
    }

    public void removeContact(Person p) {
        contacts.remove(p);
    }

    public String displayContact() {
        return "";
    }
}
