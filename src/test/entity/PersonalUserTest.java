package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalUserTest {

    // Initialize a user
    PersonalUser user = new PersonalUser();

    // Initialize a person to add to contacts
    Person newUser = new Person("Name", "1234567890", "name@name.com");

    @Test
    void addContact() {
        boolean added = user.addContact(newUser);
        assertTrue(added);

        added = user.addContact(newUser);
        assertFalse(added);
    }

    @Test
    void removeContact() {
        user.addContact(newUser);
        boolean removed = user.removeContact(newUser);
        assertTrue(removed);

        removed = user.removeContact(newUser);
        assertFalse(removed);
    }

    @Test
    void getContact() {
        user.addContact(newUser);

        String expected = "Name | 1234567890 | name@name.com\n";

        assertEquals(expected, user.getContact());
    }
}