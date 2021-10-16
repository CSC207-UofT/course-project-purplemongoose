import database.MainFrame;
import entity.PersonalUser;
import entity.Person;
import userInterface.CommandLineInterface;

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
        // Make a few new people and store them in the MainFrame mf
        Person p1 = new Person("Jack Daniels", "123-456-6789", "jack.daniel@whiskey.com");
        Person p2 = new Person("Bob", "111-222-3333", "bob@thebuilder.com");
        Person p3 = new Person("Jennifer", "121-323-4343", "jennifer@mail.com");

        //Adding users to the mf

        mf.addClient(p1, "Jack Daniels");
        mf.addClient(p2, "Bob");
        mf.addClient(p3, "Jenn Ifer");

    }
}


