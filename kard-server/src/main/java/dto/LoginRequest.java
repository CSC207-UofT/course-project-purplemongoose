package dto;

public class LoginRequest {
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
