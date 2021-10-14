package entity;

import java.util.ArrayList;

// This is a personal account for Kard
public class PersonalUser extends User {
    private ArrayList<Entity> contacts; // This is the local copy of User's contacts

    public PersonalUser()
    {
        this.contacts = new ArrayList<>();
    }

    @Override
    public boolean addContact(Entity p) {
        if (!contacts.contains(p)) {
            contacts.add(p);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean removeContact(Entity p) {
        if (contacts.contains(p)) {
            contacts.remove(p);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getContact() {
        // The type of object this method returns will depend on how we choose to display the contacts
        StringBuilder ret = new StringBuilder();
        for (Entity p: this.contacts) {
            ret.append(String.format("%s | %s | %s\n", p.getName(), p.getPhone(), p.getEmail()));
        }
        return ret.toString();
    }
}
