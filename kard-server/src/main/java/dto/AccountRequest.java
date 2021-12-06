package dto;

/**
 * Class which some JSON objects are serialized into from HTTP POST or GET requests. This one in particular contains
 * the account's username and password.
 */
public class AccountRequest {
    public String accountUsername;
    public String accountPassword;

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
