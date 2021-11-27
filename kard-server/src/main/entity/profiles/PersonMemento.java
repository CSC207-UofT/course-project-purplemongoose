package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;


public class PersonMemento extends Memento implements Serializable{
    /** Stores all values as subclasses
     * Getters return all values as Strings
     */
    @Serial
    private static final long serialVersionUID = 485037157128551751L;
    private Name name;
    private Phone phone;
    private Email email;
    private String username;


    public PersonMemento(Name name, Phone phone, Email email, String username) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    public String getName() {
        return name.getFullName();
    }

    public String getPronouns() {
        return name.getPronouns();
    }

    public Name getOriginalName() {
        return this.name;
    }

    public String getPhone() {
        return phone.getPhone();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getUsername() {
        return this.username;
    }

}
