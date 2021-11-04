package viewmodel;

/**
 * Class which some JSON objects are serialized into from HTTP POST or GET requests. This one in particular contains
 * the account's username.
 */
public class BasicRequest {
    public String accountUsername;

    /**
     * Getter for the account username
     * @return the account username
     */
    public String getAccountUsername() {
        return accountUsername;
    }
}
