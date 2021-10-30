package usecase;

import state.AppState;
import database.AccountGateway;

public class LoginAuth {
    AccountGateway ag = new AccountGateway();

    public boolean requestLogin(String username, String password) {
        String uuid = ag.authAccountData(username, password);
        if (uuid == null) {
            return false;
        }
        else {
            AppState.setCurrentUUID(uuid);
            return true;
        }

    }

}
