package csc207.purplemongoose.kard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Database for storing Persons
        MainFrame db = new MainFrame();

        // Assume these are people the user can find and add
        Person p1 = new Person("Jack Daniels", "123-456-6789", "jack.daniel@whiskey.com");
        Person p2 = new Person("Bob", "111-222-3333", "bob@thebuilder.com");
        Person p3 = new Person("Jenn Ifer", "121-323-4343", "jennifer@mail.com");

        //Adding users to the db
        db.addPerson(p1, "Jack Daniels");
        db.addPerson(p2, "Bob");
        db.addPerson(p3, "Jenn Ifer");

        // Our user instance
        User user = new User();
        Scanner sc = new Scanner(System.in);
        String input;

        while(true) {
            System.out.println("Type 'add' to add users to your contacts list");
            System.out.println("Type 'remove' to remove users from your contacts list");
            System.out.println("Type 'display' to display your contacts list");
            System.out.println("Type 'quit' to exit the program");

            input = sc.nextLine();
            if (input.equals("add")) {
                System.out.println("Type the name of the person you want to add; type 'back' to exit");
                input = sc.nextLine();
                while (!input.equals("back")) {
                    switch(user.addContact(db.query(input))) {
                        case 1:
                            System.out.printf("%s has been successfully added!\n", input);
                            break;
                        case 0:
                            System.out.printf("%s is already in your contacts!\n", input);
                            break;
                        case -1:
                            System.out.printf("%s does not exist!\n", input);
                            break;
                    }
                    input = sc.nextLine();
                }
            }
            else if (input.equals("remove")) {
                System.out.println("Type the name of the person you want to remove; type 'back' to exit");
                input = sc.nextLine();
                while (!input.equals("back")) {
                    switch(user.removeContact(db.query(input))) {
                        case 1:
                            System.out.printf("%s has been successfully removed!\n", input);
                            break;
                        case 0:
                            System.out.printf("%s is not in your contacts!\n", input);
                            break;
                        case -1:
                            System.out.printf("%s does not exist!\n", input);
                            break;
                    }
                    input = sc.nextLine();
                }
            }
            else if (input.equals("display")) {
                System.out.println(user.displayContact());
            }
            else if (input.equals("quit")) {
                break;
            }
            else {
                System.out.println("Command not recognized... Try again\n");
            }
            
        }

    }

}


