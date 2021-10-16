package userInterface;

import controller.ContactController;
import database.MainFrame;
import entity.Person;
import entity.PersonalUser;

import java.util.Scanner;

public class CommandLineInterface {
    private final PersonalUser user;
    private final Scanner sc;
    private final MainFrame mf;
    private final ContactController cc;

    public CommandLineInterface(MainFrame mf, PersonalUser user) {
        this.mf = mf;
        this.user = user;

        this.cc = new ContactController(mf, user);

        sc = new Scanner(System.in).useDelimiter("\\n");
    }

    /**
     * Function that starts up the CLI for interacting with the user.
     */
    public void run() {

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
            instructionScreen();
        } catch (Exception ignored) {
        }

        events();
    }

    /**
     * Events loop for simple cli. The user can choose between the following functions
     *  - Add an existing user from the database to their individual contacts list
     *  - Add a new user to the database
     *  - Remove an existing user from the current user's contact list
     *      NOTE: This DOES NOT remove the user from the overall database
     *  - Display all the contacts of the current user
     *  - Quit the program
     */
    private void events() {
        String input;
        eventLoop:
        while (true) {
//            instructionScreen();
            System.out.print("> ");
            input = sc.next();
            switch (input) {
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "display" -> displayContacts();
                case "add person" -> addPerson();
                case "quit" -> {
                    System.out.println("Thank you for using Kard");
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Print a list of all contacts obtained through user.getContact() with some styling;
     */
    private void displayContacts() {
        //todo temporary; should be part of ContactController
        System.out.println("+-------------------------CONTACTS LIST---------------------------+");
        System.out.println(user.getContact());
        System.out.println("+-----------------------------------------------------------------+");
    }

    /**
     * Remove a person from the current user's contact list.
     */
    private void removeContact() {
        String input;
        System.out.println("Type the name of the person you want to remove; type 'back' to return to the main menu");
        System.out.print("[remove]: ");
        input = sc.next();
        while (!input.equals("back")) {
            cc.removeContactMainFrame(input);
            System.out.print("[remove]: ");
            input = sc.next();
        }
    }

    /**
     * Add a person to the current user's contact list.
     */
    private void addContact() {
        String input;
        System.out.println("Type the name of the person you want to add; type 'back' to return to the main menu");
        System.out.print("[add]: ");
        input = sc.next();
        while (!input.equals("back")) {
            cc.addContactMainFrame(input);
            System.out.print("[add]: ");
            input = sc.next();
        }
    }

    /**
     * Add a new person to the database.
     */
    private void addPerson() {
        System.out.print("Enter the name of the person to add: ");
        String name = sc.next();

        System.out.print("Enter the phone number of the person to add: ");
        String phone = sc.next();

        System.out.print("Enter the email of the person to add: ");
        String email = sc.next();

        Person newPerson = new Person(name, phone, email);
        mf.addClient(newPerson, name);

        System.out.println(name + " has been added to the database!");
    }

    /**
     * Display a list of commands.
     */
    private void instructionScreen() {
        System.out.println("""
        
        +---kard.--------------------------------------------------+
        | Type 'add' to add users to your contacts list            |
        | Type 'add person' to add a user to the database          |
        | Type 'remove' to remove users from your contacts list    |
        | Type 'display' to display your contacts list             |
        | Type 'quit' to exit the program                          |
        +----------------------------------------------------------+
        
        """);
    }
}
