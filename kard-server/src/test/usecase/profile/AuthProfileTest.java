package usecase.profile;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class AuthProfileTest {
    private static AuthProfile authProfile;
    private static CreateAccount createAccount;
    private static CreateProfile createProfile;

    @BeforeAll
    static void setUp() {
        try {
            createAccount = new CreateAccount(
                    new AccountGateway("./AuthProfile/mainframe.db"));
            authProfile = new AuthProfile(
                    new ProfileGateway("./AuthProfile/mainframe.db"));
            createProfile = new CreateProfile(
                    new ProfileGateway("./AuthProfile/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./AuthProfile"));
    }

    /**
     * Tests an account that has a profile
     */
    @Test
    @DisplayName("Check that the account has a profile")
    void CheckForProfileSuccess() {
        createAccount.newPersonalAccount("john", "123");
        createProfile.newPerson("john", "John", "Smith",
                "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");
        assertFalse(authProfile.checkForProfile("john"));
    }

    /**
     * Tests an account that has no profile
     */
    @Test
    @DisplayName("Check that the account has no profile")
    void CheckForProfileFailure() {
        createAccount.newPersonalAccount("sarah", "123");
        assertTrue(authProfile.checkForProfile("sarah"));
    }
}
