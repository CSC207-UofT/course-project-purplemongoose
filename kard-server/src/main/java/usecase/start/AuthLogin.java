package usecase.start;

import database.gateway.AuthenticationGateway;

public class AuthLogin {
    private final AuthenticationGateway authGateway;

    public AuthLogin(AuthenticationGateway ag) {
        this.authGateway = ag;
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
        return authGateway.authAccountData(username, password);
    }
}
