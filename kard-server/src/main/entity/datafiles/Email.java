package entity.datafiles;

import java.io.Serializable;

/**
 * Class that stores information about the Person's email address
 */
public class Email implements Serializable {

    private final String email;

    // The setter
    public Email(String email) {
        this.email = email;
    }

    // The getter
    public String getEmail() {
        return email;
    }
}
