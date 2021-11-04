package usecase;

import database.ProfileGateway;
import entity.dataFiles.Email;
import entity.dataFiles.Name;
import entity.dataFiles.Phone;
import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;
import state.AppState;

public class ProfileUseCases {
    /**
     * Initializes a new profile based on the data that the user passes in when
     * setting up their account
     */
    ProfileGateway pg = new ProfileGateway();

    /**
     * This method assumes that the person whose profile is being initiated has not yet been created
     *
     * Initializes a new profile for an individual user
     * First creates a unique UUID
     * Introduces user inputted data into classes
     * Combines all classed information into a Person
     * Associates the unique UUID to the Person
     *
     * @param first string for user's first name
     * @param last string for user's last name
     * @param pronouns string for user's pronouns
     * @param titles string for user's titles
     * @param phone string for user's phone number
     * @param email string for user's email
     * @return true if the new person profile is initialized
     */
    public boolean createNewPerson(String first, String last, String pronouns, String titles, String phone,
                                   String email) {
        String uuid = AppState.getCurrentUUID();
        Name n = new Name(first, last, pronouns, titles);
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Person person = new Person(n, p, e, uuid);
        return this.pg.insertProfileData(uuid, person);
    }

    /**
     * Initializes a new profile for an organization user
     * First creates a unique UUID
     * Introduces user inputted data into classes
     * Combines all classed information into an Organization
     * Associates the unique UUID to the Organization
     *
     * @param name string for the organization's name
     * @param phone string for the organization's phone number
     * @param email string for the organization's email
     * @return true if the new organization profile is initiated
     */
    public boolean createNewOrganization(String name, String phone, String email) {
        String uuid = AppState.getCurrentUUID();
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Organization org = new Organization(name, p, e);
        return this.pg.insertProfileData(uuid, org);
    }

    /**
     * TODO: Implement fully
     *
     * Makes changes to the given profile
     *
     * @param pt profile of the user that requires some modification
     * @return true if modification is successful
     */
    public boolean updatePersonProfile(ProfileType pt) {
        String uuid = AppState.getCurrentUUID();
        // not too sure how to implement this effectively without having 15 methods for each profile data entry
        return this.pg.updateProfileData(uuid, pt);
    }

    /**
     * Check if this UUID has already been associated to a profile
     * @param profileUIUD string for the UUID to be checked
     * @return true if the checked UUID is not associated to any profile yet
     */
    public boolean checkForProfile(String profileUIUD) {
        return pg.getProfileData(profileUIUD) == null;
    }
}
