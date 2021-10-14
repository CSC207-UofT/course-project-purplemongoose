package csc207.purplemongoose.kard;

import java.util.ArrayList;


public class User {
    private ArrayList<Person> contacts;

    public User()
    {
        this.contacts = new ArrayList<>();
    }

    public int addContact(Person p) {
        if (p!=null) {
            if (contacts.contains(p)) {
                return 0;
            }
            else {
                contacts.add(p);
                return 1;
            }
        }
        else {
            return -1;
        }
    }

    public int removeContact(Person p) {
        if (p!=null) {
            if (contacts.contains(p)) {
                contacts.remove(p);
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return -1;
        }
    }

    public String displayContact() {
        StringBuilder ret = new StringBuilder();
        for (Person p: this.contacts) {
            ret.append(String.format("%s | %s | %s\n", p.getName(), p.getPhone(), p.getEmail()));
        }
        return ret.toString();
    }
}
