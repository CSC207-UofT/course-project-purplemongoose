package usecase.start;

import database.gateway.AccountGateway;
import database.gateway.AuthenticationGateway;
import entity.accounts.PersonalAccount;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the AuthLogin class
 *
 * @see AuthLogin
 */

class AuthLoginTest {
    private static AuthLogin authLogin;

    @BeforeAll
    static void setUp() {
        AccountGateway ag = null;
        try {
            ag = new AccountGateway("./AuthLogin/mainframe.db");
            authLogin = new AuthLogin(new AuthenticationGateway("./AuthLogin/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PersonalAccount account = new PersonalAccount();
        assert ag != null;
        ag.insertAccountData("spongebob", "patrick123", account);

    }

    @AfterAll
    static void tearDown() {
        // Deleting the temporary database after testing
        File tempDB = new File("./AuthLogin/mainframe.db");
        if (tempDB.delete()) {
            File tempFolder = new File("./AuthLogin/");
            tempFolder.delete();
        }
    }

    /**
     * Tests requesting a login with a blank username
     */
    @Test
    @DisplayName("Request login with a blank username")
    void requestLoginBlankUser() {
        boolean blankUser = authLogin.requestLogin("", "patrick123");
        assertFalse(blankUser);
    }

    /**
     * Tests requesting a login with a blank password
     */
    @Test
    @DisplayName("Request login with a blank password")
    void requestLoginBlankPassword() {
        boolean blankPassword = authLogin.requestLogin("spongebob", "");
        assertFalse(blankPassword);
    }

    /**
     * Tests requesting a login with a non-existent username
     */
    @Test
    @DisplayName("Request login with a non-existent username")
    void requestLoginUsernameNotExist() {
        boolean nonExistentUsername = authLogin.requestLogin("sponge", "patrick123");
        assertFalse(nonExistentUsername);
    }

    /**
     * Tests requesting a login with an incorrect password
     */
    @Test
    @DisplayName("Request login with an incorrect password")
    void requestLoginWrongPassword() {
        boolean wrongPassword = authLogin.requestLogin("spongebob", "patrick");
        assertFalse(wrongPassword);
    }

    /**
     * Tests requesting a login with correct login information
     */
    @Test
    @DisplayName("Request login with correct login information")
    void requestLoginSuccessful() {
        boolean successfulLogin = authLogin.requestLogin("spongebob", "patrick123");
        assertTrue(successfulLogin);
    }
}