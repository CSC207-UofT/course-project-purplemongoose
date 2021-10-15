package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalUserTest {

    // Initialize a user
    private final PersonalUser user = new PersonalUser();

    // Initialize a person to add to contacts
    private final Person newUser = new Person("Name", "1234567890", "name@name.com");

    @Test
    void addContactNotExisting() {
        boolean added = user.addContact(newUser);
        assertTrue(added);
    }

    @Test
    void addContactExisting() {
        user.addContact(newUser);
        boolean added = user.addContact(newUser);
        assertFalse(added);
    }

    @Test
    void removeContactExisting() {
        user.addContact(newUser);
        boolean removed = user.removeContact(newUser);
        assertTrue(removed);
    }

    @Test
    void removeContatctNotExisting() {
        user.addContact(newUser);
        user.removeContact(newUser);
        boolean removed = user.removeContact(newUser);
        assertFalse(removed);
    }

    @Test
    void getContact() {
        user.addContact(newUser);

        String expected = "Name | 1234567890 | name@name.com\n";

        assertEquals(expected, user.getContact());
    }
}