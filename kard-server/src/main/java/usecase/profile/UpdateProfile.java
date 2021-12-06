package usecase.profile;

import database.gateway.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.MementoManager;
import entity.profiles.Person;

import java.io.IOException;

public class UpdateProfile {
    private ProfileGateway profileGateway;

    public UpdateProfile(boolean inMemory) {
        if (inMemory) {
            profileGateway = new ProfileGateway();
        } else {
            try {
                profileGateway = new ProfileGateway("./data/mainframe.db");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update the personal profiles with the given arguments, and adds it back to the profile
     * database through the ProfileGateway. This method assumes that the person whose
     * profile is being edited has been created already.
     *
     * @param profileUsername the username of the account who the profile is associated with
     * @param first string for the first name
     * @param last string for the last name
     * @param pronoun string for the preferred pronoun
     * @param title string for the preferred title
     * @param phone string for the phone number
     * @param email string for the email
     * @return whether the profile was successfully created
     */
    public boolean updatePersonProfile(String profileUsername, String first, String last,
                                       String pronoun, String title, String phone, String email) {
        if (profileGateway.getProfileData(profileUsername) != null) {
            Name n = new Name(first, last, pronoun, title);
            Phone p = new Phone(phone);
            Email e = new Email(email);
            Person person = new Person(n, p, e, profileUsername);
            MementoManager memento = profileGateway.getMementoData(profileUsername);
            memento.addPersonalMemento(n, p, e, profileUsername);
            return profileGateway.updateProfileData(profileUsername, person, memento);
        } else {
            return false;
        }
    }
}
