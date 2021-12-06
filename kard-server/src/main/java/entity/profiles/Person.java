package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class which represents a personal profile. It contains all the information that would be publicly visible
 * on one's profile
 *
 * (The getters are required for Spring Boot to serialize this class into a JSON)
 */
public class Person implements ProfileType, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private Name name;
    private Phone phone;
    private Email email;
    private String username;

    public Person(Name name, Phone phone, Email email, String username) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    /**
     * Gets the full name of the Person
     * @return string representation of full name
     */
    @Override
    public String getName() {
        return name.getFullName();
    }

    /**
     * Gets phone number of the Person
     * @return string representation of phone number
     */
    @Override
    public String getPhone() {
        return phone.getPhone();
    }

    /**
     * Gets email address of the Person
     * @return string representation of email address
     */
    @Override
    public String getEmail() {
        return email.getEmail();
    }

    /**
     * Gets the pronoun of the Person
     * @return string representation of the pronoun
     */
    public String getPronouns() {
        return name.getPronouns();
    }

    /**
     * Gets username of the Person's account
     *
     * @return username in type Username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Restores the profile to a previous version of the profile stored as a memento
     * @param memento The previous version of the profile
     */
    public void restore(PersonMemento memento) {
        if (memento != null) {
            this.name = memento.getOriginalName();
            this.phone = new Phone(memento.getPhone());
            this.email = new Email(memento.getEmail());
            this.username = memento.getUsername();
        }
    }

    @Override
    public boolean equals(Object otherPerson) {
        if (this == otherPerson) return true;
        if (otherPerson == null || getClass() != otherPerson.getClass()) return false;
        Person person = (Person) otherPerson;
        return Objects.equals(name, person.name) && Objects.equals(phone, person.phone)
                && Objects.equals(email, person.email) && Objects.equals(username, person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, username);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", phone=" + phone +
                ", email=" + email +
                ", username='" + username + '\'' +
                '}';
    }
}
