package usecase;

import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import entity.accounts.CorporateAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usecase.account.CreateAccount;
import database.gateway.AccountGateway;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the CreateAccount class
 *
 * @see CreateAccount
 */

class CreateAccountTest {

    CreateAccount createAccount;
    AccountGateway ag;

    @BeforeEach
    void setUp() throws IOException {
         this.createAccount = new CreateAccount(false);
         this.ag = new AccountGateway("./data/mainframe.db");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Create a personal account in the database")
    void newPersonalAccount() {
        boolean result = createAccount.newPersonalAccount("spongebob", "patrick123");
        if (result == true){
            Account acc = ag.getAccountData("spongebob");
            assertTrue(acc != null);
            PersonalAccount acc2 = (PersonalAccount) acc;
            assertTrue(acc instanceof PersonalAccount);
        }

    }

    @Test
    @DisplayName("Create a corporate account in the database")
    void newCorporateAccount() {
        boolean result = createAccount.newCorporateAccount("Nike", "frankocean");
        if (result == true){
            Account acc = ag.getAccountData("Nike");
            assertTrue(acc != null);
            CorporateAccount acc2 = (CorporateAccount) acc;
            assertTrue(acc instanceof CorporateAccount);
        }
    }
}
