package usecase;

import database.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.Organization;
import entity.profiles.Person;

/**
 * This class contains the uses cases which involve profiles
 */
public class ProfileUseCases {
    /**
     * Initializes a new profile based on the data that the user passes in when
     * setting up their account
     */
    ProfileGateway pg = new ProfileGateway();

    /**

    todo fix formatting
    
     * Creates a new personal profiles with the given arguments and adds it to the profile database through the
     * ProfileGateway

    This method assumes that the person whose profile is being initiated has not yet been created

     * @param accountUsername the username of the account who the profile is associated with
     * @param first the first name
     * @param last the last name
     * @param pronoun the preferred pronoun
     * @param title the preferred title
     * @param phone the phone number
     * @param email the email
     * @return whether the profile was successfully created
     */
    public boolean createNewPerson(String accountUsername, String first, String last, String pronoun, String title, String phone, String email) {
        Name n = new Name(first, last, pronoun, title);
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Person person = new Person(n, p, e, accountUsername);
        return this.pg.insertProfileData(accountUsername, person);
    }

    /**
     * Creates a new organization with the given arguments and adds it to the profile database through the
     * ProfileGateway
     *
     * @param accountUsername the username of the account who claims the business
     * @param name the name of the organization
     * @param phone the phone number
     * @param email the email
     * @return whether the profile was successfully created
     */
    public boolean createNewOrganization(String accountUsername, String name, String phone, String email) {
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Organization org = new Organization(name, p, e, accountUsername);
        return this.pg.insertProfileData(accountUsername, org);
    }

    /*
    TODO: **Phase 2** Implement

    public boolean updatePersonProfile(ProfileType pt) {
        String username = AppState.getCurrentUsername();
        // not too sure how to implement this effectively without having 15 methods for each profile data entry
        return this.pg.updateProfileData(username, pt);
    }
    */

    /**
     * Checks if the profile exists in the database
     *
     * @param profileUsername the username of the profile being checked against
     * @return if the data for the profile in the database is not null
     */
    public boolean checkForProfile(String profileUsername) {
        return pg.getProfileData(profileUsername) != null;
    }

}