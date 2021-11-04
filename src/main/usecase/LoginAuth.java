package usecase;

import state.AppState;
import database.AccountGateway;

public class LoginAuth {
    /**
     * Authenticates login credentials, initializes the account gateway for this user
     */
    AccountGateway ag = new AccountGateway();

    /**
     * Checks if a UUID has already been associated to this username
     *
     * If there is already an associated username, then return ture and proceed.
     *
     * @param username string for username
     * @param password string for password
     * @return true if username, UUID, and password all match
     */
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
