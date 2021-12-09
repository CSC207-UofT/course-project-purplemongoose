import java.util.Objects;
import java.util.Scanner;

public class CommandLineInterface {
    private final Scanner sc;
    private final Request request;

    /**
     * Constructor for the CLI, instance variables: sc is used to read user input,
     * current_username is the username of the account that is currently logged in, and it starts off empty.
     */
    public CommandLineInterface() {
        this.sc = new Scanner(System.in).useDelimiter("\\n");
        this.request = new Request(null);
    }

    /**
     * Starts up the CLI for interacting with the user and displays a logo.
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
        startingScreen();
    }

    /**
     * Starting screen of the CLI that appears after enter is pressed after the logo appears,
     * this screen allows the user to log in or sign up.
     */
    public void startingScreen() {
        eventLoop:
        while (true) {
            System.out.println("Type 'login' or 'signup'");
            String input = sc.nextLine();
            switch (input) {
                case "login" -> {
                    loginScreen();
                    break eventLoop;
                }
                case "signup" -> {
                    signUpScreen();
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Screen appears after user chooses to log in, asks for username and password and submits these information
     * to account database for verification.
     */
    private void loginScreen() {
        System.out.println("Login [type '/b' to exit]:");
        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            if (username.equals("/b")) {
                startingScreen();
                break;
            }
            System.out.print("Password: ");
            String password = sc.nextLine();
            if (password.equals("/b")) {
                startingScreen();
                break;
            }

            String res = this.request.submitLogin(username, password);

            if (res.equals("105")) {
                System.out.println("Wrong username or password, please try again!\n");
            }
            else if (res.equals("404")){
                System.out.println("Log in attempt failed, please try again!\n");
            }
            else {
                System.out.println("Logged in!\n");
                this.request.setCurrentUsername(username);
                instructionScreen();
                events();
                break;
            }
        }
    }

    /**
     * Screen appears after user chooses to sign up, asks for username and password and submits these information
     * to account database for registration.
     */
    private void signUpScreen() {
        String username;
        String password;
        System.out.println("Sign up [type '/b' to exit]:");
        while (true) {
            while(true) {
                System.out.print("Username: ");
                username = sc.nextLine();
                if (username.equals("/b")) {
                    startingScreen();
                    break;
                } else if (username.equals("")) {
                    System.out.println("Cannot be empty!");
                } else {
                    break;
                }
            }
            while(true) {
                System.out.print("Password: ");
                password = sc.nextLine();
                if (password.equals("/b")) {
                    startingScreen();
                    break;
                } else if (password.equals("")) {
                    System.out.println("Cannot be empty!");
                } else {
                    break;
                }
            }

            System.out.print("Are you sure?\n");
            System.out.print("Press 'y' to continue or press 'n' to restart\n");
            String input = sc.nextLine();
            if (input.equals("y")) {
                String res = this.request.submitSignUp(username, password);
                if (res.equals("100")){
                    System.out.println("Username already taken, try again\n");
                }
                else if (res.equals("404")){
                    System.out.println("Sign up failed, try again\n");
                }
                else{
                    System.out.println("Account made successfully!\n");
                    startingScreen();
                    break;
                }
            }
            else if (!input.equals("n")) {
                System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Events loop for simple cli. The user can choose between the following functions
     *  - Open up the profile page
     *  - Add an existing user from the database to their individual contacts list
     *  - Add a new user to the database
     *  - Remove a contact from the current user's contacts
     *  - Display all the contacts of the current user
     *  - Logout to the home screen
     */
    private void events() {
        String input;
        while (true) {
            System.out.print("[kard]: ");
            input = sc.next();
            switch (input) {
                case "profile" -> profileScreen();
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "display" -> displayContacts();
                case "logout" -> logout();
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Event loop for the profile screen. Here the user can modify their own profile.
     */
    private void profileScreen() {
        String input;
        System.out.println("""
        
        +-----------------------Customize Your Profile--------------------------+
        | Type 'create' to create a public profile                              |
        | Type 'edit' to edit your existing profile                             |
        | Type 'restore' to revert your profile to a previous state             |
        | Type '/b' to go back to the main screen                               |
        +-----------------------------------------------------------------------+
        
        """);
        System.out.print("Your current profile: " + this.request.submitProfileDisplay() + "\n");
        System.out.print("[profile]: ");
        input = sc.next();
        while (!input.equals("/b")) {
            switch (input){
                case "create" -> createProfile();
                case "edit" -> editProfile();
                case "restore" -> restoreProfile();
            }
            System.out.print("[profile]: ");
            input = sc.next();
        }
        instructionScreen();
        events();
    }

    /**
     * This screen lists their past profiles with accompanying indexes. The user can choose a past profile they
     * wish to restore to by indicating the index.
     */
    private void restoreProfile() {
        System.out.println("+-------------------------PROFILE HISTORY-------------------------+");
        System.out.println(this.request.submitProfileMementoDisplay());
        System.out.println("+-----------------------------------------------------------------+");
        while(!Objects.equals(this.request.submitProfileMementoDisplay(), "Your haven't made any changes to your profile yet!")) {
            System.out.println("Enter the index of the profile you would like to restore");
            System.out.print("Index: ");
            String input = sc.next();
            String res = this.request.submitProfileRestore(input);
            if (res.equals("107")) {
                System.out.printf("Profile corresponding to index [%s] not found!%n", input);
            }
            else {
                System.out.print("Profile restored!\n");
                break;
            }
        }
        profileScreen();
    }

    /**
     * This page allows the user to enter in new information to update their profile with
     */
    private void editProfile() {
        System.out.println("Edit your profile by filling out your information below");
        System.out.print("First name: ");
        String first = sc.next();
        System.out.print("Last name: ");
        String last = sc.next();
        System.out.print("Preferred pronouns: ");
        String pronoun = sc.next();
        System.out.print("Titles: ");
        String title = sc.next();
        System.out.print("Phone number: ");
        String phone = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.println("Press y to submit: ");
        if (sc.next().equals("y")){
            String res = this.request.submitProfileUpdate(first, last, pronoun, title, phone, email);
            if (res.equals("107")){
                System.out.print("You don't have a personal profile yet!");
            } else if (res.equals("404")){
                System.out.print("Could not create profile!");
            } else {
                System.out.print("Profile successfully updated!");
            }
        } else {
            System.out.println("Unknown command... Returning to main screen");
        }
        profileScreen();
        events();
    }

    /**
     * This page allows the user to make a new account if they don't currently have one
     */
    private void createProfile() {
        System.out.println("Create your profile by filling out your information below");
        System.out.print("First name: ");
        String first = sc.next();
        System.out.print("Last name: ");
        String last = sc.next();
        System.out.print("Preferred pronouns: ");
        String pronoun = sc.next();
        System.out.print("Titles: ");
        String title = sc.next();
        System.out.print("Phone number: ");
        String phone = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.println("Press y to submit: ");
        if (sc.next().equals("y")) {
            String res = this.request.submitProfileCreate(first, last, pronoun, title, phone, email);
            switch(res) {
                case "106" -> System.out.print("Profile already exists!");
                case "404" -> System.out.print("Could not create profile!");
                default -> System.out.print("Profile successfully created!");
            }
        } else {
            System.out.println("Unknown command... Returning to main screen");
        }
        profileScreen();
        events();
    }

    /**
     * Log out, which clears the current username and returns to Main menu.
     */
    private void logout() {
        this.request.setCurrentUsername(null);
        sc.nextLine();
        startingScreen();
    }

    /**
     * Add a person to the current user's contact list.
     */
    private void addContact() {
        String input;
        System.out.println("Type the username of the person you want to add; type '/b' to return to the main menu");
        System.out.print("[add]: ");
        input = sc.next();
        while (!input.equals("/b")) {
            String res = this.request.submitContactAddition(input);
            switch (res) {
                case "102" -> {
                    System.out.printf("The username [%s] could not be found!\n", input);
                    return;
                }
                case "103" -> {
                    System.out.printf("%s is already a contact!\n", input);
                    return;
                }
                case "404" -> {
                    System.out.println("Contact could not be added, please try again!");
                    return;
                }
                default -> System.out.printf("%s has been successfully added!\n", input);
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
        System.out.println("Type the name of the person you want to remove; type '/b' to return to the main menu");
        System.out.print("[remove]: ");
        input = sc.next();
        while (!input.equals("/b")) {
            String res = this.request.submitContactRemoval(input);
            switch (res) {
                case "102" -> {
                    System.out.printf("[%s] could not be found!\n", input);
                    return;
                }
                case "104" -> {
                    System.out.printf("[%s] is not a contact!\n", input);
                    return;
                }
                case "404" -> {
                    System.out.println("Could not remove contact, please try again!");
                    return;
                }
                default -> System.out.printf("[%s] has been successfully removed!\n", input);
            }
            System.out.print("[remove]: ");
            input = sc.next();
        }
    }

    /**
     * Print a list of all contacts obtained through user.getContact() with some styling;
     */
    private void displayContacts() {
        String param;
        String order = null;
        boolean sorted = false;
        eventLoop:
        while(true) {
            System.out.println("Enter the parameter to sort by [none, name]:");
            System.out.print("Sort by: ");
            param = sc.next();
            switch (param) {
                case "name" -> {
                    sorted = true;
                    break eventLoop;
                }
                case "none" -> {
                    break eventLoop;
                }
                default -> System.out.println("Unknown sorting parameter... Try again!");
            }
        }
        if (sorted) {
            eventLoop:
            while(true) {
                System.out.println("Enter the order of the results:");
                System.out.print("Order [ascend, descend]: ");
                order = sc.next();
                switch (order) {
                    case "ascend", "descend" -> {
                        break eventLoop;
                    }
                    default -> System.out.println("Unknown order... Try again!");
                }
            }
        }
        System.out.println("+-------------------------CONTACTS LIST---------------------------+");
        System.out.println(this.request.submitContactDisplay(param, order));
        System.out.println("+-----------------------------------------------------------------+");
    }

    /**
     * Display a list of commands.
     */
    private void instructionScreen() {
        System.out.println("""
        
        +---kard.-------------------------------------------------------------------+
        | Type 'profile' to manage your public profile                              |
        | Type 'add' to add users to your contacts list                             |
        | Type 'remove' to remove users from your contacts list                     |
        | Type 'display' to display your contacts list                              |
        | Type 'logout' to logout of your account                                   |
        | Type 'quit' to exit the program                                           |
        +---------------------------------------------------------------------------+
        
        """);
    }
}
