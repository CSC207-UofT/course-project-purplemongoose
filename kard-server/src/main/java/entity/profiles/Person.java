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

    public PersonMemento createMemento(){
        return new PersonMemento(this.name, this.phone, this.email, this.username);
    }

    public void restore(PersonMemento memento){
        if (memento != null){
            this.name = memento.getOriginalName();
            this.phone = new Phone(memento.getPhone());
            this.email = new Email(memento.getEmail());
            this.username = memento.getUsername();
        }
    }
}
