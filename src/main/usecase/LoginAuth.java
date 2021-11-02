package usecase;

import state.AppState;
import database.AccountGateway;

public class LoginAuth {
    AccountGateway ag = new AccountGateway();

    public boolean requestLogin(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return false;
        }
        else {
            String currentUsername = ag.authAccountData(username, password);
            if (currentUsername == null) {
                return false;
            }
            else {
                AppState.setCurrentUsername(currentUsername);
                return true;
            }
        }
    }
}
