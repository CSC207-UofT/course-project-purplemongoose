package entity.profiles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

/**
 * A class for testing the Person classe
 *
 * @see Person
 */

public class PersonTest {
    Person profile;

    @BeforeEach
    void setUp() {
        Phone phone = new Phone("6475552401");
        Email email = new Email("jon666@gmail.com");
        Name name = new Name("George", "Michael", "she/her", "Ms.");
        profile = new Person(name, phone, email, "george2");
    }

    /**
     * Tests for the name getters of profiles as well as the pronoun getter for Person
     * and owner name getter for Business
     */

    @Test
    @DisplayName("Get names of Profiles")
    void testGetName(){
        String profileName = profile.getName();
        assertEquals("Ms. George Michael", profileName);
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

    /**
     * Tests for the pronouns getters of profiles
     */
    @Test
    @DisplayName("Get pronouns of Profiles")
    void testGetPronouns(){
        String profileUsername = profile.getPronouns();
        assertEquals("she/her", profileUsername);
    }

    /**
     * Tests for the equals override of profiles
     */
    @Test
    @DisplayName("Test the equals override")
    void testCheckEqualObjects(){
        Phone phone1 = new Phone("12345");
        Phone phone2 = new Phone("12345");
        assertEquals(phone1, phone2);
    }

    /**
     * Tests for the toString method of profiles
     */
    @Test
    @DisplayName("Test the toString override")
    void testToString(){
        String strRepresention = profile.toString();
        String expected = "Person{name=Name[first=George, last=Michael, pronouns=she/her, titles=Ms.], " +
                "phone=Phone[phone=6475552401], email=Email[email=jon666@gmail.com], username='george2'}";
        assertEquals(expected, strRepresention);
    }
}