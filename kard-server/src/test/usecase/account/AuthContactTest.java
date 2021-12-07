package usecase.account;

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
import usecase.profile.CreateProfile;

import java.io.File;
import java.io.IOException;

public class AuthContactTest {
    private static AuthContact authContact;
    private static CreateAccount createAccount;
    private static ModifyContact modifyContact;
    private static CreateProfile createProfile;

    @BeforeAll
    static void setUp() {
        try {
            createAccount = new CreateAccount(
                    new AccountGateway("./AuthContact/mainframe.db"));
            authContact = new AuthContact(
                    new AccountGateway("./AuthContact/mainframe.db"),
                    new ProfileGateway("./AuthContact/mainframe.db"));
            modifyContact = new ModifyContact(
                    new AccountGateway("./AuthContact/mainframe.db"),
                    new ProfileGateway("./AuthContact/mainframe.db"));
            createProfile = new CreateProfile(
                    new ProfileGateway("./AuthContact/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createAccount.newPersonalAccount("john", "123");
        createProfile.newPerson("john", "John", "Smith",
                "He/Him", "Dr.", "6471234567", "joe.mama@joe.io");
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./AuthContact"));
    }

    /**
     * Tests if the contacts exists
     */
    @Test
    @DisplayName("Checks for an existing contact")
    void contactExists() {
        createAccount.newPersonalAccount("user1", "123");
        modifyContact.addContact("user1", "john");
        assertTrue(authContact.checkForContact("user1", "john"));
    }

    /**
     * Tests if the contact does not exist
     */
    @Test
    @DisplayName("Checks for an non-existent contact")
    void contactDoesNotExists() {
        createAccount.newPersonalAccount("user2", "123");
        assertFalse(authContact.checkForContact("user2", "john"));
    }
}
