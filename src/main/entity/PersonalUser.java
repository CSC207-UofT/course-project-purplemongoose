package entity;

import java.util.ArrayList;

// This is a personal account for Kard
public class PersonalUser extends User {
    private final ArrayList<Client> contacts; // This is the local copy of User's contacts

    public PersonalUser()
    {
        this.contacts = new ArrayList<>();
    }

    @Override
    public boolean addContact(Client p) {
        if (!contacts.contains(p)) {
            contacts.add(p);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean removeContact(Client p) {
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
        if(this.contacts.isEmpty()){
            return "your contacts list is empty!";
        }
        ret.append("+-------------------------CONTACTS LIST---------------------------+\n");
        for (Client p: this.contacts) {
            ret.append(String.format("%s | %s | %s\n", p.getName(), p.getPhone(), p.getEmail()));
        }
        ret.append("+-----------------------------------------------------------------+\n");
        return ret.toString();
    }
}
