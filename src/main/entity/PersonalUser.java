package entity;

import java.util.ArrayList;

/**
 * The account of a user of kard
 *
 * Store all the user's individual contacts and provides methods to add, remove, and display them
 */
public class PersonalUser extends User {

    private final ArrayList<Client> contacts; // This is the local copy of User's contacts


    public PersonalUser() {
        this.contacts = new ArrayList<>();
    }

    /**
     * Add a person to the user's contact list. Return true if the person was successfully added.
     *
     * @param p person to add to contacts list
     * @return Success of adding to contacts list
     */
    @Override
    public boolean addContact(Client p) {
        if (!contacts.contains(p)) {
            contacts.add(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a person to the user's contact list. Return true if the person was successfully removed.
     *
     * @param p person to remove from contacts list
     * @return Success of removing from contacts list
     */
    @Override
    public boolean removeContact(Client p) {
        if (contacts.contains(p)) {
            contacts.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a String representation of all people in this instance of PersonalUser's contact list.
     *
     * Strings are formatted in the following way:
     *      "[name of user] | [phone number of user] | [email of user]"
     *      with one user per line.
     *
     * @return String representation of contacts list
     */
    @Override
    public Object getContact() {
        // The type of object this method returns will depend on how we choose to display the contacts
        StringBuilder ret = new StringBuilder();
        if(this.contacts.isEmpty()) return "your contacts list is empty!";

        for (Client p: this.contacts)
            ret.append(String.format("%s | %s | %s\n", p.getName(), p.getPhone(), p.getEmail()));

        return ret.toString();
    }
}
