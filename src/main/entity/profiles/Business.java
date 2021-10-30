package entity.profiles;

import entity.dataFiles.Email;
import entity.dataFiles.Name;
import entity.dataFiles.Phone;

import java.io.Serializable;
import java.util.ArrayList;

public class Business extends Organization implements Serializable {

    /** Represents a Business, which is a special type of Organization.
     *
     * Businesses have a singular Owner.
     *
     */

    private String companyName;
    private Phone companyPhone;
    private Email companyEmail;
    private final Name owner;

    // The setters

    public Business(String companyName, Phone companyPhone, Email companyEmail, Name owner) {
        super(companyName, companyPhone, companyEmail);
        this.owner = owner;
    }

    // The getters

    public String getOwnerName() {
        return owner.getFullName();
    }

}
