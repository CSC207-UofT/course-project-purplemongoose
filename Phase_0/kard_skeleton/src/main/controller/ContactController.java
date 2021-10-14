package main.controller;

import main.database.MainFrame;
import main.entity.Entity;
import main.entity.User;

public class ContactController {
    MainFrame mf;
    User user;

    public ContactController(MainFrame mf, User user) {
        this.mf = mf;
        this.user = user;
    }

    /**
     * Add a contact to the mainframe mf
     *
     * @param id the unique ID of the contact to add to mf
     */
    public void addContactMainFrame(String id) {
        Entity e = mf.query(id);
        if (e==null) {
            //display message that this user does not exist in the db
            // using display classes (in the future)
            System.out.printf("%s could not be found!\n", id);
            return;
        }
        if (user.addContact(e)) {
            System.out.printf("%s has been successfully added!\n", id);
        }
        else {
            System.out.printf("%s is already a contact!\n", id);
        }

    }

    /**
     * Remove a contact from the mainframe mf
     *
     * @param id the unique ID of the contact to remove from mf
     */
    public void removeContactMainFrame(String id) {
        Entity e = mf.query(id);
        if (e==null) {
            //display message that this user does not exist in the db
            // using display classes
            System.out.printf("%s could not be found!\n", id);
            return;
        }
        if (user.removeContact(e)) {
            System.out.printf("%s has been successfully removed!\n", id);
        }
        else {
            System.out.printf("%s is not a contact!\n", id);
        }

    }

}
