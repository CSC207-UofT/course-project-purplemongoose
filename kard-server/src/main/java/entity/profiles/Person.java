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
     * Gets pronouns of the Person
     * @return string representation of pronouns
     */
    public String getPronouns() {
        return name.getPronouns();
    }

    public String getTitle() {
        return name.getTitles();
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
     * Gets username of the Person's account
     *
     * Return doesn't require it to be a String representation because the username
     * is used more for implementation, and less for user application
     *
     * @return username in type Username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Determines if another Person being passed in refers to the same Person as the
     * current Person
     *
     * @param otherPerson the other Person being compared to
     * @return true if the compared person is the same as the current Person
     */
    @Override
    public boolean equals(Object otherPerson) {
        if (this == otherPerson) return true;
        if (otherPerson == null || getClass() != otherPerson.getClass()) return false;
        Person person = (Person) otherPerson;
        return Objects.equals(name, person.name) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email) && Objects.equals(username, person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, username);
    }

    public void restore(PersonMemento memento){
        if (memento != null){
            this.name = memento.getOriginalName();
            this.phone = new Phone(memento.getPhone());
            this.email = new Email(memento.getEmail());
            this.username = memento.getUsername();
        }
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
