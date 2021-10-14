package main;

import main.controller.ContactController;
import main.database.MainFrame;
import main.entity.PersonalUser;
import main.entity.Person;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Database for storing Persons
        MainFrame mf = new MainFrame();

        // Assume these are people the user can find and add
        Person p1 = new Person("Jack Daniels", "123-456-6789", "jack.daniel@whiskey.com");
        Person p2 = new Person("Bob", "111-222-3333", "bob@thebuilder.com");
        Person p3 = new Person("Jenn Ifer", "121-323-4343", "jennifer@mail.com");

        //Adding users to the mf
        mf.addEntity(p1, "Jack Daniels");
        mf.addEntity(p2, "Bob");
        mf.addEntity(p3, "Jenn Ifer");

        PersonalUser user = new PersonalUser();
        ContactController cc = new ContactController(mf, user);

        Scanner sc = new Scanner(System.in);
        String input;
        /* TODO fix.
        System.out.println("""
                ██╗  ██╗ █████╗ ██████╗ ██████╗\s
                ██║ ██╔╝██╔══██╗██╔══██╗██╔══██╗
                █████╔╝ ███████║██████╔╝██║  ██║
                ██╔═██╗ ██╔══██║██╔══██╗██║  ██║
                ██║  ██╗██║  ██║██║  ██║██████╔╝
                ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝\s
                """);
        */
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }

        eventLoop:
        while (true) {
            System.out.println("Type 'add' to add users to your contacts list");
            System.out.println("Type 'remove' to remove users from your contacts list");
            System.out.println("Type 'display' to display your contacts list");
            System.out.println("Type 'quit' to exit the program");

            input = sc.nextLine();
            switch (input) {
                case "add":
                    addPerson(cc, sc);
                    break;
                case "remove":
                    removePerson(cc, sc);
                    break;
                case "display":
                    System.out.println(user.getContact()); //temporary; should be part of ContactController
                    break;
                case "quit":
                    break eventLoop;
                default:
                    System.out.println("Command not recognized... Try again\n");
                    break;
            }
        }
    }

    /**
     * Remove a person from the mainframe using the contact controller
     *
     * @param cc the contact controller instance
     * @param sc the scanner used for user input
     */
    private static void removePerson(ContactController cc, Scanner sc) {
        String input;
        System.out.println("Type the name of the person you want to remove; type 'back' to exit");
        input = sc.nextLine();
        while (!input.equals("back")) {
            cc.removeContactMainFrame(input);
            input = sc.nextLine();
        }
    }

    /**
     * Add a person to the mainframe using the contact controller
     *
     * @param cc the contact controller instance
     * @param sc the scanner used for user input
     */
    private static void addPerson(ContactController cc, Scanner sc) {
        String input;
        System.out.println("Type the name of the person you want to add; type 'back' to exit");
        input = sc.nextLine();
        while (!input.equals("back")) {
            cc.addContactMainFrame(input);
            input = sc.nextLine();
        }
    }
}


