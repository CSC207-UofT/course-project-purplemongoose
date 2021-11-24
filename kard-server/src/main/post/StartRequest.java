package post;

/**
 * Class which some JSON objects are serialized into from HTTP POST or GET requests. This one in particular contains
 * the account's username and password for login or signup.
 */
public class StartRequest {
    public String accountUsername;
    public String accountPassword; // should be hashed already

    /**
     * Getter for the account username
     * @return the account username
     */
    public String getAccountUsername() {
        return accountUsername;
    }

    /**
     * Getter for the account password
     * @return the account password
     */
    public String getAccountPassword() {
        return accountPassword;
    }
}
