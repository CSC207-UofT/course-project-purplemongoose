import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

public class CommandLineInterface {
    private final Scanner sc;
    private String current_username;


    /**
     * Constructor for the CLI, instance variables: sc is used to read user input,
     * current_username is the username of the account that is currently logged in, and it starts off empty.
     */
    public CommandLineInterface() {
        this.sc = new Scanner(System.in).useDelimiter("\\n");
        this.current_username = null;
    }

    /**
     * Function that starts up the CLI for interacting with the user.
     */
    public void run() {
        logoScreen();
        startingScreen();
//        instructionScreen();
//        events();
    }

    /**
     * starting screen of the CLI that appears after enter is pressed after the logo appears,
     * this screen allows the user to log in or sign up.
     */
    public void startingScreen() {
        while (true) {
            System.out.println("Type 'login' to login or 'signup' to create an account");
            String input = sc.nextLine();
            switch (input) {
                case "login" -> loginScreen();
                case "signup" -> signUpScreen();
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Screen appears after user chooses to log in, asks for username and password and submits these information
     * to account database for verification.
     */
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

            int res = submitLogin(username, password);

            if (res == 200) {
                this.current_username = username;
                System.out.println("Logged in!\n");
                instructionScreen();
                events();
                break;
            }
            else if (res == 9){
                System.out.println("Wrong username or password, please try again!\n");
            }
            else{
                System.out.println("Log in attempt failed, please try again!\n");
            }
        }
    }

    /**
     * Method used to compile the inputted information into a HTTP request sent to the database,
     * its information is then verified for log in and returns the result.
     * @param username the username of the account
     * @param password the password of the account
     * @return code of response that indicates if the login was successful, returns the status code of the response
     */
    private int submitLogin(String username, String password){
        String endpoint = "http://localhost:8082/start/login/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"accountPassword\":\"%s\"}",
                username, password);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 404;
    }

    /**
     * Screen appears after user chooses to sign up, asks for username and password and submits these information
     * to account database for registration.
     */
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
                int res;
                res = submitSignUp(username, password);

