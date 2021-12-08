package usecase.profile;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Person;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import database.SQLite.SQLiteDataBaseManager;
import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import usecase.account.CreateAccount;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class RestoreProfileTest {
    private static CreateAccount createAccount;
    private static CreateProfile createProfile;
    private static RestoreProfile restoreProfile;
    private static UpdateProfile updateProfile;
    private static ViewProfile viewProfile;
    private static ProfileGateway pg;

    @BeforeAll
    static void setUp() {
        try {
            pg = new ProfileGateway("./RestoreProfile/mainframe.db");
            createAccount = new CreateAccount(
                    new AccountGateway("./RestoreProfile/mainframe.db"));
            createProfile = new CreateProfile(
                    pg);
            restoreProfile = new RestoreProfile(
                    pg
            );
            updateProfile = new UpdateProfile(
                    pg);
            viewProfile = new ViewProfile(
                    pg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./RestoreProfile"));
    }

    /**
     * Tests restoring an account's profile to a previous state
     */
    @Test
    @DisplayName("Create a profile, update it, then change it to its previous state")
    void CheckForProfileRestore() throws InterruptedException {
        createAccount.newPersonalAccount("john", "123");
        createProfile.newPerson("john", "John", "Smith", "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");
        TimeUnit.SECONDS.sleep(1);
        updateProfile.updatePersonProfile("john", "Johnny", "Cash", "He/Him", "Mr.", "6471223234", "johnny@cash.com");
        TimeUnit.SECONDS.sleep(1);
        updateProfile.updatePersonProfile("john", "Bob", "Bruh", "He/They", "Mr.", "123456", "bruh@bruh.com");
        TimeUnit.SECONDS.sleep(1);
        restoreProfile.restorePersonProfile("john", 0);
        Person expected = new Person(
                new Name("John", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"),
                "john");
        assertEquals(expected, viewProfile.viewProfile("john"));
    }

}