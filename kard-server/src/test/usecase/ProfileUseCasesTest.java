package usecase;

import database.gateway.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Phone;
import entity.profiles.Organization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the ProfileUseCases class
 *
 * @see ProfileUseCases
 */

public class ProfileUseCasesTest {
    Organization org;
    ProfileUseCases puc;

    /**
     * Necessary to Query test dbs
     */
    private class pucTest extends ProfileUseCases {
        public pucTest() {
            this.pg = new ProfileGateway() {
                @Override
                public Connection databaseConnect() {
                    String mfLocation = "src/test/data/profile.db";
                    File file = new File(mfLocation);
                    Connection conn = null;
                    if (file.exists()) {
                        try {
                            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    else { // if such a db doesn't exist, create one and add a table
                        try {
                            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
                            createProfileTable(conn);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    return conn; // return a connection for other methods to use
                }
            };
        }
    }

    @BeforeEach
    void setUp() {

        Phone phone = new Phone("8347586347");
        Email email = new Email("adidas@mail.com");
        org = new Organization("Adidas", phone, email, "adidas");
        puc = new pucTest();
        puc.pg.addProfileData("adidas", org);
    }

    @AfterEach
    void tearDown() {
        // reset the database.
        Connection con = puc.pg.databaseConnect();
        try {
            con.prepareStatement("DROP TABLE \"profiles\"").executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        puc.pg.createProfileTable(con);
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
