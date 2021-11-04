package entity.datafile;

import java.io.Serializable;

public class Email implements Serializable {

    /** Class that stores information about the Person's email address
     *
     */

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
