package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serializable;

/** Represents a Business, which is a special type of Organization.
 *
 * Businesses have a singular Owner.
 * TODO: Suggestion: Should we make the owner a type user?
 *
 */
public class Business extends Organization implements Serializable {

    private String companyName;
    private Phone companyPhone;
    private Email companyEmail;
    private final Name owner;

    // The setters

    public Business(String companyName, Phone companyPhone, Email companyEmail, String companyUsername, Name owner) {
        super(companyName, companyPhone, companyEmail, companyUsername);
        this.owner = owner;
    }

    // The getters

    public String getOwnerName() {
        return owner.getFullName();
    }

}
