package entity.profiles;

import entity.dataFiles.Email;
import entity.dataFiles.Phone;

import java.io.Serializable;

public class Organization implements ProfileType, Serializable {

    /** Stores the profile information related to this Organization
     *
     */

    private final String orgName;
    private final Phone orgPhone;
    private final Email orgEmail;
    private final String orgUsername;

    // the setters

    /**
     * Organization starts off not having any clients
     */
    public Organization(String orgName, Phone phone, Email email, String orgUsername) {
        this.orgName = orgName;
        this.orgPhone = phone;
        this.orgEmail = email;
        this.orgUsername = orgUsername;
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

    @Override
    public String getUsername() {
        return orgUsername;
    }

}
