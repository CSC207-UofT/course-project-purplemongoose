import controller.ContactController;
import database.MainFrame;
import entity.PersonalUser;
import entity.Person;
import userInterface.CommandLineInterface;

import java.util.Scanner;
// TODO When making this a class, make it so that there could be multiple "users". They should share a database tho


public class Main {

    public static void main(String[] args) {
        // Database for storing Persons
        MainFrame mf = new MainFrame();

        buildInitialContacts(mf);

        PersonalUser user = new PersonalUser();

        new CommandLineInterface(mf, user).run();
    }

    /**
     * Adds some initial contacts to the database for testing purposes
     *
     * @param mf Mainframe, the database for the program
     */
    private static void buildInitialContacts(MainFrame mf) {
        // TODO: 2021-10-14 Add a few more starting people. Also consider adding some contacts to the user to start out
        // Assume these are people the user can find and add
        Person p1 = new Person("Jack Daniels", "123-456-6789", "jack.daniel@whiskey.com");
        Person p2 = new Person("Bob", "111-222-3333", "bob@thebuilder.com");
        Person p3 = new Person("Jenn Ifer", "121-323-4343", "jennifer@mail.com");

        //Adding users to the mf
        mf.addEntity(p1, "Jack Daniels");
        mf.addEntity(p2, "Bob");
        mf.addEntity(p3, "Jenn Ifer");
    }
}


