package request;

/**
 * Class which some JSON objects are serialized into from HTTP POST or GET requests. This one in particular contains
 * the account's username and the contact's username.
 */
public class ContactRequest {
    public String accountUsername;
    public String contactUsername;

    /**
     * Getter for the account username
     * @return the account username
     */
    public String getAccountUsername() {
        return accountUsername;
    }

    /**
     * Getter for the contact username
     * @return the contact username
     */
    public String getContactUsername() {
        return contactUsername;
    }
}
