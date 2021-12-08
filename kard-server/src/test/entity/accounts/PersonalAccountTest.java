package entity.accounts;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the PersonalAccount class
 *
 * @see PersonalAccount
 */
class PersonalAccountTest {

    PersonalAccount account;

    @BeforeEach
    void setUp() {
        account = new PersonalAccount();

        // Some test contacts for the account
        account.addContact(new Person(
                new Name("John", "Smith", "He/Him", "Doctor"),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "Johnnyboi69"
        ));
        account.addContact(new Person(
                new Name("Sarah", "johnson", "", ""),
                new Phone("4166942069"),
                new Email("Taskmgr_dot_exe@gmail.com"),
                "NotABot"
        ));
        account.addContact(new Person(
                new Name("Peter", "Peter", "They/Them", ""),
                new Phone("5555555555555555"),
                new Email("john.smith@aol.com"),
                "TheRealJohnSmith"
        ));
    }

    /**
     * Tests adding a contact to the Account
     */
    @Test
    @DisplayName("Add a contact")
    void testAddContact(){
        Person test_person = new Person(
                new Name("Steven", "Test", "", ""),
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "TheRealJohnSmith"
        );
        Person different =  new Person(
                new Name("Steven", "Test", "", ""),
                new Phone("6663636363"),
                new Email("not_steven@y8.com"),
                "TheFakeJohnSmith"
        );

        account.addContact(test_person);
        assertFalse(account.checkContacts(different));
        assertTrue(account.checkContacts(test_person));
    }
}