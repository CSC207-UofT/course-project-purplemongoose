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

public class ModifyContactTest {
    private static AuthContact authContact;
    private static CreateAccount createAccount;
    private static ModifyContact modifyContact;
    private static CreateProfile createProfile;

    @BeforeAll
    static void setUp() {
        try {
            createAccount = new CreateAccount(
                    new AccountGateway("./ModifyContact/mainframe.db"));
            modifyContact = new ModifyContact(
                    new AccountGateway("./ModifyContact/mainframe.db"),
                    new ProfileGateway("./ModifyContact/mainframe.db"));
            authContact = new AuthContact(
                    new AccountGateway("./ModifyContact/mainframe.db"),
                    new ProfileGateway("./ModifyContact/mainframe.db"));
            createProfile = new CreateProfile(
                    new ProfileGateway("./ModifyContact/mainframe.db"));
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
        FileUtils.forceDelete(new File("./ModifyContact"));
    }

    /**
     * Tests adding a contact to an account
     */
    @Test
    @DisplayName("Add a profile to an account")
    void addContactSuccessful() {
        createAccount.newPersonalAccount("user1", "123");
        modifyContact.addContact("user1", "john");
        assertTrue(authContact.checkForContact("user1", "john"));
    }

    /**
     * Tests removing a contact from an account
     */
    @Test
    @DisplayName("Remove a contact from an account")
    void removeContactSuccess() {
        createAccount.newPersonalAccount("user2", "123");
        modifyContact.addContact("user2", "john");
        modifyContact.removeContact("user2", "john");
        assertFalse(authContact.checkForContact("user2", "john"));
    }
}
