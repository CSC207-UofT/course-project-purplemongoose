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
    ProfileGateway pg = new ProfileGateway();

    public boolean createNewPerson(String first, String last, String pronouns, String titles, String phone, String email) {
        String uuid = AppState.getCurrentUUID();
        Name n = new Name(first, last, pronouns, titles);
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Person person = new Person(n, p, e, uuid);
        return this.pg.insertProfileData(uuid, person);
    }

    public boolean createNewOrganization(String name, String phone, String email) {
        String uuid = AppState.getCurrentUUID();
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Organization org = new Organization(name, p, e);
        return this.pg.insertProfileData(uuid, org);
    }

    public boolean updatePersonProfile(ProfileType pt) {
        String uuid = AppState.getCurrentUUID();
        // not too sure how to implement this effectively without having 15 methods for each profile data entry
        return this.pg.updateProfileData(uuid, pt);
    }

    public boolean checkForProfile(String profileUIUD) {
        return pg.getProfileData(profileUIUD) == null;
    }
}
