package usecase;

import database.AccountGateway;

public class LoginAuth {
    AccountGateway ag = new AccountGateway();

    public boolean requestLogin(String username, String password) {
        return this.ag.authAccountData(username, password);
    }

}
