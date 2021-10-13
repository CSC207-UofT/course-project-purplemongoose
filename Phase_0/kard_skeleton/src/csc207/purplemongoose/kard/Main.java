package csc207.purplemongoose.kard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
        // Assume these are people the user can find and add
        Person p1 = new Person("Jack Daniels", "123-456-6789", "jack.daniel@whiskey.com");
        Person p2 = new Person("Bob", "111-222-3333", "bob@thebuilder.com");
        Person p3 = new Person("Jenn Ifer", "121-323-4343", "jennifer@mail.com");

        // Our user instance
        User user1 = new User();
        */

        // No backend functionality currently...
        Scanner sc = new Scanner(System.in);
        String input;
        while(true) {
            System.out.println("Type in 'add' to add users to your contacts list");
            System.out.println("Type in 'remove' to remove users from your contacts list");
            System.out.println("Type in 'display' to display your contacts list");
            input = sc.nextLine();
            if (input.equals("add")) {
                System.out.println("Type in the name of the person you want to add; type 'leave' to exit:");
                input = sc.nextLine();
                while (!input.equals("leave")) {
                    System.out.println("adding " + input  + "...");
                    input = sc.nextLine();
                }
            }
            else if (input.equals("remove")) {
                System.out.println("Type in the name of the person you want to remove; type 'leave' to exit:");
                input = sc.nextLine();
                while (!input.equals("leave")) {
                    System.out.println("removing " + input  + "...");
                    input = sc.nextLine();
                }
            }
            else if (input.equals("display")) {
                System.out.println("displaying users...\n");
            }
            else if (input.equals("exit")) {
                break;
            }
            else {
                input = "";
                System.out.println("command not recognized... try again\n");
            }
            
        }

    }

}


