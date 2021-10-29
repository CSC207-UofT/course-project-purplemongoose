package usecase;

import database.ProfileGateway;
import entity.dataFiles.Email;
import entity.dataFiles.Name;
import entity.dataFiles.Phone;
import entity.profiles.Organization;
import entity.profiles.Person;
import entity.profiles.ProfileType;

public class ProfileUseCases {
    ProfileGateway gw = new ProfileGateway();

    public boolean createNewPerson(String uuid, String first, String last, String pronouns, String titles, String phone,
                                   String email) {
        Name n = new Name(first, last, pronouns, titles);
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Person person = new Person(n, p, e);
        return this.gw.insertProfileData(uuid, person);
    }

    public boolean createNewOrganization(String uuid, String name, String phone, String email) {
        Phone p = new Phone(phone);
        Email e = new Email(email);
        Organization org = new Organization(name, p, e);
        return this.gw.insertProfileData(uuid, org);
    }

    public boolean updateProfile(String uuid, ProfileType pt) {
        // Not sure best to implement this - inheritance? or individual update methods for person and organization?
        return this.gw.updateProfileData(uuid, pt);
    }
}