                if (res == 200){
                    System.out.println("Account made successfully!\n");
                    startingScreen();
                    break;
                }
                else if (res == 10){
                    System.out.println("Username already taken, try again\n");
                }
                else{
                    System.out.println("Sign up failed, try again\n");
                }
            }
            else if (!input.equals("n")) {
                System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Method used to compile the inputted information into a HTTP request sent to the database,
     * its information is then verified for sign up and returns the result.
     * @param username the username of the account
     * @param password the password of the account
     * @return code of response that indicates if the sign up was successful, returns the status code of the response
     */
    private int submitSignUp(String username, String password){
        String endpoint = "http://localhost:8082/start/signup/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"accountPassword\":\"%s\"}",
                username, password);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 404;

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
                case "create" -> createProfile();
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "display" -> displayContacts();
                case "logout" -> logout();
                case "quit" -> {
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
        System.out.println("Thank you for using Kard");
    }

    private void createProfile() {
        System.out.println("Create your profile by filling out your information below");
        System.out.print("first name: ");
        String first = sc.next();
        System.out.print("last name: ");
        String last = sc.next();
        System.out.print("preferred pronoun: ");
        String pronoun = sc.next();
        System.out.print("title: ");
        String title = sc.next();
        System.out.print("phone number: ");
        String phone = sc.next();
        System.out.print("email: ");
        String email = sc.next();
        System.out.println("Press y to submit: ");
        if (sc.next().equals("y")){
            int res = submitProfileCreation(first, last, pronoun, title, phone, email);
            if (res == 25){
                // if a personal profile was already exists
                System.out.print("a personal profile already exists!");
            }else if(res == 200){
                System.out.print("profile successfully created!");
            }else{
                System.out.print("could not create profile!");
            }
        }else{
            System.out.println("unknown command... returning to main screen");
        }
        instructionScreen();
        events();
    }

    private int submitProfileCreation(String first, String last, String pronoun, String title, String phone, String email) {
        String endpoint = "http://localhost:8082/profile/new/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," + "\"firstName\":\"%s\","
                        + "\"lastName\":\"%s\","+ "\"title\":\"%s\","+ "\"pronoun\":\"%s\","
                        + "\"phone\":\"%s\"," + "\"email\":\"%s\"}",
                this.current_username, first, last, pronoun, title, phone, email);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 404;
    }

    /**
     * log out, which clears the current username and returns to Main menu.
     */
    private void logout() {
        this.current_username = null;
        startingScreen();
    }

    /**
     * Add a person to the current user's contact list.
     */
    private void addContact() {
        String input;
        System.out.println("Type the username of the person you want to add; type 'back' to return to the main menu");
        System.out.print("[add]: ");
        input = sc.next();
        while (!input.equals("back")) {
            int res = submitContactAddition(input);
            if (res == 15) {
                // if the username does not correspond to a profile
                //display message that this user does not exist in the db
                // using display classes (in the future)
                System.out.printf("The username [%s] could not be found!\n", input);
                return;
            }
            else if (res == 16) {
                // if the profile is already a contact
                System.out.printf("The user corresponding to ID [%s] is already a contact!\n", input);
                return;
            }
            else if (res == 404){
                // connection failed
                System.out.println("Contact could not be added, please try again!");
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
     * Method used to compile the inputted information into a HTTP request along with the username of
     * the current user to add the inputted username into the current user's contact.
     * @param input the username of the contact which the user wants to add
     * @return status code of response that indicates if the contact addition was successful
     */
    private int submitContactAddition(String input){
        String endpoint = "http://localhost:8082/account/add/contact/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"contactUsername\":\"%s\"}",
                this.current_username, input);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 404;

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
            int res = submitContactRemoval(input);
            if (res == 15) {
                // if the username does not correspond to a profile
                System.out.printf("The ID [%s] could not be found!\n", input);
                return;
            }
            else if (res == 17) {
                // if the profile is not a contact
                System.out.printf("The user corresponding to ID [%s] is not a contact!\n", input);
                return;
            }
            else if (res == 404){
                // connection failed
                System.out.println("Could not remove contact, please try again!");
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
     * Method used to compile the inputted information into a HTTP request along with the username of
     * the current user to remove the inputted username from the current user's contact.
     * @param input the username of the contact which the user wants to remove
     * @return status code of response that indicates if the contact removal was successful
     */
    private int submitContactRemoval(String input) {
        String endpoint = "http://localhost:8082/account/remove/contact/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"contactUsername\":\"%s\"}",
                this.current_username, input);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 404;
    }

    /**
     * Print a list of all contacts obtained through user.getContact() with some styling;
     */
    private void displayContacts() {
        System.out.println("+-------------------------CONTACTS LIST---------------------------+");
        System.out.println(submitContactDisplay());
        System.out.println("+-----------------------------------------------------------------+");
    }

    /**
     * Method used to compile the current username into a HTTP GET request to retrieve a formatted
     * list of contacts which are stored in the server.
     * @return a string of the current users contacts, compiled already and ready for display
     */
    private String submitContactDisplay() {
        String endpoint = String.format("http://localhost:8082/account/display/contact?username=%s",
                this.current_username);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Unable to retrieve contacts list, please try again!";
    }

    /**
     * Display a list of commands.
     */
    private void instructionScreen() {
        System.out.println("""
        
        +---kard.-------------------------------------------------------------------+
        | Type 'create' to create a public profile for others to add your as contact|
        | Type 'add' to add users to your contacts list                             |
        | Type 'remove' to remove users from your contacts list                     |
        | Type 'display' to display your contacts list                              |
        | Type 'logout' to log out and return to main menu                          |
        | Type 'quit' to exit the program                                           |
        +---------------------------------------------------------------------------+
        
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