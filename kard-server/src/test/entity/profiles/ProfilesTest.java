package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Person, Organization and Business classes
 *
 * @see Person
 */

public class ProfilesTest {
    Person profile;

    @BeforeEach
    void setUp() {

        Phone phone = new Phone("6475552401");
        Email email = new Email("jon666@gmail.com");
        Name name = new Name("George", "Michael", "she/her", "");
        profile = new Person(name, phone, email, "george2");

    }
    @AfterEach
    void tearDown() {
    }

    /**
     * Tests for the name getters of profiles as well as the pronoun getter for Person
     * and owner name getter for Business
     */

    @Test
    @DisplayName("Get names of Profiles")
    void testGetName(){
        String profileName = profile.getName();
        assertEquals("George Michael", profileName);
    }

    /**
     * Tests for the phone getters of profiles
     */
    @Test
    @DisplayName("Get phones of Profiles")
    void testGetPhone(){
        String profilePhone = profile.getPhone();
        assertEquals("6475552401", profilePhone);
    }

    /**
     * Tests for the email getters of profiles
     */
    @Test
    @DisplayName("Get emails of Profiles")
    void testGetEmail(){
        String profileEmail = profile.getEmail();
        assertEquals("jon666@gmail.com", profileEmail);

    }

    /**
     * Tests for the username getters of profiles
     */
    @Test
    @DisplayName("Get usernames of Profiles")
    void testGetUsername(){
        String profileUsername = profile.getUsername();
        assertEquals("george2", profileUsername);

    }
}