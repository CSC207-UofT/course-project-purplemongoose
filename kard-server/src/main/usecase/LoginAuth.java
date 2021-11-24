package usecase;

import database.gateway.AuthenticationGateway;

import java.io.IOException;

/**
 * This class contains use cases for login authentication
 */
public class LoginAuth {
    private AuthenticationGateway authGateway;

    public LoginAuth(boolean inMemory) {
        if (inMemory) {
            authGateway = new AuthenticationGateway();
        } else {
            try {
                authGateway = new AuthenticationGateway("./data/mainframe.db");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Takes a username and password and queries it against the account database. If either of the fields are blank
     * or do not exist in the database, then the login request is denied.
     *
     * @param username string for username
     * @param password string for password
     * @return if the password for the username matches the stored password.
     */
    public boolean requestLogin(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return false; // prevent empty fields
        } else {
            return authGateway.authAccountData(username, password);
        }
    }
}
