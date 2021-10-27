package entity.profiles;

import entity.*;
import entity.dataFiles.Email;
import entity.dataFiles.Name;
import entity.dataFiles.Phone;

import java.io.Serializable;

public class Person extends Profile implements Client, Serializable {

    /** Stores all values as subclasses
     * Getters return all values as Strings
     */

    private final Name name;
    private final Phone phone;
    private final Email email;


    public Person(Name name, Phone phone, Email email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name.getFullName();
    }

    public String getPronouns() {
        return name.getPronouns();
    }

    public String getPhone() {
        return phone.getPhone();
    }

    public String getEmail() {
        return email.getEmail();
    }

    @Override
    public boolean isCoworker(Person p) {
        return false;
    }
}