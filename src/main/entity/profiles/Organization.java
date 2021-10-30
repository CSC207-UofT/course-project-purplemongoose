package entity.profiles;

import entity.dataFiles.Email;
import entity.dataFiles.Phone;

import java.io.Serializable;

public class Organization implements ProfileType, Serializable {

    /** Stores a collection of Persons who are associated to a certain Organization
     * Can also run basic operations about the Persons associated to an Organization
     *
     * Additionally, also stores a collection of related companies
     */

    private final String orgName;
    private final Phone orgPhone;
    private final Email orgEmail;

    // the setters

    /**
     * Organization starts off not having any clients
     */
    public Organization(String orgName, Phone phone, Email email) {
        this.orgName = orgName;
        this.orgPhone = phone;
        this.orgEmail = email;
    }


    // the getters

    @Override
    public String getName() {
        return orgName;
    }

    @Override
    public String getPhone() {
        return orgPhone.getPhone();
    }

    @Override
    public String getEmail() {
        return orgEmail.getEmail();
    }

}
