package userInterface;

import controller.LoginController;
import controller.AccountController;
import entity.accounts.Account;


import java.util.Scanner;

public class CommandLineInterface {
    private final Scanner sc;
    private final AccountController uc;
    private final LoginController lc;
    private final String accountUUID; // hack for the program state tracker class

    public CommandLineInterface() {
        this.uc = new AccountController();
        this.lc = new LoginController();
        this.sc = new Scanner(System.in).useDelimiter("\\n");
        this.accountUUID = null;
    }

    /**
     * Function that starts up the CLI for interacting with the user.
     *//*
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
            loginScreen();
//            instructionScreen();
        } catch (Exception ignored) {
        }

//        events();
    }

    private void loginScreen() {
        System.out.println("Please log in:");
        int status = 0;
        eventloop:
        while(status != 1){
            System.out.print("username: ");
            String username = sc.next();
            System.out.print("password: ");
            String password = sc.next();
            status = (int) lc.submitLogin(username, password);
            System.out.println("wrong username or password, please try again! \n");
        }

        System.out.println("logged in! \n");
        try {
            instructionScreen();
        } catch (Exception ignored) {
        }

        events();
    }

    *//**
     * Events loop for simple cli. The user can choose between the following functions
     *  - Add an existing user from the database to their individual contacts list
     *  - Add a new user to the database
     *  - Remove an existing user from the current user's contact list
     *      NOTE: This DOES NOT remove the user from the overall database
     *  - Display all the contacts of the current user
     *  - Quit the program
     *//*
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
                case "quit" -> {
                    System.out.println("Thank you for using Kard");
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    *//**
     * Print a list of all contacts obtained through user.getContact() with some styling;
     *//*
    private void displayContacts() {
        System.out.println("+-------------------------CONTACTS LIST---------------------------+");
        System.out.println(user.getContact());
        System.out.println("+-----------------------------------------------------------------+");
    }

    *//**
     * Remove a person from the current user's contact list.
     *//*
    private void removeContact() {
        String input;
        System.out.println("Type the name of the person you want to remove; type 'back' to return to the main menu");
        System.out.print("[remove]: ");
        input = sc.next();
        while (!input.equals("back")) {
            if (uc.submitSearch(input)==null) {

                //display message that this user does not exist in the db
                // using display classes
                System.out.printf("%s could not be found!\n", input);
            }

            this.uc.submitContactRemoval(this.user, input);
            System.out.printf("%s has been successfully removed!\n", input);
            System.out.print("[remove]: ");
            input = sc.next();
        }
    }

    *//**
     * Add a person to the current user's contact list.
     *//*
    private void addContact() {
        String input;
        System.out.println("Type the name of the person you want to add; type 'back' to return to the main menu");
        System.out.print("[add]: ");
        input = sc.next();
        while (!input.equals("back")) {
            if (uc.submitSearch(input)==null) {

                //display message that this user does not exist in the db
                // using display classes (in the future)
                System.out.printf("%s could not be found!\n", user);
                return;
            }

            this.uc.submitContactAddition(this.user, input);
            System.out.printf("%s has been successfully added!\n", input);
            System.out.print("[add]: ");
            input = sc.next();
        }
    }



    *//**
     * Display a list of commands.
     *//*
    private void instructionScreen() {
        System.out.println("""
        
        +---kard.--------------------------------------------------+
        | Type 'add' to add users to your contacts list            |
        | Type 'remove' to remove users from your contacts list    |
        | Type 'display' to display your contacts list             |
        | Type 'quit' to exit the program                          |
        +----------------------------------------------------------+
        
        """);
    }

*/
}
