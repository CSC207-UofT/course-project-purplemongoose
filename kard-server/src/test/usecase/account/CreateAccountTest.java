package usecase.account;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import database.gateway.AccountGateway;
import database.gateway.AuthenticationGateway;

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
    static void tearDown() {
        // Deleting the temporary database after testing
        File tempDB = new File("./CreateAccount/mainframe.db");
        if (tempDB.delete()) {
            File tempFolder = new File("./CreateAccount/");
            tempFolder.delete();
        }
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
