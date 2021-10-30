package userInterface;

import controller.LoginController;
import controller.AccountController;
import controller.ProfileController;

// add ability to edit your profile later

import java.util.Scanner;

public class CommandLineInterface {
    private final Scanner sc;
    private final AccountController ac;
    private final LoginController lc;

    public CommandLineInterface() {
        this.ac = new AccountController();
        this.lc = new LoginController();
        this.sc = new Scanner(System.in).useDelimiter("\\n");
    }

    /**
     * Function that starts up the CLI for interacting with the user.
     */
    public void run() {
        logoScreen();
        startingScreen();
    }

    public void startingScreen() {
        while (true) {
            System.out.println("Type 'login' to login or 'signup' to create an account");
            String input = sc.nextLine();
            switch (input) {
                case "login" -> loginScreen();
                case "signup" -> signUpScreen();
            }
        }
    }

    private void loginScreen() {
        System.out.println("Please log in:");

        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            if (username.equals("back")) {
                startingScreen();
                break;
            }
            System.out.print("Password: ");
            String password = sc.nextLine();
            if (password.equals("back")) {
                startingScreen();
                break;
            }

            if (lc.submitLogin(username, password)) {
                System.out.println("Logged in!\n");
                instructionScreen();
                events();
                break;
            }
            else {
                System.out.println("Wrong username or password, please try again!\n");
            }
        }
    }

    private void signUpScreen() {
        System.out.println("Sign up:");

        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("Are you sure?\n");
            System.out.print("Press 'y' to continue or press 'n' to restart\n");
            String input = sc.nextLine();
            if (input.equals("y")) {
                lc.submitSignUp(username, password);
                System.out.println("Account made successfully!\n");
                startingScreen();
                break;
            }
            else if (!input.equals("n")) {
                System.out.println("Command not recognized... Try again\n");
            }
        }
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
                case "quit" -> {
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
        System.out.println("Thank you for using Kard");
    }

    /**
     * Add a person to the current user's contact list.
     */
    private void addContact() {
        String input;
        System.out.println("Type the ID of the person you want to add; type 'back' to return to the main menu");
        System.out.print("[add]: ");
        input = sc.next();
        while (!input.equals("back")) {
            int res = ac.submitContactAddition(input);
            if (res == -1) {

                //display message that this user does not exist in the db
                // using display classes (in the future)
                System.out.printf("The ID [%s] could not be found!\n", input);
                return;
            }
            else if (res == 0) {
                System.out.printf("The user corresponding to ID [%s] is already a contact!\n", input);
                return;
            }
            else {
                System.out.printf("The user corresponding to ID [%s] has been successfully added!\n", input);
            }
            System.out.print("[add]: ");
            input = sc.next();
        }
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
            int res = ac.submitContactRemoval(input);
            if (res == -1) {
                System.out.printf("The ID [%s] could not be found!\n", input);
                return;
            }
            else if (res == 0) {
                System.out.printf("The user corresponding to ID [%s] is not a contact!\n", input);
                return;
            }
            else {
                System.out.printf("The user corresponding to ID [%s] has been successfully removed!\n", input);
            }
            System.out.print("[remove]: ");
            input = sc.next();
        }
    }

    /**
     * Print a list of all contacts obtained through user.getContact() with some styling;
     */
    private void displayContacts() {
        System.out.println("+-------------------------CONTACTS LIST---------------------------+");
        System.out.println(ac.submitContactDisplay());
        System.out.println("+-----------------------------------------------------------------+");
    }

    private void addProfile() {
        // must have the ability to create profile to add other users (otherwise no profiles exist to add)
    }

    /**
     * Display a list of commands.
     */
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

    private void logoScreen() {
        System.out.println("""

                ██╗  ██╗ █████╗ ██████╗ ██████╗
                ██║ ██╔╝██╔══██╗██╔══██╗██╔══██╗
                █████╔╝ ███████║██████╔╝██║  ██║
                ██╔═██╗ ██╔══██║██╔══██╗██║  ██║
                ██║  ██╗██║  ██║██║  ██║██████╔╝
                ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝"""
        );

        System.out.println("Press enter to continue...");
        try {sc.nextLine();} catch (Exception ignored) {}
    }
}
