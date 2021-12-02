package usecase;

import database.gateway.AccountGateway;
import entity.accounts.PersonalAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usecase.start.AuthLogin;
import entity.accounts.Account;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the AuthLogin class
 *
 * @see AuthLogin
 */

class AuthLoginTest {

    AuthLogin authLogin;

    @BeforeEach
    void setUp() {
        AccountGateway ag = new AccountGateway();
        PersonalAccount account = new PersonalAccount();
        ag.insertAccountData("spongebob", "patrick123", account);
        this.authLogin = new AuthLogin(false);
    }

    @AfterEach
    void tearDown() {
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