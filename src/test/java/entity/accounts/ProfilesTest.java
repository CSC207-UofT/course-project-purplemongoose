package entity.accounts;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Business;
import entity.profiles.Organization;
import entity.profiles.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Person, Organization and Business classes
 *
 * @see Person
 * @see Organization
 * @see Business
 */

public class ProfilesTest {
    Person profile;
    Business biz;
    Organization org;

    @BeforeEach
    void setUp() {

        Phone phone = new Phone("6475552401");
        Email email = new Email("jon666@gmail.com");
        Name name = new Name("George", "Michael", "she/her");
        profile = new Person(name, phone, email, "george2");

        Phone phone2 = new Phone("4527386487");
        Email email2 = new Email("mcdonalds@yahoo.com");
        biz = new Business("Mc Donald's", phone2, email2, "mcdonalds", name);

        Phone phone3 = new Phone("8347586347");
        Email email3 = new Email("adidas@mail.com");
        org = new Organization("Adidas", phone3, email3, "adidas");

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
        assertEquals("George Michael (she/her)", profileName);

        String profilePronouns = profile.getPronouns();
        assertEquals("she/her", profilePronouns);

        String bizName = biz.getName();
        assertEquals("Mc Donald's", bizName);

        String bizOwnerName = biz.getOwnerName();
        assertEquals("George Michael (she/her)", bizOwnerName);

        String orgName = org.getName();
        assertEquals("Adidas", orgName);
    }

    /**
     * Tests for the phone getters of profiles
     */
    @Test
    @DisplayName("Get phones of Profiles")
    void testGetPhone(){

        String profilePhone = profile.getPhone();
        assertEquals("6475552401", profilePhone);

        String bizPhone = biz.getPhone();
        assertEquals("4527386487", bizPhone);

        String orgPhone = org.getPhone();
        assertEquals("8347586347", orgPhone);
    }

    /**
     * Tests for the email getters of profiles
     */
    @Test
    @DisplayName("Get emails of Profiles")
    void testGetEmail(){

        String profileEmail = profile.getEmail();
        assertEquals("jon666@gmail.com", profileEmail);

        String bizEmail = biz.getEmail();
        assertEquals("mcdonalds@yahoo.com", bizEmail);

        String orgEmail = org.getEmail();
        assertEquals("adidas@mail.com", orgEmail);
    }







}