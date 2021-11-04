package request;


/**
 * Class which some JSON objects are serialized into from HTTP POST or GET requests. This one in particular contains
 * the all the information needed to create a personal profile.
 */
public class PersonalProfileRequest {
    public String accountUsername;
    public String firstName;
    public String lastName;
    public String title;
    public String pronoun;
    public String phone;
    public String email;

    /**
     * Getter for the account username
     * @return the account username
     */
    public String getAccountUsername() {
        return accountUsername;
    }

    /**
     * Getter for the first name
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the preferred pronoun
     * @return the pronoun
     */
    public String getPronoun() {
        return pronoun;
    }

    /**
     * Getter for the preferred title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the phone number
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Getter for the email
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
