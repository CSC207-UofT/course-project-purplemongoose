package usecase.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
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


public class ViewProfileTest {
    private static ViewProfile viewProfile;
    private static CreateAccount createAccount;
    private static CreateProfile createProfile;

    @BeforeAll
    static void setUp() {
        try {
            createAccount = new CreateAccount(
                    new AccountGateway("./ViewProfile/mainframe.db"));
            viewProfile = new ViewProfile(
                    new ProfileGateway("./ViewProfile/mainframe.db"));
            createProfile = new CreateProfile(
                    new ProfileGateway("./ViewProfile/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./ViewProfile"));
    }

    /**
     * Tests if the right Profile object is returned
     */
    @Test
    @DisplayName("Check for correct viewed profile ")
    void ViewProfileSuccess() {
        createAccount.newPersonalAccount("john", "123");
        createProfile.newPerson("john", "John", "Smith",
                "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");
        Person expected = new Person(
                new Name("John", "Smith", "He/Him", "Dr."),
                new Phone("6471234567"),
                new Email("joe.mama@joe.io"), "john");
        assertEquals(expected, viewProfile.viewProfile("john"));
    }
}
