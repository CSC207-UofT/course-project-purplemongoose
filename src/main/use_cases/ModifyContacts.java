package use_cases;

import entity.Client;
import entity.User;

public class ModifyContacts {

    public void add(User user, Client contact){
        // assuming the contact does not already exist in the contact list of the user

        user.addContact(contact);
    }

    public void remove(User user, Client contact){
        // assuming the contact is in the contact list of the user

        user.removeContact(contact);
    }
}
