package usecase.profile;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import database.SQLite.SQLiteDataBaseManager;
import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Person;
import usecase.account.CreateAccount;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UpdateProfileTest {
    private static CreateAccount createAccount;
    private static CreateProfile createProfile;
    private static UpdateProfile updateProfile;
    private static ViewProfile viewProfile;

    @BeforeAll
    static void setUp() {
        try {
            createAccount = new CreateAccount(
                    new AccountGateway("./UpdateProfile/mainframe.db"));
            createProfile = new CreateProfile(
                    new ProfileGateway("./UpdateProfile/mainframe.db"));
            updateProfile = new UpdateProfile(
                    new ProfileGateway("./UpdateProfile/mainframe.db"));
            viewProfile = new ViewProfile(
                    new ProfileGateway("./UpdateProfile/mainframe.db"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./UpdateProfile"));
    }

    /**
     * Tests updating the profile with edits to the profile
     */
    @Test
    @DisplayName("Change the profile information to something else")
    void updateAccountProfile() {
        createAccount.newPersonalAccount("john", "123");
        createProfile.newPerson("john", "John", "Smith", "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");
        updateProfile.updatePersonProfile("john", "Johnny", "Cash", "He/Him", "Mr.", "6471223234", "johnny@cash.com");
        Person expected = new Person(
                new Name("Johnny", "Cash", "He/Him", "Mr."),
                new Phone("6471223234"),
                new Email("johnny@cash.com"),
                "john");
        assertEquals(expected, viewProfile.viewProfile("john"));
    }

    /**
     * Tests updating when an initial one has not been initialized yet
     */
    @Test
    @DisplayName("Change the profile without having an initial one")
    void updateAccountNoProfile() {
        createAccount.newPersonalAccount("peter", "123");

        assertFalse(updateProfile.updatePersonProfile("peter", "Peter", "Peter",
                "He/Him", "Mr.", "6475534937", "john.smith@aol.com"));
    }
}
