package entity.accounts;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
/* TODO: These seem to be outdated. Please check. I believe that that it should be entity.datafiles.*
import entity.datafile.Email;
import entity.datafile.Name;
import entity.datafile.Phone;
*/
import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

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
        assertFalse(account.checkContacts(different));
        assertTrue(account.checkContacts(test_person));
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

        assertFalse(account.removeContact(test_person));
        assertFalse(account.checkContacts(test_person));
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

        Set<ProfileType> before = account.getContacts();
        assertFalse(account.removeContact(test_person));
        Set<ProfileType> after = account.getContacts();
        assertEquals(before, after);
    }

    /**
     * Tests the getContacts function on an empty contact list
     */
    @Test
    @DisplayName("Get the contacts as a string for an empty contact list")
    void testGetContactsEmpty(){
        CorporateAccount new_account = new CorporateAccount();
        Set<ProfileType> expected = Collections.emptySet();

        Set<ProfileType> before = new_account.getContacts();
        assertEquals(expected, before);
    }

    /**
     * Tests the addAffiliation function
     */
    @Test
    @DisplayName("Add an affiliation")
    void testAddAffiliation(){
        Organization test_org = new Organization(
                "StevenInc",
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "JonnyBoi"
        );

        assertTrue(account.addAffiliation(test_org));
        assertTrue(account.getAffiliations().contains(test_org));
    }

    /**
     * Tests the removeAffiliation function
     */
    @Test
    @DisplayName("Remove an affiliation")
    void testRemoveAffiliation(){
        Organization test_org = new Organization(
                "StevenInc",
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "JonnyBoi"
        );

        account.addAffiliation(test_org);

        assertFalse(account.removeAffiliation(test_org));
        assertFalse(account.getAffiliations().contains(test_org));
    }

    /**
     * Tests the removeAffiliation function an org that is not affiliated with the account
     */
    @Test
    @DisplayName("remove an affiliation that does not exist")
    void testRemoveAffiliationNotExists(){
        Organization test_org = new Organization(
                "StevenInc",
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "JonnyBoi"
        );

        assertFalse(account.getAffiliations().contains(test_org));
        assertFalse(account.removeAffiliation(test_org));
    }

    /**
     * Tests the getAffiliations function on an empty affiliations list
     */
    @Test
    @DisplayName("Get the affiliations as a string for an empty affiliations list")
    void testGetAffiliationsEmpty(){
        CorporateAccount new_account = new CorporateAccount();
        Set<ProfileType> expected = Collections.emptySet();

        Set<ProfileType> before = new_account.getAffiliations();
        assertEquals(expected, before);
    }

}