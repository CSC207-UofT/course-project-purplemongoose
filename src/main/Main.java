import controller.ContactController;
import database.MainFrame;
import entity.PersonalUser;
import entity.Person;

import java.util.Scanner;

// TODO remove scanner as parameter being passed around. Make it a global static variable
// TODO: 2021-10-14 Remove scanner as param once above is done^^^^^^ 
// TODO When making this a class, make it so that there could be multiple "users". They should share a database tho


public class Main {

    public static void main(String[] args) {
        // Database for storing Persons
        MainFrame mf = new MainFrame();

        buildInitialContacts(mf);

        PersonalUser user = new PersonalUser();
        ContactController cc = new ContactController(mf, user);

        Scanner sc = new Scanner(System.in);

        System.out.println("""

                ██╗  ██╗ █████╗ ██████╗ ██████╗
                ██║ ██╔╝██╔══██╗██╔══██╗██╔══██╗
                █████╔╝ ███████║██████╔╝██║  ██║
                ██╔═██╗ ██╔══██║██╔══██╗██║  ██║
                ██║  ██╗██║  ██║██║  ██║██████╔╝
                ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝"""
        );

        System.out.println("Press enter to continue...");
        try {
            sc.nextLine();
        } catch (Exception ignored) {
        }

        events(user, cc, sc, mf);
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

    /**
     * Events loop for simple cli. The user can choose between the following functions
     *  - Add an existing user from the database to their individual contacts list
     *  - Add a new user to the database
     *  - Remove an existing user from the current user's contact list
     *      NOTE: This DOES NOT remove the user from the overall database
     *  - Display all the contacts of the current user
     *  - Quit the program
     *
     * @param user The current user
     * @param cc The database of the user's contacts
     * @param sc Scanner for system input
     */
    private static void events(PersonalUser user, ContactController cc, Scanner sc, MainFrame mf) {
        String input;
        eventLoop:
        while (true) {
            // TODO: 2021-10-14 consider making this a function...
            System.out.println("Type 'add' to add users to your contacts list");
            System.out.println("Type 'add person' to add a user to the database");
            System.out.println("Type 'remove' to remove users from your contacts list");
            System.out.println("Type 'display' to display your contacts list");
            System.out.println("Type 'quit' to exit the program");

            input = sc.nextLine();
            switch (input) {
                case "add" -> addContact(cc, sc);
                case "remove" -> removeContact(cc, sc);
                case "display" -> System.out.println(user.getContact()); //todo temporary; should be part of ContactController
                case "add person" -> addPerson(mf, sc);
                case "quit" -> {
                    System.out.println("Thank you for using Kard");
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Remove a person from the current user's contact list
     *
     * @param cc the contact controller instance
     * @param sc the scanner used for user input
     */
    private static void removeContact(ContactController cc, Scanner sc) {
        String input;
        System.out.println("Type the name of the person you want to remove; type 'back' to exit");
        input = sc.nextLine();
        while (!input.equals("back")) {
            cc.removeContactMainFrame(input);
            input = sc.nextLine();
        }
    }

    /**
     * Add a person to the current user's contact list
     *
     * @param cc the contact controller instance
     * @param sc the scanner used for user input
     */
    private static void addContact(ContactController cc, Scanner sc) {
        String input;
        System.out.println("Type the name of the person you want to add; type 'back' to exit");
        input = sc.nextLine();
        while (!input.equals("back")) {
            cc.addContactMainFrame(input);
            input = sc.nextLine();
        }
    }

    /**
     * Add a new person to the database
     *
     * @param mf Mainframe - the database for the project
     * @param sc Scanner for user input
     */
    private static void addPerson(MainFrame mf, Scanner sc) {
        System.out.println("Enter the name of the person to add:");
        String name = sc.nextLine();

        System.out.println("Enter the phone number of the person to add:");
        String phone = sc.nextLine();

        System.out.println("Enter the email of the person to add:");
        String email = sc.nextLine();

        Person newPerson = new Person(name, phone, email);
        mf.addEntity(newPerson, name);

        System.out.println(name + " has been added to the database!");
    }
}


