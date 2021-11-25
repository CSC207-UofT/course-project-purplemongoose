package usecase.account;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.accounts.CorporateAccount;
import entity.accounts.PersonalAccount;

import java.io.IOException;

public class CreateAccount {
    private AccountGateway accountGateway;

    public CreateAccount (boolean inMemory) {
        if (inMemory) {
            accountGateway = new AccountGateway();
        } else {
            try {
                accountGateway = new AccountGateway("./data/mainframe.db");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a new personal account and calls the AccountGateway to add it to the database
     * @param username string for account username
     * @param password string for account password
     * @return if the account was successfully created
     */
    public boolean newPersonalAccount(String username, String password) {
        return accountGateway.insertAccountData(username, password, new PersonalAccount());
    }

    /**
     * Creates a new corporate account and calls the AccountGateway to add it to the database
     * @param username string for account username
     * @param password string for account password
     * @return if the account was successfully created
     */
    public boolean newCorporateAccount(String username, String password) {
        return accountGateway.insertAccountData(username, password, new CorporateAccount());
    }
}
