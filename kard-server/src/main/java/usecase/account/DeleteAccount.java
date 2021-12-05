package usecase.account;

import database.gateway.AccountGateway;

import java.io.IOException;

public class DeleteAccount {
    private AccountGateway accountGateway;

    public DeleteAccount (boolean inMemory) {
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

    public boolean delete(String accountUsername) {
        return accountGateway.deleteAccountData(accountUsername);
    }
}
