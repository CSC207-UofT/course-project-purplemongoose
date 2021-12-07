package usecase.account;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Person;
import entity.profiles.ProfileType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usecase.profile.CreateProfile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListContactTest {
    private static ListContact listContact;
    private static CreateAccount createAccount;
    private static ModifyContact modifyContact;
    private static CreateProfile createProfile;

    @BeforeAll
    static void setUp() {
        try {
            listContact = new ListContact(
                    new AccountGateway("./ListContact/mainframe.db"),
                    new ProfileGateway("./ListContact/mainframe.db"));
            createAccount = new CreateAccount(
                    new AccountGateway("./ListContact/mainframe.db"));
            modifyContact = new ModifyContact(
                    new AccountGateway("./ListContact/mainframe.db"),
                    new ProfileGateway("./ListContact/mainframe.db"));
            createProfile = new CreateProfile(
                    new ProfileGateway("./ListContact/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createAccount.newPersonalAccount("john", "123");
        createProfile.newPerson("john", "John", "Smith",
                "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");

        createAccount.newPersonalAccount("sarah", "123");
        createProfile.newPerson("sarah", "Sarah", "Johnson",
                "She/Her", "Ms.", "6471234623", "Taskmgr_dot_exe@gmail.com");

        createAccount.newPersonalAccount("peter", "123");
        createProfile.newPerson("peter", "Peter", "Peter",
                "He/Him", "Mr.", "6475534937", "john.smith@aol.com");
    }

    @AfterAll
    static void tearDown() {
        // Deleting the temporary database after testing
        File tempDB = new File("./ListContact/mainframe.db");
        if (tempDB.delete()) {
            File tempFolder = new File("./ListContact/");
            tempFolder.delete();
        }
    }

    /**
     * Tests displaying an empty contact list
     */
    @Test
    @DisplayName("Display an empty contact list")
    void displayEmptyContacts() {
        createAccount.newPersonalAccount("user1", "123");
        ProfileType[] contacts = listContact.getContacts("user1");
        assertEquals(0, contacts.length);
    }

    /**
     * Tests displaying an unsorted contact list with 2 contacts
     */
    @Test
    @DisplayName("Display a contact list with two contacts")
    void displayTwoContacts() {
        createAccount.newPersonalAccount("user2", "123");
        modifyContact.addContact("user2", "john");
        modifyContact.addContact("user2", "sarah");
        ProfileType[] contacts = listContact.getContacts("user2");

        ProfileType[] expected = {new Person(
                new Name("Sarah", "Johnson", "She/Her", "Ms."),
                new Phone("6471234623"),
                new Email("Taskmgr_dot_exe@gmail.com"),
                "sarah"), new Person(
                new Name("John", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "john")};

        assertEquals(contacts[0], expected[0]);
        assertEquals(contacts[1], expected[1]);

    }

    /**
     * Tests displaying a sorted contact list with 3 contacts in ascending order by name
     */
    @Test
    @DisplayName("Display a contact list with three sorted contacts")
    void displayThreeSortedContacts() {
        listContact.setSorter(new SortByName());
        createAccount.newPersonalAccount("user3", "123");
        modifyContact.addContact("user3", "john");
        modifyContact.addContact("user3", "sarah");
        modifyContact.addContact("user3", "peter");
        ProfileType[] contacts = listContact.getSortedContacts("user3", "ascend");

        ProfileType[] expected = {new Person(
                new Name("John", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "john"), new Person(
                new Name("Peter", "Peter", "He/Him", "Mr."),
                new Phone("6475534937"),
                new Email("john.smith@aol.com"),
                "peter"),new Person(
                new Name("Sarah", "Johnson", "She/Her", "Ms."),
                new Phone("6471234623"),
                new Email("Taskmgr_dot_exe@gmail.com"),
                "sarah")};

        assertEquals(contacts[0], expected[0]);
        assertEquals(contacts[1], expected[1]);
        assertEquals(contacts[2], expected[2]);
    }
}
