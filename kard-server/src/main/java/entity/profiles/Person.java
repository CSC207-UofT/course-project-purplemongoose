package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Person implements ProfileType, Serializable {

    /** Stores all values as subclasses
     * Getters return all values as Strings
     */
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final String username;


    public Person(Name name, Phone phone, Email email, String username) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    @Override
    public String getName() {
        return name.getFullName();
    }

    public String getPronouns() {
        return name.getPronouns();
    }

    @Override
    public String getPhone() {
        return phone.getPhone();
    }

    @Override
    public String getEmail() {
        return email.getEmail();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email) && Objects.equals(username, person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, username);
    }
}
