package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;


public class PersonMemento extends Memento implements Serializable{
    @Serial
    private static final long serialVersionUID = 485037157128551751L;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final String username;


    public PersonMemento(Name name, Phone phone, Email email, String username) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    /**
     * Getter for the profile memento full name (which includes titles and pronouns)
     * @return string representation of the full name
     */
    public String getName() {
        return name.getFullName();
    }

    /**
     * Getter for the profile memento pronouns
     * @return string representation of the pronouns
     */
    public String getPronouns() {
        return name.getPronouns();
    }

    /**
     * Getter for the profile memento name
     * @return string representation of the name
     */
    public Name getOriginalName() {
        return this.name;
    }

    /**
     * Getter for the profile memento phone
     * @return string representation of the phone number
     */
    public String getPhone() {
        return phone.getPhone();
    }

    /**
     * Getter for the profile memento email
     * @return string representation of the email
     */
    public String getEmail() {
        return email.getEmail();
    }

    /**
     * Getter for the profile memento username
     * @return string representation of the username
     */
    public String getUsername() {
        return this.username;
    }

}
