package entity.accounts;

import database.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Phone;
import entity.profiles.Business;
import entity.profiles.Organization;
import entity.profiles.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usecase.ProfileUseCases;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the ProfileUseCases class
 *
 * @see ProfileUseCases
 */

public class ProfileUseCasesTest {
    Organization org;
    ProfileUseCases puc;
    ProfileGateway pg = new ProfileGateway();

    @BeforeEach
    void setUp() {

        Phone phone = new Phone("8347586347");
        Email email = new Email("adidas@mail.com");
        org = new Organization("Adidas", phone, email, "adidas");
        this.pg.insertProfileData("adidas", org);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests the checkForProfile method for a profile that DOES exists in the database
     */
    @Test
    @DisplayName("Check for a profile that exists in the database")
    void testCheckForProfileExists(){

        // assuming checkForProfile returns true if the profile DOES exist
        assertTrue(puc.checkForProfile("adidas"));

    }

    /**
     * Tests the checkForProfile method for a profile that DOES NOT exist in the database
     */
    @Test
    @DisplayName("Check for a profile that does not exist in the database")
    void testCheckForProfileNotExists(){

        // assuming checkForProfile returns true if the profile DOES exist
        assertFalse(puc.checkForProfile("abcd"));

    }

    /**
     * Tests the createNewPerson method
     */
    @Test
    @DisplayName("Create a new Person")
    void testCreateNewPerson(){

        puc.createNewPerson("tyler",
                "Tyler", "Baudelaire", "he/him",
                "Mr.", "39478859987", "tyler@gmail.com");

        // assuming checkForProfile returns true if the profile DOES exist
        assertTrue(puc.checkForProfile("tyler"));

    }

    /**
     * Tests the createNewOrganization method
     */
    @Test
    @DisplayName("Create a new Organization")
    void testCreateNewOrganization(){

        puc.createNewOrganization("uoft", "University of Toronto",
                "8939547", "uoft@mail.utoronto.ca");

        // assuming checkForProfile returns true if the profile DOES exist
        assertTrue(puc.checkForProfile("uoft"));

    }
}
