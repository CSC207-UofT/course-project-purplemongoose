package entity.profiles;

import entity.dataFiles.Email;
import entity.dataFiles.Name;
import entity.dataFiles.Phone;

import java.io.Serializable;

public class Person implements ProfileType, Serializable {

    /** Stores all values as subclasses
     * Getters return all values as Strings
     */

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final String uuid;


    public Person(Name name, Phone phone, Email email, String uuid) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.uuid = uuid;
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

}
