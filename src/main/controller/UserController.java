package controller;

import database.MainFrame;
import entity.Client;
import entity.User;
import use_cases.ModifyContacts;

public class UserController {
    // TBA until merge with new use cases
    ModifyContacts contacts;
    public UserController(){
        this.contacts = new ModifyContacts();
    }

    public Client submitSearch(String name) {
        // TODO: accesses the mainframe and returns the Client corresponding to the id (name)
        return null;
    }

    public Object submitProfileChanges(Object request) {
        return null;
    }

    public void submitContactRemoval(User user, String name) {
        contacts.remove(user, submitSearch(name));
    }

    public void submitContactAddition(User user, String name) {
        contacts.add(user, submitSearch(name));
    }
}
