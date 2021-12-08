package usecase.account;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import database.gateway.AccountGateway;
import database.gateway.AuthenticationGateway;
import database.SQLite.SQLiteDataBaseManager;

import java.io.File;
import java.io.IOException;

/**
 * A class for testing the CreateAccount class
 *
 * @see CreateAccount
 */
public class CreateAccountTest {
    private static AuthenticationGateway authenticationGateway;
    private static CreateAccount createAccount;

    @BeforeAll
    static void setUp() {
        try {
            authenticationGateway = new AuthenticationGateway("./CreateAccount/mainframe.db");
            createAccount = new CreateAccount(new AccountGateway("./CreateAccount/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Close the connection and delete the directory
        SQLiteDataBaseManager.close();
        FileUtils.forceDelete(new File("./CreateAccount"));
    }

    /**
     * Tests requesting a new account be created and checks if the new account is present in the database
     */
    @Test
    @DisplayName("Create an account and check if it exists")
    void createNewAccount() {
        boolean created = createAccount.newPersonalAccount("test-account", "1234");
        assertTrue(created);
        assertTrue(authenticationGateway.authAccountData("test-account", "1234"));
    }

}
