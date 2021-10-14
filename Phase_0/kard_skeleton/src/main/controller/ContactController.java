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
