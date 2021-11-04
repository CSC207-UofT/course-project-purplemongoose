package entity.accounts;

import entity.dataFiles.Email;
import entity.dataFiles.Name;
import entity.dataFiles.Phone;
import entity.profiles.Organization;
import entity.profiles.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the CorporateAccount class
 *
 * @see CorporateAccount
 */
class CorporateAccountTest {

    CorporateAccount account;

    @BeforeEach
    void setUp() {
        account = new CorporateAccount();

        // Some test contacts for the account
        account.addContact(new Person(
                new Name("John", "Smith", "He/Him", "Doctor"),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "Johnnyboi69"
        ));
        account.addContact(new Person(
                new Name("Sarah", "johnson"),
                new Phone("4166942069"),
                new Email("Taskmgr_dot_exe@gmail.com"),
                "NotABot"
        ));
        account.addContact(new Person(
                new Name("Peter", "Peter", "They/Them"),
                new Phone("5555555555555555"),
                new Email("john.smith@aol.com"),
                "TheRealJohnSmith"
        ));

        // A test organization
        account.addAffiliation(new Organization(
                "JhonnyboiCorp",
                new Phone("6471234567"),
                new Email("inquiries@joe.io"),
                "JohnnyBoi"
        ));
    }

    @AfterEach
    void tearDown() {

    }

    /**
     * Tests adding a contact to the Account
     */
    @Test
    @DisplayName("Add a contact")
    void testAddContact(){
        Person test_person = new Person(
                new Name("Steven", "Test"),
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "TheFakeJohnSmith"
        );
        Person different =  new Person(
                new Name("Steven", "Test"),
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "TheFakeJohnSmith"
        );

        account.addContact(test_person);
        assertTrue(account.checkContacts(different));
    }

    /**
     * Tests removing a contact of the Account
     */
    @Test
    @DisplayName("Remove a person")
    void testRemoveContact(){
        Person test_person = new Person(
                new Name("Peter", "Peter", "They/Them"),
                new Phone("5555555555555555"),
                new Email("john.smith@aol.com"),
                "TheRealJohnSmith"
        );

        assertTrue(account.removeContact(test_person));
        assertTrue(account.checkContacts(test_person));
    }

    /**
     * Tests removing a person that is not a contact of the Account
     */
    @Test
    @DisplayName("Remove a person that doesn't exist")
    void testRemoveContactNotExists(){
        Person test_person = new Person(
                new Name("Steven", "Test"),
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "TheFakeJohnSmith"
        );

        String before = account.getContacts();
        assertFalse(account.removeContact(test_person));
        String after = account.getContacts();
        assertEquals(before, after);
    }

    /**
     * Tests the getContacts function.
     */
    @Test
    @DisplayName("Get the contacts as a string")
    void testGetContacts(){
        String expected = """
PeterPeter | john.smith@aol.com | 5555555555555555
Sarahjohnson | Taskmgr_dot_exe@gmail.com | 4166942069
JohnSmith | joe.mama@joe.io | 6471234567
                """ ;

        String before = account.getContacts();
        assertEquals(expected, before);
    }



}