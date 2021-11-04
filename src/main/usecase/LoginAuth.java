package usecase;

import database.AccountGateway;


/**
 * This class contains use cases for login authentication
 */
public class LoginAuth {
    AccountGateway ag = new AccountGateway();

    /**
     * Takes a username and password and queries it against the account database. If either of the fields are blank
     * or do not exist in the database, then the login request is denied.
     * @param username the username of the account
     * @param password the password of the account
     * @return whether the account successfully logins in
     */
    public boolean requestLogin(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return false; // prevent empty fields
        }
        else {
            return ag.authAccountData(username, password) != null;
        }
    }
}
