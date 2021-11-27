package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;


public class OrganizationMemento extends Memento implements Serializable{
    /** Stores all values as subclasses
     * Getters return all values as Strings
     */
    @Serial
    private static final long serialVersionUID = 3245480302122932830L;
    private final String name;
    private final Phone phone;
    private final Email email;
    private final String username;


    public OrganizationMemento(String name, Phone phone, Email email, String username) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    public String getName() {
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