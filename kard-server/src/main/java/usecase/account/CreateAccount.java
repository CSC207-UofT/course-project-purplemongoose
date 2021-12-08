package usecase.account;

import database.gateway.AccountGateway;
import entity.accounts.PersonalAccount;

/**
 * Class for creating a new Account and storing it in the database.
 */
public class CreateAccount {
    private final AccountGateway accountGateway;

    public CreateAccount (AccountGateway ag) {
        this.accountGateway = ag;
    }

    /**
     * Creates a new personal account and calls the AccountGateway to add it to the database
     *
     * @param username string for account username
     * @param password string for account password
     * @return if the account was successfully created
     */
    public boolean newPersonalAccount(String username, String password) {
        return accountGateway.insertAccountData(username, password, new PersonalAccount());
    }
}
