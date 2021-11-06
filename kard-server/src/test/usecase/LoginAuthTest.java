package usecase;

import database.AccountGateway;
import entity.accounts.PersonalAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginAuthTest {

    LoginAuth loginAuth;

    @BeforeEach
    void setUp() {
        AccountGateway ag = new AccountGateway();
        PersonalAccount account = new PersonalAccount();
        ag.insertAccountData("spongebob", "patrick123", account);
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
        boolean blankUser = loginAuth.requestLogin("", "patrick123");
        assertFalse(blankUser);
    }

    /**
     * Tests requesting a login with a blank password
     */
    @Test
    @DisplayName("Request login with a blank password")
    void requestLoginBlankPassword() {
        boolean blankPassword = loginAuth.requestLogin("spongebob", "");
        assertFalse(blankPassword);
    }

    /**
     * Tests requesting a login with a non-existent username
     */
    @Test
    @DisplayName("Request login with a non-existent username")
    void requestLoginUsernameNotExist() {
        boolean nonExistentUsername = loginAuth.requestLogin("sponge", "patrick123");
        assertFalse(nonExistentUsername);
    }
}